package com.zjf.cms.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zjf.cms.entity.Staff;

/**
 * Ȩ�����ع���
 * @author �Կ���
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
	 * ���췽��
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
	 * ���Ȩ���Ƿ���ͨ��
	 * @return
	 */
	public boolean check(){
		boolean access = false;
		
		for(String pri:userPrivileges){
			/*
			 * ����û���Ȩ�޴�*,�Ҳ�����except�﷨
			 */
			if(pri.contains("*") && !pri.contains(EXCEPT)){
				String privilege = pri.substring(0,pri.indexOf('*')-1);
				if(targetUri.startsWith(privilege)){
					log.debug("ͨ����"+targetUri+"\t"+privilege);
					
					return true;
				}
			}else if(pri.contains("*") && pri.contains(EXCEPT)){
				/*
				 * ����û�Ȩ�޴�*���Ҵ���except�﷨
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
					log.debug("ͨ����"+targetUri+"\t");
					return true;
				}else{
					access = false;
				}
			}else if(!pri.contains("*")){
				/*
				 * Ӳ����Ȩ��
				 */
				if(targetUri.equals(pri)){
					log.debug("ͨ����"+targetUri+"\t"+pri);
					return true;
				}
			}
		}
		return access;
	}
	/**
	 * ��Ա����¼-
	 * ��staff�������HttpSession��
	 * @param req
	 * @param staff
	 */
	public void login(Staff staff){
		HttpSession session = req.getSession();
		session.setAttribute("loginUser", staff);
	}
	/**
	 * ��Ա���ǳ�-
	 * ��staff�����Ƴ���HttpSession��
	 * @param req
	 * @param staff
	 */
	public void logout(){
		HttpSession session = req.getSession();
		session.removeAttribute("loginUser");
	}
	/**
	 * ��session��ȡ����ǰ��¼��Ա��
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
	 * �ж�Ա���Ƿ��¼
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
