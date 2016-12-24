package com.yc.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.commons.DbHelper;

	public class SendDAO {
		DbHelper dbHelper=new DbHelper();
//		/**
//		 * 发送邮件
//		 * @param params
//		 * @return
//		 * @throws Exception
//		 */
//	 public int SENDEmail1(List<List<Object>>params) throws Exception{
//		 String sql = "insert into email values(1,?,?,?,?,sysdate,1,1,0,0,null,null,null)";
//		 String sql1="insert into files values(seq_files.nextval,?,'da',?)";
//		 List<String> sqls=new ArrayList<String>();
//		 sqls.add(sql);
//		 sqls.add(sql1);
//		
//		 return dbHelper.doUpdate(sqls, params);
//	 }
		
		/**
		 * 发送邮件
		 * @param params
		 * @param file
		 * @return
		 * @throws SQLException
		 * @throws FileNotFoundException
		 */
	public boolean sendEmail(List<Object> params) throws SQLException, FileNotFoundException{
		String sql = "insert into email values(seq_email.nextval,?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),1,1,0,0,null,null,null,null,null)";
		//String sql1="insert into files values(seq_files.nextval,?,?,?);";
		//插入基本信息
		int i = dbHelper.doUpdate(sql, params);
		if(i>0){
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * 发送群邮件
	 * @param params
	 * @param file
	 * @return
	 * @throws SQLException
	 * @throws FileNotFoundException
	 */
public boolean sendqunEmail(List<Object> params) throws SQLException, FileNotFoundException{
	String sql = "insert into email values(seq_email.nextval,?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),1,1,0,1,null,null,null,?,?)";
	//String sql1="insert into files values(seq_files.nextval,?,?,?);";
	//插入基本信息
	int i = dbHelper.doUpdate(sql, params);
	if(i>0){
		return true;
	}else{
		return false;
	}
}
	
	//获取文件发送的时间
	public Map<String,Object> findEmailID (int toid,String title) throws SQLException,IOException{
		Map<String, Object> map = new HashMap<String,Object>();
		String sql= "select email_date from email where toid=? and title=? ";//and email_date=to_date('2013-07-08','yyyy-MM-dd')";
		//params.add(email_date);
		List<Object> params=new ArrayList<Object>();
		params.add(toid);
		params.add(title);
		map = dbHelper.findSingleObject(sql, params);
		return map;
	}
		
	/**
	 * 存草稿箱
	 * @param params
	 * @param file
	 * @return
	 * @throws SQLException
	 * @throws FileNotFoundException
	 */
public boolean cuncaogaoEmail(List<Object> params) throws SQLException, FileNotFoundException{
	String sql = "insert into email values(seq_email.nextval,?,null,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),0,1,0,2,?,?,null,null,null)";
	//插入基本信息
	int i = dbHelper.doUpdate(sql, params);
	if(i>0){
		return true;
	}else{
		return false;
	}
}
	
/**
 *存群草稿箱
 * @param params
 * @param file
 * @return
 * @throws SQLException
 * @throws FileNotFoundException
 */
public boolean cunquncaogaoEmail(List<Object> params) throws SQLException, FileNotFoundException{
String sql = "insert into email values(seq_email.nextval,?,null,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),0,1,0,2,?,?,null,null,null)";
//插入基本信息
int i = dbHelper.doUpdate(sql, params);
if(i>0){
	return true;
}else{
	return false;
}
}
	
		/**
		 * 附件
		 * @param params
		 * @return
		 * @throws SQLException 
		 * @throws FileNotFoundException 
		 */
		public boolean accessory(List<Object> params) throws SQLException, FileNotFoundException{
			String sql="insert into files values(seq_files.nextval,?,?,?)";
			
			int i = dbHelper.doUpdate(sql, params);
			if(i>0){
				return true;
			}else{
				return false;
			}
			
		}
		
		/**
		  * 发送草稿箱邮件
		  * @param params
		  * @return
		  * @throws FileNotFoundException
		  * @throws SQLException
		  */
		 public boolean sendcaogaoEmail(List<Object> params) throws FileNotFoundException, SQLException{
				String sql="update email set email_send=1,partition_type=0 where emailid=?";
				int i = dbHelper.doUpdate(sql, params);
				if(i>0){
					return true;
				}else{
					return false;
				}
		 }
		 
		 /**
		  *	修改草稿箱内容
		  */
		 public boolean updatecaogaoEmail(List<Object> params) throws FileNotFoundException, SQLException{
				String sql="update email set toid=?,title=?,con=? where emailid=?";
				int i = dbHelper.doUpdate(sql, params);
				if(i>0){
					return true;
				}else{
					return false;
				}
		 }
}
