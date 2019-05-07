package com.jt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;

@Service
public class UserServiceImpl implements UserService {
	/**
	 * param: 用户输入内容
	 * type: 1 username、2 phone、3 email
	 * sql: select count(*) from tb_user where username="张三";
	 * 经过分析:	首先应该将1 2 3转化为具体的数据库字段.
	 */
	
	@Autowired
	private UserMapper userMapper;

	
	@Override
	public Boolean findCheckUser(String param, Integer type) {
		String column = null;
		switch(type) {
		case 1:
			column="username";
			break;
		case 2:	
			column="phone";
			break;
		case 3:	
			column="email";
			break;	
			
		}
		
		//根据数据库的结果分析如果总数为0，返回false，否则返回true
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq(column, param);
		int count = userMapper.selectCount(queryWrapper);
		
		
		return count==0?false:true;
	}

	
	
	
	
}
