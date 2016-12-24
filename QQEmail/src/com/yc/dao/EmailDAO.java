package com.yc.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.ui.internal.intro.IIntroConstants;

import com.yc.commons.DbHelper;

public class EmailDAO {
	DbHelper dbHelper=new DbHelper();
	//用户登录
	public Map<String , Object> login(int userId,String userPwd) throws SQLException, FileNotFoundException{
		Map<String , Object> map=new HashMap<String, Object>();
		String sql1="select * from userInfo where userid=? and userpwd=?";
		List<Object> params=new ArrayList<Object>();
		params.add(userId);
		params.add(userPwd);
		dbHelper.findSingleObject(sql1, params);
		return map;
	}
	
	/**
	 * 获取用户名
	 * @param stu_id
	 * @return
	 * @throws FileNotFoundException
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findUserName(int userid) throws FileNotFoundException, SQLException{
		String sql="select username from userInfo where userid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(userid);
		return dbHelper.findMultiObject(sql, params);
		
	}
	
	 
	 /**
	  * 未读邮件数量
	  * @param params
	  * @return
	  * @throws FileNotFoundException
	  * @throws SQLException
	  */
	 public List<Map<String , Object>> weiduCount(int fromid) throws FileNotFoundException, SQLException{
			String sql="select count(emailid) count1 from email where inbox_type=0 and toid=?";
			List<Object> params=new ArrayList<Object>();
			params.add(fromid);
			return dbHelper.findMultiObject(sql, params);
		}
	 
	 
	 
		
		/**
		 * 查看附件
		 * @param className
		 * @return
		 * @throws FileNotFoundException
		 * @throws SQLException
		 */
		public List<Map<String , Object>> FindFujian(int emailid) throws FileNotFoundException, SQLException{
			String sql="select newFile from files where emailid=?";
			List<Object> params=new ArrayList<Object>();
			params.add(emailid);
			return dbHelper.findMultiObject(sql, params);
		}
		
		/**
		 * caogao查询邮件id
		 * @param personId
		 * @return
		 * @throws SQLException
		 * @throws IOException
		 */
		public Map<String,Object> caogaofindEmailID (List<Object> params) throws SQLException,IOException{
			Map<String, Object> map = new HashMap<String,Object>();
			String sql= "select emailid from email where toid=? ";//and title=?";//and email_date=to_date('2013-07-08','yyyy-MM-dd')";
			//params.add(email_date);
			map = dbHelper.findSingleObject(sql, params);
			return map;
		}
		
		/**
		 * caogao时间查询邮件id
		 * @param personId
		 * @return
		 * @throws SQLException
		 * @throws IOException
		 */
		public Map<String,Object> DatecaogaofindEmailID () throws SQLException,IOException{
			Map<String, Object> map = new HashMap<String,Object>();
			String sql= "select emailid from email where email_date=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')";//and title=?";//and email_date=to_date('2013-07-08','yyyy-MM-dd')";
			//params.add(email_date);
			map = dbHelper.findSingleObject(sql, null);
			return map;
		}
		
		/**
		 * caogao查询邮件id
		 * @param personId
		 * @return
		 * @throws SQLException
		 * @throws IOException
		 */
		public Map<String,Object> findEmailID (List<Object> params) throws SQLException,IOException{
			Map<String, Object> map = new HashMap<String,Object>();
			String sql= "select emailid from email where toid=? and partition_type=0";//and email_date=to_date('2013-07-08','yyyy-MM-dd')";
			//params.add(email_date);
			map = dbHelper.findSingleObject(sql, params);
			return map;
		}
		
		
		/**
		 * 删除发送失败的邮件
		 * @param params
		 * @return
		 * @throws FileNotFoundException
		 * @throws SQLException
		 */
		public boolean delfailEmail(List<Object> params) throws FileNotFoundException, SQLException{
			String sql="delete email where emailid=?";
			int i = dbHelper.doUpdate(sql, params);
			if(i>0){
				return true;
			}else{
				return false;
			}
		}
		
		

		 /**
		  * 收件箱
		  * @param params
		  * @return
		  * @throws FileNotFoundException
		  * @throws SQLException
		  */
		 public List<Map<String , Object>> shoujianxiang(List<Object> params) throws FileNotFoundException, SQLException{
			String sql="select emailid,fromid,title,email_date from email where toid=? and partition_type=0 and email_drop=1";
			return dbHelper.findMultiObject(sql, params);
		}
		 
		 //查询当前用户所加的群
		 public List<Map<String , Object>> FindUserQunid(List<Object> params) throws FileNotFoundException, SQLException{
//				String sql="select emailid,toid,title,email_date from email where fromid=? and partition_type=1";
				 String sql="select distinct f.flockid from flock f inner join flock_info fi on f.flockid=fi.flockid where userId=?";
				return dbHelper.findMultiObject(sql, params);
		}
			 
		 /**
		  * 群收件箱
		  * @param params
		  * @return
		  * @throws FileNotFoundException
		  * @throws SQLException
		  */
		 public List<Map<String , Object>> Qunshoujian(int toid) throws FileNotFoundException, SQLException{
//			String sql="select emailid,toid,title,email_date from email where fromid=? and partition_type=1";
			 String sql="select distinct emailid,emailA4,title,email_date from email where toid=? and partition_type=1 and email_drop=1";
			 List<Object> params =new ArrayList<Object>();
			 params.add(toid);
//			 params.add(fromid);
			return dbHelper.findMultiObject(sql, params);
		}
		 
//		 //利用邮件id查询群号
//		 public List<Map<String , Object>> Qunshoujian(int emailid) throws FileNotFoundException, SQLException{
////				String sql="select emailid,toid,title,email_date from email where fromid=? and partition_type=1";
//				 String sql="select emailid,title,email_date from email where toid=? and fromid=?";
//				 List<Object> params =new ArrayList<Object>();
//				 params.add(toid);
//				 params.add(fromid);
//				return dbHelper.findMultiObject(sql, params);
//			}
		 
		 
//		 /**
//		  * 群收件箱
//		  * @param params
//		  * @return
//		  * @throws FileNotFoundException
//		  * @throws SQLException
//		  */
//		 public List<Map<String , Object>> qunshoujianxiang(List<Object> params) throws FileNotFoundException, SQLException{
////			String sql="select emailid,toid,title,email_date from email where fromid=? and partition_type=1";
//			 String sql="select emailid,fromid,title,email_date from email where toid=(select toid from email where partition_type=1"
//                                +"intersect select  f.flockid from flock f inner join flock_info fi on f.flockid=fi.flockid where userId=?)";
//			return dbHelper.findMultiObject(sql, params);
//		}
//		 
		 //查询qq群id
		 public Map<String , Object> qunID(int emailid) throws FileNotFoundException, SQLException{
			 String sql="select toid from email where emailid=?";
			 List<Object> params=new ArrayList<Object>();
			 params.add(emailid);
			 return dbHelper.findSingleObject(sql, params);
		 }
		 
		 //查询qq群名称
		 public Map<String , Object> qunName(int flockid) throws FileNotFoundException, SQLException{
			 String sql="select flockname from flock where flockid=?";
			 List<Object> params=new ArrayList<Object>();
			 params.add(flockid);
			 return dbHelper.findSingleObject(sql, params);
		 }
		 
//		 /**
//		  * 好友列表
//		  * @param params
//		  * @return
//		  * @throws FileNotFoundException
//		  * @throws SQLException
//		  */
//		 public List<Map<String , Object>> tongxunlu(List<Object> params) throws FileNotFoundException, SQLException{
//			String sql="select u.username,p.personid,p.personname,p.persontype from userInfo u inner join user_person p on u.userid=p.userid where userid=?";
//			return dbHelper.findMultiObject(sql, params);
//		}
		 
		 
		 /**
		  * 已删除
		  * @param params
		  * @return
		  * @throws FileNotFoundException
		  * @throws SQLException
		  */
		 public List<Map<String , Object>> yishanchu(List<Object> params) throws FileNotFoundException, SQLException{
			String sql="select emailid,fromid,title,email_date from email where toid=? and email_drop=0 ";
			return dbHelper.findMultiObject(sql, params);
		}
		 
		 /**
		  * 垃圾箱
		  * @param params
		  * @return
		  * @throws FileNotFoundException
		  * @throws SQLException
		  */
		 public List<Map<String , Object>> lajixiangEmail(List<Object> params) throws FileNotFoundException, SQLException{
			String sql="select emailid,fromid,title,email_date from email where toid=? and partition_type=3 and email_drop=1";
			return dbHelper.findMultiObject(sql, params);
		}
		 
		 
		 
		 /**
		  * 草稿箱
		  * @param params
		  * @return
		  * @throws FileNotFoundException
		  * @throws SQLException
		  */
		 public List<Map<String , Object>> caogaoxiang(List<Object> params) throws FileNotFoundException, SQLException{
			String sql="select emailid,toid,title,email_date,emailA1,emailA2 from email where fromid=? and partition_type=2 and email_send=0 and email_drop=1";
			return dbHelper.findMultiObject(sql, params);
		}
		 
		 /**
		  * 删除草稿箱邮件
		  * @param params
		  * @return
		  * @throws FileNotFoundException
		  * @throws SQLException
		  */
		 public boolean deletecaogaoxiang(int emailid) throws FileNotFoundException, SQLException{
			String sql="update email set email_drop=2 where partition_type=2 and emailid=?";
			List<Object> params=new ArrayList<Object>();
			params.add(emailid);
			int i = dbHelper.doUpdate(sql, params);
			if(i>0){
				return true;
			}else{
				return false;
			}
		}
		 
		 /**
		  * 查看邮件
		  * @param params
		  * @return
		  * @throws FileNotFoundException
		  * @throws SQLException
		  */
		 public boolean readEmail(List<Object> params) throws FileNotFoundException, SQLException{
				String sql="update email set inbox_type=1 where emailid=？;";
				int i = dbHelper.doUpdate(sql, params);
				if(i>0){
					return true;
				}else{
					return false;
				}
		 }
		 
		 /**
		  * 删除邮件
		  * @param params
		  * @return
		  * @throws FileNotFoundException
		  * @throws SQLException
		  */
		 public boolean shanchuEmail(int shanchuid) throws FileNotFoundException, SQLException{
				String sql="update email set email_drop=0 where emailid=?";
				List<Object> shanchu=new ArrayList<Object>();
				shanchu.add(shanchuid);
				int i = dbHelper.doUpdate(sql, shanchu);
				if(i>0){
					return true;
				}else{
					return false;
				}
		 }
		 /**
		  * 恢复删除邮件
		  * @param params
		  * @return
		  * @throws FileNotFoundException
		  * @throws SQLException
		  */
		 public boolean huifushanchuEmail(int shanchuid) throws FileNotFoundException, SQLException{
				String sql="update email set email_drop=1 where emailid=?";
				List<Object> shanchu=new ArrayList<Object>();
				shanchu.add(shanchuid);
				int i = dbHelper.doUpdate(sql, shanchu);
				if(i>0){
					return true;
				}else{
					return false;
				}
		 }
		 
		 /**
		  * 彻底删除邮件
		  * @param params
		  * @return
		  * @throws FileNotFoundException
		  * @throws SQLException
		  */
		 public boolean SuperdeleteEmail(List<Object> params) throws FileNotFoundException, SQLException{
				String sql="delete email where emailid=?";
				int i = dbHelper.doUpdate(sql, params);
				if(i>0){
					return true;
				}else{
					return false;
				}
		 }
		 
		 
		 
		
		 
		 /**
		  * 已发送
		  * @param params
		  * @return
		  * @throws FileNotFoundException
		  * @throws SQLException
		  */
		 public List<Map<String, Object>> yifasong(List<Object> params) throws FileNotFoundException, SQLException{
				String sql="select emailid,toid,title,email_date from email where fromId=? and email_send=1 and partition_type=0";
				return dbHelper.findMultiObject(sql, params);
			}
		 
		 /**
		  * 删除已发送
		  * @param params
		  * @return
		  * @throws FileNotFoundException
		  * @throws SQLException
		  */
		 public boolean dropyifasong(int emailid) throws FileNotFoundException, SQLException{
				String sql="update email set email_send=2 where emailid=?";
				List<Object> params=new ArrayList<Object>();
				params.add(emailid);
				int i = dbHelper.doUpdate(sql, params);
				if(i>0){
					return true;
				}else{
					return false;
				}
		 }
		 
		 
		//将邮件从收件箱将邮件移到已删除
			//--是否删除  0 表示删除 1表示未删除
		 public boolean email_drop(String sql,List<Object> params){
			 String sql1 ="update email set email_drop =0 where emailId =? ";	
			 return false;
		}
		 
		 
		//将邮件从收件箱将邮件移到已删除
		//--是否删除  0 表示删除 1表示未删除
		public boolean email_drop(List<Object> params) throws FileNotFoundException, SQLException{
			String sql1 ="update email set email_drop =0 where emailId =? ";
			dbHelper.doUpdate(sql1, params);
			return false;
		}
		
		//彻底删除
		//--是否删除  0 表示删除 1表示未删除 2彻底删除
		public boolean chedidrop(int emailid) throws FileNotFoundException, SQLException{
			String sql1 ="update email set email_drop =2 where emailId =? ";
			List<Object> params=new ArrayList<Object>();
			params.add(emailid);
			int i = dbHelper.doUpdate(sql1, params);
			if(i>0){
				return true;
			}else{
				return false;
			}
		}
		
		/**
		 * 垃圾箱查询带有垃圾词汇的邮件
		 * @param personId
		 * @return
		 * @throws SQLException
		 * @throws IOException
		 */
		public Map<String,Object> lajixiangExist(int emailid) throws SQLException,IOException{
			String sql= "select * from email where con like '%fuck%' and emailid= ?";
			List<Object> params=new ArrayList<Object>();
			params.add(emailid);
			return dbHelper.findSingleObject(sql, params);
		}
		
		
		/**
		 * 修改邮件状态为垃圾邮件
		 * @param params
		 * @return
		 * @throws FileNotFoundException
		 * @throws SQLException
		 */
		public boolean type_laji(int emailid) throws FileNotFoundException, SQLException{
			String sql1 ="update email set partition_type =3 where emailId =? ";
			List<Object>params=new ArrayList<Object>();
			params.add(emailid);
			int i = dbHelper.doUpdate(sql1, params);
			if(i>0){
				return true;
			}else{
				return false;
			}
		}
		
		//////////////////////////////////////////////////////////////////////////////////
		/*************************************群邮件 *************************************/
		/** 
		 * 查询所有群成员
		* @throws SQLException 
		* @throws FileNotFoundException
		* 
		* */
		public List<Map<String , Object>> findQunUser(int flockid) throws FileNotFoundException, SQLException{
			String sql="select distinct fi.userid from flock f inner join flock_info fi on f.flockid=fi.flockid where f.flockid=?";
			List<Object> params=new ArrayList<Object>();
			params.add(flockid);
			return dbHelper.findMultiObject(sql, params);
		}
		
		
		/**
		 * 判断是否属于这个群
		 * @param userid
		 * @param flockid
		 * @return
		 * @throws FileNotFoundException
		 * @throws SQLException
		 */
		public Map<String , Object> findisexist(int userid,int flockid) throws FileNotFoundException, SQLException{
			String sql="select distinct * from flock_info where userid=? and flockid=?";
			List<Object> params=new ArrayList<Object>();
			params.add(userid);
			params.add(flockid);
			return dbHelper.findSingleObject(sql, params);
		}
		
		//////////////////////////////////////////////////////////////////////////
		 /**
		  *   通讯录
		  * @param params
		  * @return
		  * @throws FileNotFoundException
		  * @throws SQLException
		  */
		 public List<Map<String , Object>> addressbook(int userid) throws FileNotFoundException, SQLException{
			String sql="select personId,personName,personType from user_person where userId=?";
			List<Object> params=new ArrayList<Object>();
			params.add(userid);
			return dbHelper.findMultiObject(sql, params);
		}
		 
		 /**
		  *   根据用户名获取账号
		  * @param params
		  * @return
		  * @throws FileNotFoundException
		  * @throws SQLException
		  */
		 public Map<String , Object> finduserid(int userid,String userName) throws FileNotFoundException, SQLException{
			String sql="select personid from user_person where userid=? and personname=?";
			List<Object> params=new ArrayList<Object>();
			params.add(userid);
			params.add(userName);
			return dbHelper.findSingleObject(sql, params);
		}
		 
		 
		 /*
		  * 查询好友名称
		  */
		public Map<String , Object> findFriendName(int personId) throws FileNotFoundException, SQLException{
			String sql="select username,tel from Userinfo where userid=?";
			List<Object> params=new ArrayList<Object>();
			params.add(personId);
			return dbHelper.findSingleObject(sql, params);
			
		}
		
		/**
		 * 查询好友备注和类型
		 * @param personid
		 * @return
		 * @throws FileNotFoundException
		 * @throws SQLException
		 */
		public Map<String , Object> findperson(int personid) throws FileNotFoundException, SQLException{
			String sql="select personname,persontype from user_person where personid=?";
			List<Object> params=new ArrayList<Object>();
			params.add(personid);
			return dbHelper.findSingleObject(sql, params);
		}
		
		/**
		 * 
		 * 删除通讯录好友
		 */
		public boolean deletefriend(int personId) throws FileNotFoundException, SQLException{
			String sql1 ="delete user_person where personid=? ";
			List<Object> params=new ArrayList<Object>();
			params.add(personId);
			int i = dbHelper.doUpdate(sql1, params);
			if(i>0){
				return true;
			}else{
				return false;
			}
		}
		
		/**
		 * 修改好友 信息
		 * @param personname
		 * @param persontype
		 * @param personid
		 * @return
		 * @throws FileNotFoundException
		 * @throws SQLException
		 */
		public boolean updateperson(String personname,String persontype,int personid) throws FileNotFoundException, SQLException{
			String sql1 ="update user_person set personname=?,persontype=? where personid=?";
			List<Object>params=new ArrayList<Object>();
			params.add(personname);
			params.add(persontype);
			params.add(personid);
			int i = dbHelper.doUpdate(sql1, params);
			if(i>0){
				return true;
			}else{
				return false;
			}
		}
		
		/**
		 * 通讯录 添加好友
		 * @param params
		 * @return
		 * @throws SQLException 
		 * @throws FileNotFoundException 
		 */
		public boolean addfriends(List<Object> params) throws SQLException, FileNotFoundException{
			String sql="insert into user_person values(?,?,?,?)";
			int i = dbHelper.doUpdate(sql, params);
			if(i>0){
				return true;
			}else{
				return false;
			}
			
		}
		
		/////////////////////////////////////////////////////////////////////////////////
		/**
		 * 查询群邮件名称
		 * @param userid
		 * @return
		 * @throws FileNotFoundException
		 * @throws SQLException
		 */
		public List<Map<String , Object>> findqunName(int userid) throws FileNotFoundException, SQLException{
			String sql="select f.flockname from flock f inner join flock_info fi on f.flockid=fi.flockid where fi.userid=?";
			List<Object> params=new ArrayList<Object>();
			params.add(userid);
			return dbHelper.findMultiObject(sql, params);
		}
		
		/**
		 * 根据群名称查询群id
		 * @param userid
		 * @return
		 * @throws FileNotFoundException
		 * @throws SQLException
		 */
		public Map<String , Object> findqunid(String flockname) throws FileNotFoundException, SQLException{
			String sql="select flockid from flock where flockname=?";
			List<Object> params=new ArrayList<Object>();
			params.add(flockname);
			return dbHelper.findSingleObject(sql, params);
		}
		
		public Map<String , Object> finduserid(String username) throws FileNotFoundException, SQLException{
			String sql="select userid from userInfo where username=?";
			List<Object> params=new ArrayList<Object>();
			params.add(username);
			return dbHelper.findSingleObject(sql, params);
		}
}
