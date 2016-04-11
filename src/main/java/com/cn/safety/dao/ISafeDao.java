package com.cn.safety.dao;

import java.util.HashMap;

import com.cn.safety.pojo.User;

public interface ISafeDao {
    
    int addHomeAddress(String userid,String lat,String lng);
    
    int updateHomeAddress(String userid,String lat,String lng);
    
    int strongHomeAddress(String userid);

    HashMap<String,Object> selectUserHome(String userid);
    
}