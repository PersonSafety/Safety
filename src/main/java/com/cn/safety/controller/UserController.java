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
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.safety.model.ResultData;
import com.cn.safety.model.UserRequest;
import com.cn.safety.pojo.User;
import com.cn.safety.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userService;
	
	@RequestMapping("/showUser")
	public String toIndex(HttpServletRequest request,Model model){
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		return "showUser";
	}
	
	@RequestMapping(value = "/getdate", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getDate(HttpServletResponse response)throws IOException {
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");

		String datetime = tempDate.format(new java.util.Date());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", datetime);
		return map;  
	}
	
	/** 
     * add  增加一个DishesCook 
     * http://localhost:8080/demo2/dishescook/add 
     * @param requestData 
     * @param mode 
     * @param response 
     * @return 
     * @throws IOException 
     */  
    @RequestMapping(value = "/add", method = RequestMethod.POST,consumes = "application/json")  
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












