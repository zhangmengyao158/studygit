package com.yc.mybatis.service;

import com.yc.mybatis.entity.PaginationBean;
import com.yc.mybatis.entity.User;
import com.yc.mybatis.entity.UserBean;

public interface UserService {

 	boolean login(User user);

	PaginationBean<User> listPartUsers(String currPage, String pageSize);

	boolean modifyUser(User user);

 
 
}
