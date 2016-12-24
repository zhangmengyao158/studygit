package com.yc.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Display;

import com.yc.commons.DbHelper;


public class ReadDAO {
	static DbHelper dbHelper=new DbHelper();
	/**
	 * 查看收件箱邮件
	 * @param stu_id
	 * @return
	 * @throws FileNotFoundException
	 * @throws SQLException
	 */
	public List<Map<String, Object>> readEmail(int fromId) throws FileNotFoundException, SQLException{
		String sql="select fromid,title,email_date,con from email where Emailid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(fromId);
		return dbHelper.findMultiObject(sql, params);
	}
	
	/**
	 * 查看群收件箱邮件
	 * @param stu_id
	 * @return
	 * @throws FileNotFoundException
	 * @throws SQLException
	 */
	public List<Map<String, Object>> readqunEmail(int fromId) throws FileNotFoundException, SQLException{
		String sql="select fromid,toid,title,email_date,con,emailA4,emailA5 from email where Emailid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(fromId);
		return dbHelper.findMultiObject(sql, params);
	}
	
	/**
	  * 查看邮件附件
	  * @param params
	  * @return
	  * @throws FileNotFoundException
	  * @throws SQLException
	  */
	public List<Map<String, Object>> ReadFujian(List<Object> params) throws FileNotFoundException, SQLException{
	String sql="select filesid,filesname from files where emailid=?";
	return dbHelper.findMultiObject(sql, params);
	}
	
	
	/**
	 * 查看邮件是否存在
	 * @param toid
	 * @return
	 * @throws FileNotFoundException
	 * @throws SQLException
	 */
	 public Map<String, Object> findemailexist(int emailid) throws FileNotFoundException, SQLException{
		 	String sql1 ="select * from email where emailid=? and  partition_type=2";// where inbox_type=0 and partition_type=0 and email_drop=1";	
		 	List<Object> params=new ArrayList<Object>();
		 	params.add(emailid);
		 	return dbHelper.findSingleObject(sql1, params);
	 }
	 
	 /**
		 * 删除存在的草稿邮件附件
		 * @param toid
		 * @return
		 * @throws FileNotFoundException
		 * @throws SQLException
		 */
		 public Map<String, Object> deletecaogaofujian(int emailid) throws FileNotFoundException, SQLException{
			 	String sql1 ="delete files where emailid=?";// where inbox_type=0 and partition_type=0 and email_drop=1";	
			 	List<Object> params=new ArrayList<Object>();
			 	params.add(emailid);
			 	return dbHelper.findSingleObject(sql1, params);
		 }
	 
	 /**
		 * 删除存在的草稿邮件
		 * @param toid
		 * @return
		 * @throws FileNotFoundException
		 * @throws SQLException
		 */
		 public Map<String, Object> deletecaogao(int emailid) throws FileNotFoundException, SQLException{
			 	String sql1 ="delete email where emailid=?";// where inbox_type=0 and partition_type=0 and email_drop=1";	
			 	List<Object> params=new ArrayList<Object>();
			 	params.add(emailid);
			 	return dbHelper.findSingleObject(sql1, params);
		 }
	 
	
	/**
	  * 查看邮件附件
	  * @param params
	  * @return
	  * @throws FileNotFoundException
	  * @throws SQLException
	  */
	public List<Map<String, Object>> ReadFujianname(List<Object> params) throws FileNotFoundException, SQLException{
	String sql="select filesid,filesname from files where emailid=?";
	return dbHelper.findMultiObject(sql, params);
	
	}
	 
	 /**
	  * 查看已发送邮件
	  * @param fromId
	  * @return
	  * @throws FileNotFoundException
	  * @throws SQLException
	  */
	 public List<Map<String, Object>> readyifasongEmail(int fromId) throws FileNotFoundException, SQLException{
			String sql="select toid,title,email_date,con from email where emailid=?";
			
			List<Object> params=new ArrayList<Object>();
			params.add(fromId);
			return dbHelper.findMultiObject(sql, params);
			
		}
	 
	 
	 
	 
	 /**
	  * 读取图片
	  * @param id
	  * @return
	  * @throws FileNotFoundException
	  * @throws SQLException
	  */
	 public static Image selectimage(int id) throws FileNotFoundException, SQLException{
			String sql="select uPicturePath from userinfo where userId=?";
			List<Object> params= new ArrayList<Object>();
			params.add(id);
			InputStream in=	dbHelper.selectImg(sql, params);
			if(in==null){
				return null;
			}
			
			Image image =new Image(Display.getDefault(), in);
			return image;		
		}
//			Image image2=null;
//			image2=MainDAO.selectimage(ids);
//			ImageData imageData=image2.getImageData();
//			ImageData img=imageData.scaledTo(25, 25);
//			treeItem.setImage(new Image(Display.getDefault(), img));
	 
	 /**
		 * 查看草稿箱邮件
		 * @param stu_id
		 * @return
		 * @throws FileNotFoundException
		 * @throws SQLException
		 */
		public Map<String, Object> readcaogaoEmail(int Emailid) throws FileNotFoundException, SQLException{
			String sql="select title,EmailA2,con,EmailA1 from email where Emailid=?";
			List<Object> params=new ArrayList<Object>();
			params.add(Emailid);
			return dbHelper.findSingleObject(sql, params);
		}
		
		//查看邮件已读或未读状态
		public Map<String, Object> Emailreadtype(int emailid,int toid) throws FileNotFoundException, SQLException{
			String sql="select inbox_type from email where Emailid=? and toid=? ";
			List<Object> params=new ArrayList<Object>();
			params.add(emailid);
			params.add(toid);
//			return dbHelper.findMultiObject(sql, params);
			return dbHelper.findSingleObject(sql, params);
		}
		
		//查看群邮件已读或未读状态
		public Map<String, Object> EmailQunreadtype(int emailid,int toid) throws FileNotFoundException, SQLException{
			String sql="select inbox_type from email where Emailid=? and toid=? ";
			List<Object> params=new ArrayList<Object>();
			params.add(emailid);
			params.add(toid);
//			return dbHelper.findMultiObject(sql, params);
			return dbHelper.findSingleObject(sql, params);
		}
		
		
		//查看群邮件已读或未读状态
//				public Map<String, Object> qunEmailreadtype(int emailid,int toid) throws FileNotFoundException, SQLException{
		public Map<String, Object> qunEmailreadtype(int emailid) throws FileNotFoundException, SQLException{
				String sql="select inbox_type from email where Emailid=?";
				List<Object> params=new ArrayList<Object>();
				params.add(emailid);
//				return dbHelper.findMultiObject(sql, params);
				return dbHelper.findSingleObject(sql, params);
			}
		
		//将邮件从未读改为已读
		//----邮件类型   0未读,1已读
			 public boolean index_type(int emailid) throws FileNotFoundException, SQLException{
				 	String sql1 ="update email set inbox_type=1 where emailid=?";	
				 	List<Object> params=new ArrayList<Object>();
				 	params.add(emailid);
				 	dbHelper.doUpdate(sql1,params);
			    return false;
			 }
	 
		
		//----邮件状态类型  0 表示为个人邮件  1表示群邮件  2表示草稿箱 3表示为垃圾邮件
		
		//将邮件状态从个人邮件改为群邮件
			 public boolean partition_type1(String sql,List<Object> params){
				 	String sql2 ="update email set partition_type =1 where emailId =? ";	
			    return false;
			 }
			 
			//将邮件状态从个人邮件改为草稿箱
			 public boolean partition_type2(String sql,List<Object> params){
				 	String sql3 ="update email set partition_type =2 where emailId =?";	
			    return false;
			 }
			 
			//将邮件状态从个人邮件改为垃圾邮件
			 public boolean partition_type3(String sql,List<Object> params){
				 	String sql4 ="update email set partition_type =3 where emailId =? ";	
			    return false;
			 }
			 
			 
			//将邮件从收件箱将邮件移到已删除
			//--是否删除  0 表示删除 1表示未删除 
			 public boolean email_drop(int emailid) throws FileNotFoundException, SQLException{
				 	String sql1 ="update email set email_drop =0 where emailId =? ";	
				 	List<Object> params=new ArrayList<Object>();
				 	params.add(emailid);
				 	dbHelper.doUpdate(sql1,params);
			    return false;
			 }
			 
			 
			 //个人邮件 	标记为已读 
			 //--是否删除  0已读 	1表示未读
			 public boolean updateyidu(int toid) throws FileNotFoundException, SQLException{
				 	String sql1 ="update email set inbox_type=1 where partition_type=0 and email_drop=1 and toid=?";// where inbox_type=0 and partition_type=0 and email_drop=1";	
				 	List<Object> params=new ArrayList<Object>();
				 	params.add(toid);
				 	int i =dbHelper.doUpdate(sql1,params);
					if(i>0){
						return true;
					}else{
						return false;
					}
			 }
			 
			//群邮件 	标记为已读 
			 //--是否删除  0已读 	1表示未读
			 public boolean updatequnyidu(int toid) throws FileNotFoundException, SQLException{
				 	String sql1 ="update email set inbox_type=1 where partition_type=1 and email_drop=1 and toid=?";// and fromid=?";// where inbox_type=0 and partition_type=0 and email_drop=1";	
				 	List<Object> params=new ArrayList<Object>();
				 	params.add(toid);
				 	int i =dbHelper.doUpdate(sql1,params);
					if(i>0){
						return true;
					}else{
						return false;
					}
			 }
			 
			//个人邮件 	标记为已读 
			 //--是否删除  0已读 	1表示未读
			 public boolean updatelajiyidu(int toid) throws FileNotFoundException, SQLException{
				 	String sql1 ="update email set inbox_type=1 where partition_type=3 and email_drop=1 and toid=?";// where inbox_type=0 and partition_type=0 and email_drop=1";	
				 	List<Object> params=new ArrayList<Object>();
				 	params.add(toid);
				 	int i =dbHelper.doUpdate(sql1,params);
					if(i>0){
						return true;
					}else{
						return false;
					}
			 }
	
}
