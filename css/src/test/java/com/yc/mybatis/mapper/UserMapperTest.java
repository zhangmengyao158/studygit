package com.yc.mybatis.mapper;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import com.yc.mybatis.entity.User;
import com.yc.mybatis.util.MyBatisUtil;

public class UserMapperTest {
	SqlSession session = MyBatisUtil.getAutoCommitSession();
	private UserMapper userMapper;
	private UserMapper userMapper02;

	@Before
	public void setUp() throws Exception {
		userMapper  = session.getMapper(UserMapper.class);
		userMapper02  = session.getMapper(UserMapper.class);

	}

	@Test
	public void testGetUserById() {
		 User user = userMapper.getUserById(10355);
		 System.out.println("第一遍：" + user);
		 session.clearCache();
		 System.out.println("=====================");
		 user = userMapper02.getUserById(11000);
		 System.out.println("第二遍：" + user);
	}

}
