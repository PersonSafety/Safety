package com.cn.safety.model;

import java.io.Serializable;

public class UserRequest implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;

    private String password;

    private Integer age;

	public UserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRequest(String userName, String password, Integer age) {
		super();
		this.userName = userName;
		this.password = password;
		this.age = age;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
    
    
    
}
