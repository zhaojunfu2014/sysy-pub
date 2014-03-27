package com.zjf.cms.controllor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.zjf.cms.dao.AttachmentDao;
import com.zjf.cms.entity.Article;
import com.zjf.cms.entity.Attachment;
import com.zjf.cms.util.ConfigUtil;
import com.zjf.cms.util.UploadUtil;

/**
 * �ļ��ϴ�������
 * @author �Կ���
 *
 * Aug 22, 2012
 */
@Controller
@RequestMapping("/admin/attachment")
public class AttachmentControllor {
	@Resource(name="attachmentDao")
	private AttachmentDao attachmentDao;
	private Log log = LogFactory.getLog(getClass());
	/**
	 * �ϴ��ļ�
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String upload(MultipartFile u,HttpServletRequest req,Model model){
		log.debug(u.getName()+u.getSize()+u.getContentType()+u.getOriginalFilename());
		String type = u.getContentType();
		ConfigUtil util = new ConfigUtil();
		/*
		 * У���ϴ�����
		 */
		if(!util.getUploadType().contains(type)){
			log.debug(type+"---");
			model.addAttribute("message", "�������ϴ�������");
			return "error";
		}
		UploadUtil uploadUtil = new UploadUtil(u,req,"attachment");
		
		String path = null;
		/*
		 * ���ļ������ļ�ϵͳ
		 */
		try {
			path = uploadUtil.saveToFileSystem();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * ����������־û������ݿ�
		 */
		Attachment attachment = new Attachment();
		
		attachment.setName(path.substring(path.lastIndexOf("/")+1));
		attachment.setPath(path);
		attachment.setType(u.getContentType());
		attachment.setUploadDate(new Date());
		attachment.setSize(u.getSize());
		attachmentDao.save(attachment);
		/*
		 * ���������ϴ��ĸ����б����session
		 */
		HttpSession session = req.getSession();
		List<Attachment> attachments = (List<Attachment>) session.getAttribute("attachments");
		if(attachments==null || attachments.isEmpty()){
			attachments =  new ArrayList<Attachment>();
			attachments.add(0,attachment);
			session.setAttribute("attachments", attachments);
		}else{
			attachments.add(0,attachment);
		}
		
		return "admin/attachment/add";
		
	}
	/**
	 * �ϴ��ļ�-����
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		return "admin/attachment/add";
		
	}
	/**
	 * ɾ���ļ�
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable long id,Model model,HttpServletRequest req,Integer action){
		
		/*
		 * �����ݿ���ɾ��
		 */
		Attachment a = attachmentDao.fetch(Attachment.class, id);
		String path = a.getPath();
		attachmentDao.delete(a);
		
		/*
		 * ���ļ�ϵͳ��ɾ��
		 */
		UploadUtil util = new UploadUtil(null,req,null);
		util.deleteFromFileSystem(path);
		/*
		 * ��session�еĻ���ɾ��
		 */
		HttpSession session = req.getSession();
		List<Attachment> attachments = (List<Attachment>) session.getAttribute("attachments");
		if(attachments==null || attachments.isEmpty()){
		}else{
			attachments.remove(a);
		}
		if(action!=null && action==1){
			String page = req.getParameter("pager.offset");
			String callback="redirect:/admin/attachment/list";
			if(page!=null){
				callback+="?pager.offset="+page;
			}
			return callback;
		}
		String referer = req.getHeader("Referer");
		referer = referer.substring(referer.indexOf(req.getContextPath())+req.getContextPath().length());
		return "redirect:"+referer;
		
	}
	/**
	 * �����б�
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(HttpServletRequest req,Model model){
		int firstIndex  = 0;
		try{
		firstIndex = Integer.parseInt(req.getParameter("pager.offset"));
		}catch(Exception e){
			firstIndex  = 0;
		}finally{
			/*
			 * ��ѯ����
			 */
			Set<Order> orders =  new LinkedHashSet<Order>();
			orders.add(Order.desc("uploadDate"));
			List<Attachment> datas = attachmentDao.getScrollData(Attachment.class, firstIndex, 20, null, orders);
			int total = attachmentDao.getTableSize(Attachment.class, null);
			model.addAttribute("datas", datas);
			model.addAttribute(new Attachment());
			model.addAttribute("total",total);
			model.addAttribute("nowPage", firstIndex/20+1);
		}
		return "admin/attachment/list";
		
	}
}
