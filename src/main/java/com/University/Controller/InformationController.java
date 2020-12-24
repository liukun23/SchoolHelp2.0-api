package com.University.Controller;

import com.University.entity.RequestStatus;
import com.University.model.Information;

import com.University.model.PageResult;
import com.University.service.InformationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Controller
@RequestMapping("information")
public class InformationController {
    @Autowired
    private InformationService informationService;

    RequestStatus requestStatus = new RequestStatus();
    @PostMapping("type")
    public ResponseEntity<PageResult<Information>> InformationByType(@RequestParam("informationType") int informationType,
                                                               @RequestParam(value = "page",defaultValue = "1")Integer page,
                                                               @RequestParam(value = "rows",defaultValue = "5")Integer rows){
        PageResult<Information> information = this.informationService.InformationByType(informationType,page,rows);

        if(CollectionUtils.isEmpty(information.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(information);
    }
    /*~~~~~~~~
    * 添加消息
    * */
    @PostMapping("addInformation")
    public ResponseEntity<Void> addInformation(@RequestBody Information information){
        this.informationService.addInformation(information);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("page")
    public ResponseEntity<PageResult<Information>> InformationPage(
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows
    ){
        PageResult<Information> result = this.informationService.InformationPage(page, rows);
        if(CollectionUtils.isEmpty(result.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
    /*
    * 单个信息查询，首页点击事件进入详情信息页面
    * */
    @GetMapping("InformationById")
    public ResponseEntity<Information> selectInformationById(@RequestParam("informationId") Integer informationId){
        Map<String,Object>  modelMap = new HashMap<String,Object>();
        Information information = informationService.selectInformationById(informationId);
        modelMap.put("information",information);
        modelMap.put("status",200);
        return ResponseEntity.ok(information);
    }
    /*
    * 改变消息接收状态
    * */
    @GetMapping("updateInformation")
    public ResponseEntity<Void>  updateInformationById(@RequestParam("informationId") Integer informationId){
        informationService.updateInformationById(informationId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*
    * 信息接收
    * */
    @PostMapping("receiveInforation")
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseEntity<RequestStatus> receiveInforation(@RequestParam("informationId") Integer informationId,
                                                  @RequestParam("userId") Integer userId){
        Integer num = informationService.selectpeopleNum(informationId);
        Integer count = informationService.countpeopleNum(informationId);
        Information information = informationService.selectInformationById(informationId);
        Integer people = informationService.SelectReceiveInformation(informationId,userId);
        RequestStatus requestStatus = new RequestStatus();
        if(information.getUser().getUserId() == userId){
            String msg = "不能接收自己发布的信息!";
            requestStatus.setMsg(msg);
            requestStatus.setStatus(400);
            return new ResponseEntity<RequestStatus>(requestStatus,HttpStatus.BAD_REQUEST);
        }
            if(people != 0){
                String msg = "不能同时接收同一个消息!";
                requestStatus.setMsg(msg);
                requestStatus.setStatus(400);
                return new ResponseEntity<RequestStatus>(requestStatus,HttpStatus.BAD_REQUEST);
            }
        if (count+1 == num){
            informationService.updateInformationById(informationId);
        }
        if (count < num ){
            informationService.receiveInformation(informationId,userId);
            String msg = "接收信息成功!";
            requestStatus.setMsg(msg);
            requestStatus.setStatus(200);
            return new ResponseEntity<RequestStatus>(requestStatus,HttpStatus.OK);
        }else {
            String msg = "接收消息出错!";
            requestStatus.setMsg(msg);
            requestStatus.setStatus(400);
            return new ResponseEntity<RequestStatus>(requestStatus,HttpStatus.BAD_REQUEST);
        }


    }
    /*
    * 用户取消消息
    * */
    @PostMapping("CancelReceiveInforation")
    public ResponseEntity<Map<String,Object>> CancelReceiveInforation(@RequestParam("informationId") Integer informationId,
                                                                      @RequestParam("userId") Integer userId){
        Map<String,Object> model = new HashMap<>();
        informationService.CancelReceiveInforation(informationId,userId);
        model.put("msg","success");
        model.put("state",200);
        return ResponseEntity.ok(model);
    }
    /*
    * 上传图片
    * */
    @PostMapping("uploadimage")
    public ResponseEntity<List<String>> uploadimage(@RequestParam("file") MultipartFile[] files){
        List<String> urlList = new ArrayList<String>();
        for (MultipartFile file : files) {
            System.out.println(file);
            String url = informationService.upload(file);
        if (StringUtils.isBlank(url)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
            urlList.add(url);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(urlList);
    }
}
