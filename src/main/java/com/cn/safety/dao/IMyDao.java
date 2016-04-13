package com.cn.safety.dao;

import java.util.List;
import java.util.Map;

public interface IMyDao {
    
    int addHomeAddress(String userid,String lat,String lng);
    
    int updateHomeAddress(String userid,String lat,String lng);
    
    int strongHomeAddress(String userid);

    List<Map<String,Object>> getContacts(String userid);
    
}