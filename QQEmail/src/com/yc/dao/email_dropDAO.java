package com.yc.dao;

import java.util.List;

import com.yc.commons.DbHelper;

public class email_dropDAO {

	DbHelper db = new DbHelper();

	//将邮件从收件箱将邮件移到已删除
	//--是否删除  0 表示删除 1表示未删除
		 public boolean email_drop(List<Object> params){
			 	String sql1 ="update email set email_drop =0 where emailId =? ";	
			 	
		    return false;
		 }
}
