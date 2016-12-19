package com.yc.mybatis.mapper;

import java.util.List;

import com.yc.mybatis.entity.PaginationBean;
import com.yc.mybatis.entity.User;

public interface UserMapper {
//	public List<User> getAll();      ///获得所有人员信息
//	public User get(int id);          //获得指定人员明细
//	public void update(User profile);  //更新指定人员明细
	
	User findUser(User user);

	PaginationBean<User> getUsersByPagination(PaginationBean<User> userBean);

	int updateUser(User user);
	
	User getUserById(int id);
}
