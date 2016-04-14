package com.cn.safety.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.safety.dao.IMyDao;
import com.cn.safety.dao.ISafeDao;
import com.cn.safety.dao.IUserDao;
import com.cn.safety.pojo.User;
import com.cn.safety.service.IMyService;
import com.cn.safety.service.ISafeService;
import com.cn.safety.service.IUserService;

@Service("helpService")
public class HelpServiceImpl implements IMyService {
	@Resource
	private IMyDao myDao;
	
	@Override
	public List<Map<String, Object>> getContacts(String userId) {
		List<Map<String,Object>> contacts = this.myDao.getContacts(userId);
		return contacts;
	}

}
