package com.yc.mybatis.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyBatisUtilTest {

	@Test
	public void testGetSession() {
		assertNotNull(MyBatisUtil.getSession().getConnection());
	}

	@Test
	public void testGetAutoCommitSession() {
		assertNotNull(MyBatisUtil.getAutoCommitSession().getConnection());
	}

}
