package com.University.Controller;


import com.University.config.JwtProperties;
import com.University.entity.UserInfo;
import com.University.model.UserCode;
import com.University.utils.CookieUtils;
import com.University.service.UserService;
import com.University.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
@EnableConfigurationProperties(JwtProperties.class)
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties prop;
    /*
    * 登陆
    * */
    @PostMapping("openId")
    public ResponseEntity<UserCode> SelectByOpenId(@RequestParam("openId") String openId,
                                                     @RequestParam("phone") String phone,
                                                     @RequestParam("nickname") String nickname,
                                                     @RequestParam("avatarurl") String avatarurl) throws Exception {
        UserCode user = this.userService.SelectByOpenId(openId);
        if (user==null){
            UserCode user2 = new UserCode();
            user2.setAvatarurl(avatarurl);
            user2.setNickname(nickname);
            user2.setOpenId(openId);
            user2.setPhone(phone);
            this.userService.addUser(user2);
            user2 = this.userService.SelectByOpenId(openId);
            return ResponseEntity.ok(user2);
        }
        return ResponseEntity.ok(user);
    }
    /*
    * 第三方授权登陆
    * */
    @PostMapping("Thirdlogin")
    public ResponseEntity<Map<String,Object>> Thirdlogin(@RequestBody UserCode userCode) throws Exception {
        Map<String, Object> thirdlogin = userService.Thirdlogin(userCode);
        return ResponseEntity.ok(thirdlogin);
    }
   /* @GetMapping("userId/{id}")
    public User SelectByUserId(@PathVariable("id") Integer id){
        System.out.print(id);
        User user = this.userService.SelectByUserId(id);
        return user;

    }*/
    /*~~~~~~~~~*/
   /*
   *
   @PostMapping("addUser")
    public ResponseEntity<User> addInformation(@RequestBody User user){
        this.userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    *
    */
   @PostMapping("accredit")
   public ResponseEntity<Void> AuthCheck(@RequestParam("openId") String openId,
                                         HttpServletRequest request,
                                         HttpServletResponse response){
       //登陆效验收
       String token = this.userService.authentication(openId);
       if (StringUtils.isBlank(token)){
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
       }
       //将tokenSH_TOKEN HttpOnly设置为true,防止通过js获取或修改
       CookieUtils.setCookie(request,response,prop.getCookieName(),token,prop.getCookieMaxAge(),null,true);
       //返回
       return ResponseEntity.ok().build();
   }

    /*
     * 解析用户信息
     * */
    @GetMapping("verify")
    public ResponseEntity<UserInfo> verifyInfo(@CookieValue("SH_TOKEN") String token, HttpServletRequest request, HttpServletResponse response){
        //获取token
        try {
            //cookie 和token 有效时间需要刷新，重新计时(这样防止重复登陆)
            UserInfo user = JwtUtils.getInfoFromToken(token, prop.getPublicKey());
            //刷新token
            token = JwtUtils.generateToken(user, prop.getPrivateKey(), prop.getExpire());
            CookieUtils.setCookie(request,response,prop.getCookieName(),token,prop.getCookieMaxAge(),null,true);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    /*
    * 自己接收的信息
    * */
    @GetMapping("myreceive")
    public ResponseEntity<Map<String,Object>> myreceive(@RequestParam("userId") Integer userId){
        Map<String,Object> model = new HashMap<>();
        List<Object> user_informations = userService.UserAndInformation(userId);
        model.put("data",user_informations);
        model.put("msg","success");
        model.put("state",200);
        return ResponseEntity.ok(model);
    }
}
