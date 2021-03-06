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
import com.cn.safety.service.IHelpService;
import com.cn.safety.service.IMyService;
import com.cn.safety.service.ISafeService;
import com.cn.safety.service.IUserService;

@Service("helpService")
public class HelpServiceImpl implements IHelpService {
	@Resource
	private IMyDao myDao;
	
}
