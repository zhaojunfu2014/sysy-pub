package com.zjf.cms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zjf.cms.entity.Staff;
import com.zjf.cms.util.PrivilegeUtil;
/**
 * Ȩ��������
 * @author �Կ���
 *
 * Aug 24, 2012
 */
public class PrivilegeInterceptor extends HandlerInterceptorAdapter {
	private Log log = LogFactory.getLog(getClass());
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String path =request.getRequestURI();
		String contextPath = request.getContextPath();
		path = path.replace(contextPath, "");
		log.debug(path);
		
		PrivilegeUtil util = new PrivilegeUtil(request);
		Staff loginUser = null;
		/*
		 * ��̨����
		 */
		if(path.startsWith("/admin")){
			/*
			 * �ж��Ƿ��¼
			 */
			if(util.isLogin()){
				loginUser = util.getLoginUser();
				String userPrivilege = loginUser.getPrivilege();
				util = new PrivilegeUtil(path,userPrivilege,request);
				/*
				 * Ȩ�޲���
				 */
				if(!util.check()){
					request.getRequestDispatcher("/miss.jsp").forward(request, response);
					return false;
				}
			}else{
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				return false;
			}
			
			
			//request.getRequestDispatcher("/miss.jsp").forward(request, response);
		}
		return true;
	}
}
