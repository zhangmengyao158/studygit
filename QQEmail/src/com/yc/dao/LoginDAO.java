package com.yc.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.commons.DbHelper;

public class LoginDAO {
	DbHelper db = new DbHelper();
	//登录
	public Map<String, Object> login (int userid , String pwd) throws SQLException,IOException{
		
		Map<String, Object> map = new HashMap<String,Object>();
		String sql= "select * from userInfo where userid=? and userpwd=?";
		List<Object> params = new ArrayList<Object>();
		params.add(userid);
		params.add(pwd);
		map = db.findSingleObject(sql, params);
		return map;
	}
	
	/**
	 * 找回密码
	 * @param userid
	 * @param newpwd
	 * @param answer
	 * @return
	 * @throws SQLException
	 * @throws FileNotFoundException
	 */
	public boolean FindPwd(int userid,String newpwd,String answer) throws SQLException, FileNotFoundException{
		String sql = "update userinfo set userpwd=? where userid=? and answer=?";
		//String sql1="insert into files values(seq_files.nextval,?,?,?);";
		//插入基本信息
		List<Object> params = new ArrayList<Object>();
		params.add(newpwd);
		params.add(userid);
		params.add(answer);
		
		int i = db.doUpdate(sql, params);
		if(i>0){
			return true; 
		}else{
			return false;
		}
	}
	

	/**
	 * 注册
	 * @param params
	 * @return
	 * @throws SQLException
	 * @throws FileNotFoundException
	 */
	public boolean Register(List<Object> params) throws SQLException, FileNotFoundException{
		String sql = "insert into userInfo values(seq_userInfo.nextval,?,?,?,?,?,?,'你的爸爸是谁？',?,null,null,null)";
		//String sql1="insert into files values(seq_files.nextval,?,?,?);";
		//插入基本信息
		int i = db.doUpdate(sql, params);
		if(i>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 *	查看注册账号
	 * @param name
	 * @param idCard
	 * @return
	 * @throws FileNotFoundException
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findRegisterID(String name,String idCard) throws FileNotFoundException, SQLException{
		String sql="select userid from userInfo where username=? and idCard=?";
		List<Object> params=new ArrayList<Object>();
		List<Map<String, Object>> list=new ArrayList <Map<String,Object>>();
		params.add(name);
		params.add(idCard);
		list=db.findMultiObject(sql, params);
		return list;
	}
	
}
