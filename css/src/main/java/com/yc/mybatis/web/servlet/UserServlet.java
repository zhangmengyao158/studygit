package com.yc.mybatis.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.yc.mybatis.entity.PaginationBean;
import com.yc.mybatis.entity.User;
import com.yc.mybatis.entity.UserBean;
import com.yc.mybatis.service.UserService;
import com.yc.mybatis.service.impl.UserServiceImpl;
import com.yc.mybatis.util.ServletUtil;
   
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
 
	private static final long serialVersionUID = 5130695560849273625L;
	private UserService userService;  //业务处理
	
	public UserServlet() {
		userService = new UserServiceImpl();
	}
	 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  		if(req.getRequestURI().endsWith("login")){
			doLogin(req,resp);
		}else if(req.getRequestURI().endsWith("list")){
  			doList(req,resp);
		}else if(req.getRequestURI().endsWith("modify")){
  			doModify(req,resp);
		}
	}	
	
	private void doModify(HttpServletRequest request,HttpServletResponse resp) throws IOException, ServletException {
		System.out.println("进来1啊啊");
		SmartUpload su = getSmartUpload(request, resp);
		Request req = su.getRequest();  //smartupload里的request
		
		//取上传文件数据
		File f = su.getFiles().getFile(0);
		if(!f.isMissing()){
			//做文件上传处理
 		String path = new java.io.File(getServletContext().getRealPath("/")).getParent()+"\\upload\\" + f.getFileName();
		System.out.println("上传文件的保存位置："+ path);
			try {
				f.saveAs(path, SmartUpload.SAVE_PHYSICAL);
 			} catch (SmartUploadException e) {
 				e.printStackTrace();
			}	
		}
		 User user  = getReqParam2Obj(req, User.class);
		 user.setPicPath("/upload/"+ f.getFileName());
		 System.out.println("UserServlet.modify ==>user: " + user);
		 respOutStr(resp, toJsonStr( userService.modifyUser(user)));//把对象转换成json字符串 ，作为响应数据
		
	}

	private void doList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		 String currPage = request.getParameter("page"); //分页的当前页数
	     String pageSize = request.getParameter("rows");  //当前页面的数据条数 
 		PaginationBean<User> userBean  = userService.listPartUsers(currPage, pageSize); //分页处理封装对象  封装要传递的数据		
		respOutStr(response, toJsonStr(userBean));//把对象转换成json字符串 ，作为响应数据
		
	}

	//做业务处理    参数    分发
	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
 	//1.把请求参数转换成请求对象  java是面向对象语言  更加便捷
		User user = getReqParam2Obj(request,User.class) ;  //User.class 就是一个实例
		System.out.println(user);   
		respOutStr(response,userService.login(user)+"");
		
 
	
		
	}

	 
}
