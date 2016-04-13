package com.cn.safety.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cn.safety.model.ResultData;
import com.cn.safety.service.IMyService;
import com.cn.safety.service.IUserService;

/**
 * 我的
 * @author tech
 *
 */
public class MyController {
	@Resource
	private IMyService myService;
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
            resultData.setMessage("参数错误：没有传入参数");  
        } else {            
            try { 
            	List<Map<String,Object>> contacts = myService.getContacts(userid);
            	if(CollectionUtils.isEmpty(contacts)){
            		resultData.setMessage("无记录");  
            	}else{
            		resultData.setData(contacts);
            	}
            }catch (Exception e) {  
                resultData.setMessage("getContacts失败:"+e.getMessage());  
            }             
        }
        return resultData;
	}
}
