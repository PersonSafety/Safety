package com.cn.safety.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.safety.dao.ISafeDao;
import com.cn.safety.dao.IUserDao;
import com.cn.safety.pojo.User;
import com.cn.safety.service.ISafeService;
import com.cn.safety.service.IUserService;

@Service("safeService")
public class SafeServiceImpl implements ISafeService {
	@Resource
	private ISafeDao safeDao;
	/**
	 * 校验home坐标
	 */
	@Override
	public int addHomeAddress(String userId, String lat, String lng) {
		// TODO Auto-generated method stub
		int i = this.safeDao.addHomeAddress(userId,lat,lng);
		return i;
	}
	
	/**
	 * 校验home坐标
	 */
	@Override
	public int strongHomeAddress(String userId) {
		// TODO Auto-generated method stub
		int i = this.safeDao.strongHomeAddress(userId);
		return i;
	}
	
	/**
	 * 校验home坐标
	 */
	@Override
	public int updateHomeAddress(String userId, String lat, String lng) {
		// TODO Auto-generated method stub
		int i = this.safeDao.updateHomeAddress(userId,lat,lng);
		return i;
	}
	
	@Override
	public HashMap<String, Object> getUserHome(String userId) {
		HashMap<String,Object> user = this.safeDao.selectUserHome(userId);
		return user;
	}

}
