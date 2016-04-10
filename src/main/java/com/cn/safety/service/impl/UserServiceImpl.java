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
	/**
	 * 校验home坐标
	 */
	@Override
	public int addHomeAddress(String userId, String lat, String lng) {
		// TODO Auto-generated method stub
		int i = this.userDao.addHomeAddress(userId,lat,lng);
		return i;
	}
	
	/**
	 * 校验home坐标
	 */
	@Override
	public int strongHomeAddress(String userId) {
		// TODO Auto-generated method stub
		int i = this.userDao.strongHomeAddress(userId);
		return i;
	}
	
	/**
	 * 校验home坐标
	 */
	@Override
	public int updateHomeAddress(String userId, String lat, String lng) {
		// TODO Auto-generated method stub
		int i = this.userDao.updateHomeAddress(userId,lat,lng);
		return i;
	}
	
	@Override
	public HashMap<String, Object> getUserHome(String userId) {
		HashMap<String,Object> user = this.userDao.selectUserHome(userId);
		return user;
	}

}
