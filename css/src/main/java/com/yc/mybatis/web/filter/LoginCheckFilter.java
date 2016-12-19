package com.yc.mybatis.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;

/**
 * 校验用户登录过滤器
 * @author Administrator
 *
 */
//@WebFilter("/*")
public class LoginCheckFilter extends AbstractFilter {
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		LogManager.getLogger().debug("使用校验用户登录过滤器， 进行权限控制....");
	}
       
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		//1.过滤那些请求
		HttpServletRequest request = (HttpServletRequest) req;
		String reqUri = request.getRequestURI();
		LogManager.getLogger().debug("请求路径uri ==> " + reqUri);
		//取到session中的登录信息
		if(request.getSession().getAttribute("user") == null){
			if(reqUri.endsWith("manage.jsp") || reqUri.endsWith("user/list") || reqUri.endsWith("add.jsp")){
				//2.满足过滤过滤条件终止继续请求， 返回登录页面
				if(request.getSession().getAttribute("errorMsg") == null){
					request.setAttribute("errorMsg", "请求登录后，再来操作...");
				}
				req.getRequestDispatcher("/page/login.jsp").forward(req, resp);
				return;
			}
		}
		//3.不满足过滤过滤条件继续请求
		chain.doFilter(req, resp);
	}
}
