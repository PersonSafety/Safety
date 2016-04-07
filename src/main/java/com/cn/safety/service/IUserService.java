package com.cn.safety.service;

import java.util.HashMap;

import com.cn.safety.pojo.User;

public interface IUserService {
	public User getUserById(int userId);
	public HashMap<String, Object> getUserHome(String userId);
	public int insert(String name);
	public int homeAddress(String userId,String homecoordinate);
}
 