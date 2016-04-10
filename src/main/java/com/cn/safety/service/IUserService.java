package com.cn.safety.service;

import java.util.HashMap;

import com.cn.safety.pojo.User;

public interface IUserService {
	public User getUserById(int userId);
	public HashMap<String, Object> getUserHome(String userId);
	public int insert(String name);
	public int addHomeAddress(String userId,String lat,String lng);
	public int updateHomeAddress(String userId,String lat,String lng);
	public int strongHomeAddress(String userId);
}
 