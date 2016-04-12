package com.cn.safety.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cn.safety.model.ResultData;
import com.cn.safety.model.UserRequest;
import com.cn.safety.pojo.User;
import com.cn.safety.service.ISafeService;
import com.cn.safety.service.IUserService;
import com.cn.safety.webMagic.NewsCrawler;
/**
 * 安全
 * @author tech
 *
 */
@Controller
@RequestMapping("/safe")
public class SafeController {
	@Resource
	private ISafeService safeService;
    
    /**
     * 算法思路：
     * 1.先从服务器取家的坐标
     * 2.如果没有，则上传坐标；如果有，则计算该坐标和现在坐标的距离。
     * 3.如果距离很小，则本次校验通过。如果距离很大，上传新的坐标
     * 校验home坐标
     * @param requestData
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/homeAddress", method = RequestMethod.POST,consumes = "application/json")  
    @ResponseBody
    public ResultData<User> homeAddress(@RequestBody JSONObject json) throws IOException { 
    	String userid = json.getString("userid");
    	String isNew = json.getString("isNew");//第一次上传坐标
    	String isHome = json.getString("isHome");//是否和上次坐标一样
    	String lat = json.getString("lat");
    	String lng = json.getString("lng");
        ResultData<User> resultData =new ResultData<User>();
        resultData.setStatus(0);  
        resultData.setData(null);  
        if (userid == null || lat == null || lng == null) {              
            resultData.setMessage("参数错误：没有传入参数");  
        } else {        
            try {
            	int i = 0;
            	if(isNew.equals("1")){
            		i=safeService.addHomeAddress(userid, lat, lng); 
            	}else if(isHome.equals("0")){
            		//重新上传坐标
            		i = safeService.updateHomeAddress(userid, lat, lng);
            	}else{
            		//验证天数+1
            		i = safeService.strongHomeAddress(userid);
            	}
                if(i == 1){
                	resultData.setMessage("更新成功");  
                }else{
                	resultData.setMessage("更新失败");  
                }
            } catch (Exception e) {  
                resultData.setMessage("更新失败:"+e.getMessage());  
            }             
        }
        return resultData;
    }
    /**
     * 得到已记录的home坐标
     * @param userid
     * @return
     */
    @RequestMapping(value = "/getUserHome", method = RequestMethod.GET)
    @ResponseBody
	public ResultData<HashMap<String,Object>> getUserHome(@RequestParam("userid") String userid){
    	ResultData<HashMap<String,Object>> resultData =new ResultData<HashMap<String,Object>>();
        resultData.setStatus(0);  
        resultData.setData(null);  
        if (userid == null) {              
            resultData.setMessage("参数错误：没有传入参数");  
        } else {             
            try {  
                HashMap<String, Object> user=safeService.getUserHome(userid);
                if(user == null){
                	System.out.println("-------------不存在哦--------------");
                	resultData.setMessage("无记录");  
                }else{
                	resultData.setData(user);
                }
            } catch (Exception e) {  
                resultData.setMessage("getUserHome失败:"+e.getMessage());  
            }             
        }
        return resultData;
	}
}












