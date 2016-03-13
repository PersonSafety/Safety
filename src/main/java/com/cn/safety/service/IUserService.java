package com.cn.safety.service;

import com.cn.safety.pojo.User;

public interface IUserService {
	public User getUserById(int userId);
	public int insert(String name);
}
 