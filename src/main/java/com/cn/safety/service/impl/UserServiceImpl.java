package com.cn.safety.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.safety.dao.IUserDao;
import com.cn.safety.pojo.User;
import com.cn.safety.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;
	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userDao.selectByPrimaryKey(userId);
	}
	@Override
	public int insert(String name) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUsername("huangtq");
		user.setUsersex(1);
		user.setUserage(23);
		int i = this.userDao.insert(user);
		return i;
	}

}
