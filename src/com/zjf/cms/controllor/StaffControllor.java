package com.zjf.cms.controllor;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.zjf.cms.dao.StaffDao;
import com.zjf.cms.entity.Article;
import com.zjf.cms.entity.Menu;
import com.zjf.cms.entity.Staff;
import com.zjf.cms.util.MD5Util;

/**
 * 员工控制器
 * @author 赵俊夫
 *
 * Aug 24, 2012
 */
@Controller
@RequestMapping("/admin/staff")
public class StaffControllor {
	@Resource(name="staffDao")
	private StaffDao staffDao;
	/**
	 * 显示员工分页
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
			 * 查询条件
			 */
			Set<Order> orders =  new LinkedHashSet<Order>();
			orders.add(Order.desc("regDate"));
			List<Staff> datas = staffDao.getScrollData(Staff.class, firstIndex, 20, null, orders);
			int total = staffDao.getTableSize(Staff.class, null);
			/*
			 * 存放数据到view
			 */
			model.addAttribute("datas", datas);
			model.addAttribute("total", total);
			model.addAttribute("nowPage", firstIndex/20+1);
			model.addAttribute(new Staff());
		}
		return "admin/staff/list";
		
	}
	/**
	 * 添加新员工-界面
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute(new Staff());
		return "admin/staff/add";
	}
	/**
	 * 添加新员工
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Valid Staff staff,BindingResult br,Model model){
		try{
			if(br.hasErrors()){
				return "admin/staff/add";
			}
			String password = staff.getPassword();
			password =  MD5Util.MD5Encode(password);
			staff.setPassword(password);
			staffDao.save(staff);
		}catch(Exception e){
			model.addAttribute("usermessage", "不能重复添加");
			return "admin/staff/add";
		}
		return InternalResourceViewResolver.REDIRECT_URL_PREFIX+"/admin/staff/list";
	}
	/**
	 * 更新员工-界面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String edit(@PathVariable int id,Model model){
		Staff staff = staffDao.fetch(Staff.class, id);
		model.addAttribute("staff",staff);
		return "admin/staff/edit";
	}
	/**
	 * 更新员工
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(@Valid Staff staff,BindingResult br){
		Staff data = staffDao.load(Staff.class, staff.getId());
		data.setPrivilege(staff.getPrivilege());
		if(!staff.getPassword().equals("")){
			data.setPassword(MD5Util.MD5Encode(staff.getPassword()));
		}
		staffDao.update(data);
		return "redirect:/admin/staff/list";
	}
	/**
	 * 删除员工
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable int id,HttpServletRequest req){
		Staff staff = staffDao.load(Staff.class, id);
		staffDao.delete(staff);
		
		String referer = req.getHeader("Referer");
		referer = referer.substring(referer.indexOf(req.getContextPath())+req.getContextPath().length());
		return "redirect:"+referer;
	}
}
