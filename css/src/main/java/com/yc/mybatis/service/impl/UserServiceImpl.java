package com.yc.mybatis.service.impl;

import java.util.List;

import com.yc.mybatis.entity.PaginationBean;
import com.yc.mybatis.entity.User;
import com.yc.mybatis.entity.UserBean;
import com.yc.mybatis.mapper.UserMapper;
import com.yc.mybatis.service.UserService;
import com.yc.mybatis.util.MyBatisUtil;

public class UserServiceImpl implements UserService {

	private UserMapper userMapper;
	
	public UserServiceImpl() {
		userMapper  = MyBatisUtil.getAutoCommitSession().getMapper(UserMapper.class);
//		System.out.println(userMapper);
	}
	@Override
	public boolean login(User user) { 
		return userMapper.findUser(user)!= null;
	}
 
	@Override
	public PaginationBean<User> listPartUsers(String currPage, String pageSize) {
   		PaginationBean<User> userBean = new PaginationBean<User>();
		if(currPage != null){
		   userBean.setCurrPage(Integer.parseInt(currPage));
		}
		if(pageSize != null){
		   userBean.setPageSize(Integer.parseInt(pageSize));
		}
		return userMapper.getUsersByPagination(userBean);
	}
	@Override
	public boolean modifyUser(User user) {
		return userMapper.updateUser(user) > 0;
	}
	 
	 
 
}
