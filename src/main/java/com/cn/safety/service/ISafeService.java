package com.cn.safety.service;

import java.util.HashMap;

public interface ISafeService {
	public HashMap<String, Object> getUserHome(String userId);
	public int addHomeAddress(String userId,String lat,String lng);
	public int updateHomeAddress(String userId,String lat,String lng);
	public int strongHomeAddress(String userId);
}
 