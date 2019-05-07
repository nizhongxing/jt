package com.jt.service;

import com.jt.pojo.User;

public interface DubboUserService {

	void saveUser(User user);

	//dubbo接口定义实现的接口
	String findUserByUP(User user);
}
