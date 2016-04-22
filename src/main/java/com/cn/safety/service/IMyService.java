package com.cn.safety.service;

import java.util.List;
import java.util.Map;

public interface IMyService {
	public List<Map<String, Object>> getContacts(String userId);
	
	public int addContact(String userId,String contactTel,String contactName);
}
 