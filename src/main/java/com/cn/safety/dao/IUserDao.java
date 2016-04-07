package com.cn.safety.dao;

import java.util.HashMap;

import com.cn.safety.pojo.User;

public interface IUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    HashMap<String,Object> selectUserHome(String userid);
    
    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}