package com.cn.safety.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.safety.model.ResultData;
import com.cn.safety.model.UserRequest;
import com.cn.safety.pojo.User;
import com.cn.safety.service.IUserService;
/**
 * 新闻控制器
 * @author tech
 *
 */
@Controller
@RequestMapping("/news")
public class NewsController {

	@Resource
	private IUserService userService;
	
	@RequestMapping(value = "/get", method = RequestMethod.POST,consumes = "application/json")  
    @ResponseBody  
    public ResultData<User> add(@RequestBody UserRequest requestData) throws IOException {        
        ResultData<User> resultData =new ResultData<User>();  
        resultData.setStatus(0);  
        resultData.setData(null);  
        if (requestData==null) {              
            resultData.setMessage("参数错误：没有传入参数");  
        } else {              
            //身份验证处理  
            try {  
                int i=userService.insert(requestData.getUserName());  
                if (i==1){  
                    resultData.setStatus(1);  
                    resultData.setMessage("添加成功");  
                } else {  
                    resultData.setMessage("添加失败");  
                }  
            } catch (Exception e) {  
                resultData.setMessage("添加失败:"+e.getMessage());  
            }             
        }  
        return resultData;  
    }
}