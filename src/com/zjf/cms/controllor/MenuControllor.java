package com.zjf.cms.controllor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import com.zjf.cms.dao.MenuDao;
import com.zjf.cms.entity.Menu;
/**
 * 菜单的控制器
 * @author 赵俊夫
 *
 * Aug 15, 2012
 */
@Controller
@RequestMapping("/admin/menu")
public class MenuControllor {
	@Resource(name="menuDao")
	private MenuDao menuDao;
	private Log log = LogFactory.getLog(getClass());
	/**
	 * 显示菜单列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model){
		Set<Order> orders =  new LinkedHashSet<Order>();
		orders.add(Order.asc("level"));
		Set<Criterion> criterions = new LinkedHashSet<Criterion>();
		criterions.add(Restrictions.eq("isRoot", true));
		List<Menu> menus = menuDao.getScrollData(Menu.class, -1, -1, criterions, orders);
		model.addAttribute("menus",menus);
		return "admin/menu/list";
	}
	/**
	 * 更新菜单-界面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String edit(@PathVariable int id,Model model){
		Menu menu = menuDao.fetch(Menu.class, id);
		model.addAttribute("menu",menu);
		return "admin/menu/edit";
	}
	/**
	 * 更新菜单
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(@Valid Menu menu,BindingResult br){
		if(br.hasErrors()){
			return "admin/menu/edit";
		}
		Menu m = menuDao.load(Menu.class, menu.getId());
		m.setName(menu.getName());
		m.setLevel(menu.getLevel());
		m.setVisible(menu.isVisible());
		
		menuDao.update(m);
		return "redirect:/admin/menu/list";
	}
	
	/**
	 * 添加新菜单
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Valid Menu menu,BindingResult br){
		if(br.hasErrors()){
			return "admin/menu/add";
		}
		menu.setRoot(true);
		menuDao.save(menu);
		log.debug(menu.getName()+menu.isRoot());
		return InternalResourceViewResolver.REDIRECT_URL_PREFIX+"/admin/menu/list";
	}
	/**
	 * 添加新菜单-界面
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute(new Menu());
		return "admin/menu/add";
	}
	/**
	 * 添加子菜单-界面
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/add/{id}",method=RequestMethod.GET)
	public String addSub(@PathVariable int id,Model model){
		model.addAttribute(id);
		model.addAttribute(new Menu());
		return "admin/menu/addSub";
	}
	/**
	 * 添加子菜单
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/add/{id}",method=RequestMethod.POST)
	public String addSub(@Valid Menu menu,int id,BindingResult br){
		if(br.hasErrors()){
			return "admin/menu/add";
		}
		Menu parent = menuDao.load(Menu.class, id);
		menu.setParent(parent);
		menu.setRoot(false);
		menu.setVisible(true);
		menuDao.save(menu);
		return InternalResourceViewResolver.REDIRECT_URL_PREFIX+"/admin/menu/list";
	}
	/**
	 * 子菜单列表
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/sub/{id}",method=RequestMethod.GET)
	public String listSub(@PathVariable int id,Model model){
		Menu menu = menuDao.fetch(Menu.class, id);
		List<Menu> datas = menu.getChilds();
		log.debug(menu.getChilds().size());
		model.addAttribute("menus", datas);
		return "admin/menu/listSub";
	}
	/**
	 * 隐藏菜单
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/hide/{id}",method=RequestMethod.GET)
	public String hide(@PathVariable int id,HttpServletRequest req){
		Menu menu = menuDao.load(Menu.class, id);
		menu.setVisible(false);
		menuDao.update(menu);
		String referer = req.getHeader("Referer");
		referer = referer.substring(referer.indexOf(req.getContextPath())+req.getContextPath().length());
		return "redirect:"+referer;
	}
	/**
	 * 显示菜单
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/show/{id}",method=RequestMethod.GET)
	public String show(@PathVariable int id,HttpServletRequest req){
		Menu menu = menuDao.load(Menu.class, id);
		menu.setVisible(true);
		menuDao.update(menu);
		String referer = req.getHeader("Referer");
		referer = referer.substring(referer.indexOf(req.getContextPath())+req.getContextPath().length());
		return "redirect:"+referer;
	}
	/**
	 * 删除菜单
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable int id,HttpServletRequest req){
		Menu menu = menuDao.load(Menu.class, id);
		menuDao.delete(menu);
		
		String referer = req.getHeader("Referer");
		referer = referer.substring(referer.indexOf(req.getContextPath())+req.getContextPath().length());
		return "redirect:"+referer;
	}
	
}
