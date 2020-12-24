package com.University.service;

import com.University.config.JwtProperties;
import com.University.entity.UserInfo;
import com.University.mapper.InformationDao;
import com.University.mapper.InformationMapper;
import com.University.mapper.UserAndInformation;
import com.University.mapper.UserMapper;
import com.University.model.User;
import com.University.model.UserCode;
import com.University.model.User_Information;
import com.University.utils.JwtUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserAndInformation userAndInformation;
    @Autowired
    private InformationDao informationDao;
    @Autowired
    private InformationMapper informationMapper;
    public UserCode SelectByOpenId(String openId) throws Exception {
        UserCode user = userMapper.SelectByOpenId(openId);
        return user;
    }

    public void addUser(UserCode user) {
        userMapper.addUser(user);
    }

    public User SelectByUserId(Integer userId) {
        return userMapper.SelectByUserId(userId);
    }

    //生产token
    public String authentication(String openId) {
        try {
            //调用登陆方法返回用户信息
            UserCode user = this.userMapper.SelectByOpenId(openId);
            //判断
            if (user==null){
                return null;
            }
            //生成tokent
            String token = JwtUtils.generateToken(new UserInfo(user.getUserId(), user.getOpenId()), jwtProperties.getPrivateKey(), jwtProperties.getExpire());
            System.out.print(token);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String,Object> Thirdlogin(UserCode userCode) throws Exception {
        Map<String,Object> model = new HashMap<>();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={code}&grant_type=authorization_code";
        Map<String,String> requestMap = new HashMap<>();
        requestMap.put("appid","wx4bb4cd8720b860ef");
        requestMap.put("secret","b97afe43e8bbe4fecf3ee8068d6f0798");
        requestMap.put("code",userCode.getCode());
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class,requestMap);
        JSONObject jsonObject= JSONObject.fromObject(responseEntity);
        if (jsonObject.has("body")){
            String body=jsonObject.getString("body");
            JSONObject data= JSONObject.fromObject(body);
            if (data.has("openid")){
                String openId=data.getString("openid");
                String session_key=data.getString("session_key");
//            System.out.println(openId);
//            System.out.println(session_key);
                UserCode user = userMapper.SelectByOpenId(openId);
                if (user == null){
                    userCode.setOpenId(openId);
                    userMapper.addUser(userCode);
                    UserCode user1 = userMapper.SelectByOpenId(openId);
                    model.put("data",user1);
                }else {
                    model.put("data",user);
                }
                String token = JwtUtils.generateToken(new UserInfo(openId,session_key), jwtProperties.getPrivateKey(), jwtProperties.getExpire());
//                System.out.println(token);
                model.put("token",token);
                model.put("msg","success");
                model.put("state",200);
                return model;
            }else {
                model.put("msg","openid有错或超时");
                model.put("state",200);
                return model;
            }
        }else {
            model.put("msg","传参或系统有误");
            model.put("state",200);
            return model;
        }
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Object> UserAndInformation(Integer userId) {
        List<Object> informations = new LinkedList<Object>();
        List<User_Information> user_informations = userAndInformation.userAndInformation(userId);
        for(User_Information user_information :user_informations){
            System.out.println(user_information);
            informations.add(informationMapper.InformationById(user_information.getInformationId()));
        }
        System.out.println(informations);
        return informations;
    }
}
