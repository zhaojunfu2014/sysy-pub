package com.zjf.cms.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjf.cms.entity.Staff;

/**
 * 权限拦截工具
 * @author 赵俊夫
 *
 * Aug 24, 2012
 */
public class PrivilegeUtil {
	private String[] userPrivileges;
	private String targetUri;
	private HttpServletRequest req;
	public static final String EXCEPT = "!except:";
	public static final String EXCEPT_SPLIT = ",";
	private Log log = LogFactory.getLog(getClass());
	/**
	 * 构造方法
	 * @param targetUri
	 * @param privilege
	 */
	public PrivilegeUtil(String targetUri,String privilege) {
		userPrivileges= privilege.split(";");
		this.targetUri = targetUri;
	}
	public PrivilegeUtil() {
	}
	public PrivilegeUtil(HttpServletRequest req) {
		this.req = req;
	}
	public PrivilegeUtil(String targetUri,String privilege,HttpServletRequest req) {
		this(targetUri,privilege);
		this.req = req;
	}
	/**
	 * 检测权限是否能通过
	 * @return
	 */
	public boolean check(){
		boolean access = false;
		
		for(String pri:userPrivileges){
			/*
			 * 如果用户的权限带*,且不存在except语法
			 */
			if(pri.contains("*") && !pri.contains(EXCEPT)){
				String privilege = pri.substring(0,pri.indexOf('*')-1);
				if(targetUri.startsWith(privilege)){
					log.debug("通过："+targetUri+"\t"+privilege);
					
					return true;
				}
			}else if(pri.contains("*") && pri.contains(EXCEPT)){
				/*
				 * 如果用户权限带*，且存在except语法
				 */
				String privilege = pri.substring(0,pri.indexOf('*')-1);
				List<String> excepts = new ArrayList<String>();
				String exceptExt = pri.substring(pri.indexOf(EXCEPT)+EXCEPT.length());
				String[] exceptExtSplit = exceptExt.split(EXCEPT_SPLIT);
				for(String eespt:exceptExtSplit){
					excepts.add(privilege+"/"+eespt);
					log.debug(privilege+"/"+eespt);
				}
				if(targetUri.startsWith(privilege)){
					for(String s:excepts){
						if(targetUri.startsWith(s)){
							return false;
						}
					}
					log.debug("通过："+targetUri+"\t");
					return true;
				}else{
					access = false;
				}
			}else if(!pri.contains("*")){
				/*
				 * 硬编码权限
				 */
				if(targetUri.equals(pri)){
					log.debug("通过："+targetUri+"\t"+pri);
					return true;
				}
			}
		}
		return access;
	}
	/**
	 * 用员工登录-
	 * 将staff对象存入HttpSession中
	 * @param req
	 * @param staff
	 */
	public void login(Staff staff){
		HttpSession session = req.getSession();
		session.setAttribute("loginUser", staff);
	}
	/**
	 * 将员工登出-
	 * 将staff对象移除出HttpSession中
	 * @param req
	 * @param staff
	 */
	public void logout(){
		HttpSession session = req.getSession();
		session.removeAttribute("loginUser");
	}
	/**
	 * 从session中取出当前登录的员工
	 * 
	 * @return
	 */
	public Staff getLoginUser(){
		HttpSession session = req.getSession();
		Staff staff = (Staff) session.getAttribute("loginUser");
		if(staff!=null){
			return staff;
		}
		return null;
	}
	/**
	 * 判断员工是否登录
	 * @return
	 */
	public boolean isLogin(){
		HttpSession session = req.getSession();
		Staff staff = (Staff) session.getAttribute("loginUser");
		if(staff!=null){
			return true;
		}else{
			return false;
		}
	}
	public static void main(String[] args) {
		PrivilegeUtil util = new PrivilegeUtil("/admin/article/add/1","/admin/article/*!except:delete,add;/admin/*");
		util.check();
	}
	public String[] getUserPrivileges() {
		return userPrivileges;
	}
	public void setUserPrivileges(String[] userPrivileges) {
		this.userPrivileges = userPrivileges;
	}
	public String getTargetUri() {
		return targetUri;
	}
	public void setTargetUri(String targetUri) {
		this.targetUri = targetUri;
	}
	public HttpServletRequest getReq() {
		return req;
	}
	public void setReq(HttpServletRequest req) {
		this.req = req;
	}
	
}
