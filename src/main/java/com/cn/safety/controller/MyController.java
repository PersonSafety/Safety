package com.cn.safety.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cn.safety.model.ResultData;
import com.cn.safety.pojo.User;
import com.cn.safety.service.IMyService;
import com.cn.safety.service.IUserService;
import com.cn.safety.utils.Constant;

/**
 * 我的
 * @author tech
 *
 */
public class MyController {
	@Resource
	private IMyService myService;
	/**
	 * 添加紧急联系人
	 * @param json
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/addContact", method = RequestMethod.POST,consumes = "application/json")  
    @ResponseBody
    public ResultData<User> addContact(@RequestBody JSONObject json) throws IOException { 
    	String userid = json.getString("userid");
    	String contactTel = json.getString("contactTel");//电话
    	String contactName = json.getString("contactName");//姓名
        ResultData<User> resultData =new ResultData<User>();
        resultData.setStatus(0);  
        resultData.setData(null);  
        if (userid == null || contactTel == null) {
            resultData.setMessage(Constant.Null_Param_Error);  
        } else {        
            try {
            	int i = 0;
            	i = myService.addContact(userid, contactTel, contactName);
                if(i == 1){
                	resultData.setStatus(1);
                	resultData.setMessage("添加成功");  
                }else{
                	resultData.setMessage("添加失败");  
                }
            } catch (Exception e) {  
                resultData.setMessage("addContact出错:"+e.getMessage());  
            }             
        }
        return resultData;
    }
	/**
	 * 获得紧急联系人
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getContacts", method = RequestMethod.GET)
	@ResponseBody
	public ResultData<List<Map<String,Object>>> getContacts(@RequestParam("userid") String userid)throws IOException {
		ResultData<List<Map<String,Object>>> resultData =new ResultData<List<Map<String,Object>>>();
		resultData.setStatus(0);  
        resultData.setData(null); 
        if (userid == null) {             
            resultData.setMessage(Constant.Null_Param_Error);  
        } else {            
            try { 
            	List<Map<String,Object>> contacts = myService.getContacts(userid);
            	if(CollectionUtils.isEmpty(contacts)){
            		resultData.setMessage("无记录");  
            	}else{
            		resultData.setStatus(1);
            		resultData.setData(contacts);
            	}
            }catch (Exception e) {  
                resultData.setMessage("getContacts失败:"+e.getMessage());  
            }             
        }
        return resultData;
	}
}
