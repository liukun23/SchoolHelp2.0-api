package com.University.service;

import com.University.mapper.InformationDao;
import com.University.mapper.InformationImpl;
import com.University.mapper.InformationMapper;
import com.University.model.Information;

import com.University.model.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class InformationService implements InformationImpl{

    private static  final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg","image/gif");
    private static final Logger LOGGER = LoggerFactory.getLogger(InformationService.class);
    @Autowired
    private InformationMapper informationMapper;
    @Autowired
    private InformationDao informationDao;
    public List<Information> Allinformation(){
        return informationMapper.Allinformation();
    }

    public PageResult<Information> InformationByType(Integer informationType,Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<Information> information = informationDao.selectInformationByType(informationType);
        PageInfo<Information> pageInfo = new PageInfo<>(information);
        return new PageResult<>((long) pageInfo.getPages(),pageInfo.getTotal(),pageInfo.getList());
    }

    public void addInformation(Information information) {
         informationMapper.addInformation(information);
    }

    public PageResult<Information> InformationPage(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        List<Information> allinformation = informationDao.selectInformation();
        PageInfo<Information> pageInfo = new PageInfo<>(allinformation);

        return new PageResult<>((long) pageInfo.getPages(),pageInfo.getTotal(),pageInfo.getList());
    }
    /*查找*/
    @Override
    public List<Information> selectInformation() {
        return informationDao.selectInformation();
    }

    @Override
    public Information selectInformationById(Integer informationId){
        return informationDao.selectInformationById(informationId);
    }
    /*
    * 修改信息接收状态
    * */
    public void updateInformationById(Integer informationId) {
        informationMapper.updateInfomation(informationId);
    }
    /*
    * 查找信息需求多少人
    * */
    public Integer selectpeopleNum(Integer informationId){
        return informationMapper.selectpeopleNum(informationId);
    }
    /*
    * 统计消息已经接收人数量
    * */
    public Integer countpeopleNum(Integer informationId){
        Integer integer = informationMapper.countpeopleNum(informationId);
        if (integer == null){
            integer = 0;
        }
        return integer;
    }

    /*
    * 接收数据
    * */
    public void receiveInformation(Integer informationId,Integer userId){
        informationMapper.receiveInformation(informationId,userId);
    }

    public Integer SelectReceiveInformation(Integer informationId,Integer userId) {
        Integer integer = informationMapper.SelectReceiveInformation(informationId, userId);
        return integer;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public String upload(MultipartFile file) {
        //获取上传的图片文件
        String originalFilename = file.getOriginalFilename();
        // 校验文件的类型
        String contentType = file.getContentType();
        if (!CONTENT_TYPES.contains(contentType)){
            // 文件类型不合法，直接返回null
            LOGGER.info("文件类型不合法：{}", originalFilename);
            return null;
        }
        try {
            // 校验文件的内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null){
                LOGGER.info("文件内容不合法：{}", originalFilename);
                return null;
            }
            // 保存到服务器
            file.transferTo(new File("/project/image/" + originalFilename));
            // 生成url地址，返回
//            return "http://image.leyou.com/" + originalFilename;
            return "http://120.24.151.246:8080/images/"+originalFilename;
        } catch (IOException e) {
            LOGGER.info("服务器内部错误：{}", originalFilename);
            e.printStackTrace();
        }
        return null;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void CancelReceiveInforation(Integer informationId, Integer userId) {
        Integer num = informationMapper.selectpeopleNum(informationId);
        Integer count = informationMapper.countpeopleNum(informationId);
        if (count == null){
            count = 0;
        }
        if (count+1 == num){
            informationMapper.CancelupdateInformationById(informationId);
            System.out.println("更新数据");
        }
        informationMapper.CancelReceiveInforation(informationId,userId);
    }
}
