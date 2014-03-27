package com.zjf.cms.controllor;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zjf.cms.dao.StaffDao;
import com.zjf.cms.entity.Staff;
import com.zjf.cms.util.MD5Util;
import com.zjf.cms.util.PrivilegeUtil;

/**
 * 登录控制器
 * @author 赵俊夫
 *
 * Aug 27, 2012
 */
@Controller
@RequestMapping("/login")
public class LoginControllor {
	@Resource(name="staffDao")
	private StaffDao staffDao;
	private Log log = LogFactory.getLog(getClass());
	@RequestMapping(method=RequestMethod.POST)
	public String login(String username,String password,String checkcode1,HttpServletRequest req,HttpServletResponse rep,Model model) throws ServletException, IOException{
		String checkcode = (String) (req.getSession().getAttribute("checkcode"));
		log.debug(checkcode1==null);
		log.debug(checkcode==null);
		/*
		 * 验证码不一致
		 */
		if(checkcode1==null ||!checkcode1.toLowerCase().equals(checkcode.toLowerCase())){
			req.setAttribute("codemessage", "验证码不正确");
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, rep);
			log.debug("验证码不正确");
			return null;
		}
		Staff data = staffDao.fetchByUsername(username);
//		log.debug(data.getUsername());
		PrivilegeUtil util = new PrivilegeUtil(req);
		/*
		 * 用户名在数据库中存在
		 */
		if(data!=null){
			String dataPass = MD5Util.MD5Encode(password);
			/*
			 * 且密码正确
			 */
			log.debug(data.getPassword()+"\t"+dataPass);
			if(data.getPassword().equals(dataPass)){
				util.login(data);
				return "redirect:/admin/main";
			}else{
				req.setAttribute("passmessage", "密码不正确");
				req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, rep);
				log.debug("密码不正确");
				return null;
			}
		}else{
			req.setAttribute("usermessage", "不存在用户");
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, rep);
			log.debug("用户名不存在");
			return null;
		}
	}
	@RequestMapping(method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	@RequestMapping(value="/out",method=RequestMethod.GET)
	public String logout(HttpServletRequest req){
		PrivilegeUtil util = new PrivilegeUtil(req);
		util.logout();
		return "redirect:/login";
	}
}
