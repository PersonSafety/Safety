package com.cn.safety.dao;

import java.util.List;
import java.util.Map;

public interface IMyDao {
    
    int addContact(String userid,String contactTel,String contactName);

    List<Map<String,Object>> getContacts(String userid);
    
}