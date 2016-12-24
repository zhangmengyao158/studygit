package com.yc.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.sql.BLOB;


public class DbHelper {
    static{
    	//��������  ����ϵͳ��ֻ��Ҫ����һ�ε�JVM�м���
    	try {
			Class.forName(MyProperties.getInstance().getProperty("driverClass"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //��ȡ���ݿ����Ӷ���
    public  Connection getConn(){
    	Connection conn= null;
    	try {
			conn =DriverManager.getConnection(MyProperties.getInstance().getProperty("url"),MyProperties.getInstance());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return conn;
    }
    
    
    //�ر����ݿ�����
    public void closeAlll(ResultSet rs,PreparedStatement pstmt,Connection conn){
    	if(null!=rs){
    	try {
    		
			rs.close();
    		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	 }
   	 //�ڰ˲� �ر�������
    if(null!=pstmt){
   	 try {
		pstmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   	 //�ھŲ�  �ر����Ӷ���
    
    if(null!=conn){
   	 try {
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }	
}
 
    //关闭所有
    public void closeAll(ResultSet rs,Statement stmt,Connection conn){
    	if(null!=rs){
    	try {
    		
			rs.close();
    		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	 }
   	 //
    if(null!=stmt){
   	 try {
		stmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
    
    if(null!=conn){
   	 try {
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
   }	
}
    
    
    
    
    private void setParams(PreparedStatement pstmt,List<Object> params) throws SQLException, FileNotFoundException{
    	if(null!=params&&params.size()>0){
    		for(int i=0;i<params.size();i++){
    			if(params.get(i) instanceof File){
    				File file=(File)params.get(i);//强转为文件对象
    				InputStream in=new FileInputStream(file);//转为输入流对象
    				pstmt.setBinaryStream(i+1, in, (int)file.length());
    			}else{
    				pstmt.setString(i+1, params.get(i).toString());
    			}
    			
    		}
    	}
    	
    }
    
//
    public int doUpdate(String sql,List<Object> params) throws SQLException, FileNotFoundException{
    	
    	Connection conn = null ;
    	PreparedStatement pstmt=null;
    	int result=-1;
    	try{
    	conn=this.getConn();
    	conn.setAutoCommit(true);
    	pstmt=conn.prepareStatement(sql);
    	setParams(pstmt, params);//
    	result=pstmt.executeUpdate();
    	conn.commit();
    	}finally{
    		this.closeAll(null, pstmt, conn);
    	}
    	return result;
    	
    }
    
  //��ѯ������¼  
    public List<Map<String, Object>> findMultiObject(String sql, List <Object> params) throws SQLException, FileNotFoundException{
    	List<Map<String, Object>> list=new ArrayList <Map<String,Object>>();
    	Connection conn = null;
    	PreparedStatement pstmt=null;
    	ResultSet rs=null;
    	Map<String,Object> map =null;
    	conn=this.getConn();
    	conn.setAutoCommit(true);
    	
    	try {
			pstmt = conn.prepareStatement(sql);
			this.setParams(pstmt, params);
			rs=pstmt.executeQuery();
			List<String> columnNames = this.getAllColumnNames(rs);
			while(rs.next()){
				map=new HashMap<String, Object>();
				for (String cn:columnNames) {
					map.put(cn, rs.getObject(cn));
				}
				list.add(map);
			}
			conn.commit();
		}finally{
			this.closeAll(rs, pstmt, conn);
		}
    	return list;
    	
    }
    
    
    public Map<String, Object> findSingleObject(String sql,List<Object> params) throws SQLException, FileNotFoundException{
    	Connection conn= null ;
    	PreparedStatement pstmt=null;
    	ResultSet rs = null;
    	Map<String, Object> map=null;
    	try {
    		conn=this.getConn();
    		conn.setAutoCommit(true);
    		conn.commit();
    		pstmt=conn.prepareStatement(sql);
			this.setParams(pstmt, params);
			rs = pstmt.executeQuery();
			List<String> columnNames = this.getAllColumnNames(rs);
			if(rs.next()){
				map = new HashMap<String, Object>();
				for(String cn : columnNames){
					map.put(cn, rs.getString(cn));
				}
			}
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAll(rs, pstmt, conn);
		}
    	
    
    	this.closeAll(rs, pstmt, conn);
   
    	return map;
 }  
    
    private List<String> getAllColumnNames(ResultSet rs) throws SQLException{
    	List<String> columnNames=new ArrayList<String>();
    	if(null!=rs){
    		for(int i= 0; i<rs.getMetaData().getColumnCount();i++){
    			columnNames.add(rs.getMetaData().getColumnName(i+1));
    		}
    	}
    	return columnNames;
    }
    
    //批处理
    public int doUpdate(List<String> sqls,List<List<Object>> params) throws Exception{
    	Connection conn = null;
    	PreparedStatement pstmt=null;
    	int result = -1;
    	try {
    		conn=this.getConn();
			conn.setAutoCommit(false);//�ر��Զ��ύ����
			if(null!=sqls && sqls.size()>0){
				
    		for(int i = 0 ;i<sqls.size();i++){
    			String sql=sqls.get(i);
    			pstmt = conn.prepareStatement(sql);
    			this.setParams(pstmt, params.get(i));
    			result=pstmt.executeUpdate();	
    		}
    	}
			conn.commit();//�ύ����
	} catch (Exception e) {
		//�ع�
			conn.rollback();
			throw e;
		}finally{
			conn.setAutoCommit(true);//�ָ��ֳ�
			this.closeAll(null, pstmt, conn);
		}
    	return result;
    }
    
    
    public int UpdateImg(String sql, int id, File file) throws FileNotFoundException {
		FileInputStream in = new FileInputStream(file);
		Connection conn =null;
		PreparedStatement pstmt=null;
		int result=0;
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setBinaryStream(1, in, (int)file.length());
			pstmt.setInt(2, id);
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeAlll(null, pstmt, conn);
		}
		return result;
	}

    
    
    public InputStream selectImg(String sql,List<Object> params) throws FileNotFoundException{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;	
		InputStream inputStream=null;
		try {
			conn=getConn();
			pstmt=conn.prepareStatement(sql);
			this.setParams(pstmt,params);
			rs=pstmt.executeQuery();
			if(rs.next()){
				BLOB blob=(BLOB)rs.getBlob(1);
			   inputStream =blob.getBinaryStream();
			   return inputStream;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputStream;
	}
	

	
}