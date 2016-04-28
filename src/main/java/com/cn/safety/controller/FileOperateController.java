package com.cn.safety.controller;

/** 
 * 
 * @author tech
 * @date 2016-4-14 
 */  
  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
  




import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  




import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.ServletRequestUtils;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;  

import com.cn.safety.model.ResultData;
import com.cn.safety.utils.FileOperateUtil;
  
/** 
 *  
 * @author tech
 * @date 2016-4-14 
 */  
@Controller  
@RequestMapping(value = "/file")  
public class FileOperateController {  
    /** 
     * 到上传文件的位置 
     *  
     * @return 
     */  
    @RequestMapping(value = "to_upload")  
    public ModelAndView toUpload() {  
        return new ModelAndView("background/fileOperate/upload");  
    }  
  
    /** 
     * 上传文件 
     *  
     * @param request 
     * @return 
     * @throws Exception 
     */  
    @RequestMapping(value = "/upload")
    @ResponseBody
    public ResultData<Map<String,Object>> upload(HttpServletRequest request) throws Exception {
  
    	ResultData<Map<String,Object>> resultData =new ResultData<Map<String,Object>>();
        resultData.setStatus(0);
        resultData.setData(null);
        Map<String, Object> map = new HashMap<String, Object>();
  
        List<Map<String, Object>> result = FileOperateUtil.upload(request);
        map.put("result", result);

        resultData.setMessage("上传成功");
        resultData.setData(map);
        return resultData;
    }  
  
    /** 
     * 下载 
     *  
     * @param attachment 
     * @param request 
     * @param response 
     * @return 
     * @throws Exception 
     */  
    @RequestMapping(value = "/download")  
    public ModelAndView download(HttpServletRequest request,  
            HttpServletResponse response) throws Exception {  
  
        String storeName = "201205051340364510870879724.zip";  
        String realName = "Java设计模式.zip";  
        String contentType = "application/octet-stream";  
  
        FileOperateUtil.download(request, response, storeName, contentType,  
                realName);  
  
        return null;  
    }  
}
