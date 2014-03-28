package com.zjf.cms.controllor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zjf.cms.dao.ArticleDao;
import com.zjf.cms.dao.MenuDao;
import com.zjf.cms.dao.StaffDao;
import com.zjf.cms.dao.TempArticleDao;
import com.zjf.cms.entity.Article;
import com.zjf.cms.entity.Menu;
import com.zjf.cms.entity.ProjectDetail;
import com.zjf.cms.entity.Staff;
import com.zjf.cms.entity.TempArticle;
import com.zjf.cms.util.PrivilegeUtil;

/**
 * 文章控制器
 * @author 赵俊夫
 *
 * Aug 20, 2012
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleControllor {
	@Resource(name="articleDao")
	private ArticleDao articleDao;
	@Resource(name="menuDao")
	private MenuDao menuDao;
	@Resource(name="staffDao")
	private StaffDao staffDao;
	@Resource(name="tempArticleDao")
	private TempArticleDao tempDao;
	private Log log = LogFactory.getLog(getClass());
	/**
	 * 显示文章分页
	 * @param firstIndex
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
			orders.add(Order.desc("pubDate"));
			List<Article> datas = articleDao.getScrollData(Article.class, firstIndex, 20, null, orders);
			int total = articleDao.getTableSize(Article.class, null);
			Set<Criterion> criterions1 = new LinkedHashSet<Criterion>();
			criterions1.add(Restrictions.eq("isRoot", true));
			List<Menu> menus = menuDao.getScrollData(Menu.class, criterions1);
			/*
			 * 存放数据到view
			 */
			model.addAttribute("datas", datas);
			model.addAttribute("total", total);
			model.addAttribute("nowPage", firstIndex/20+1);
			model.addAttribute("menus", menus);
			model.addAttribute(new Menu());
		}
		return "admin/article/list";
		
	}
	/**
	 * 显示文章分页-按菜单
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listMenu/{id}",method=RequestMethod.GET)
	public String listMenu(HttpServletRequest req,@PathVariable int id,Model model){
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
			orders.add(Order.desc("pubDate"));
			Set<Criterion> criterions = new LinkedHashSet<Criterion>();
			Menu menu = menuDao.load(Menu.class, id);
			criterions.add(Restrictions.eq("menu", menu));
			List<Article> datas = articleDao.getScrollData(Article.class, firstIndex, 20, criterions, orders);
			int total = articleDao.getTableSize(Article.class, criterions);
			Set<Criterion> criterions1 = new LinkedHashSet<Criterion>();
			criterions1.add(Restrictions.eq("isRoot", true));
			List<Menu> menus = menuDao.getScrollData(Menu.class, criterions1);
			/*
			 * 存放数据到view
			 */
			model.addAttribute("datas", datas);
			model.addAttribute("total", total);
			model.addAttribute("nowPage", firstIndex/20+1);
			model.addAttribute("menus", menus);
			model.addAttribute(id);
			model.addAttribute(new Menu());
		}
		return "admin/article/listMenu";
	}
	/**
	 * 显示文章分页-按标题
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listTitle",method=RequestMethod.POST)
	public String listTitle(HttpServletRequest req,String title,Model model){
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
			orders.add(Order.desc("pubDate"));
			Set<Criterion> criterions = new LinkedHashSet<Criterion>();
			log.debug(title);
			criterions.add(Restrictions.like("title", title,MatchMode.ANYWHERE));
			List<Article> datas = articleDao.getScrollData(Article.class, firstIndex, -1, criterions, orders);
			int total = articleDao.getTableSize(Article.class, criterions);
			Set<Criterion> criterions1 = new LinkedHashSet<Criterion>();
			criterions1.add(Restrictions.eq("isRoot", true));
			List<Menu> menus = menuDao.getScrollData(Menu.class, criterions1);
			/*
			 * 存放数据到view
			 */
			model.addAttribute("datas", datas);
			model.addAttribute("total", total);
			model.addAttribute("nowPage", firstIndex/20+1);
			model.addAttribute("menus", menus);
			model.addAttribute(1);
			model.addAttribute(new Menu());
		}
		return "admin/article/listTitle";
	}
	/**
	 * 发表文章-界面
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value="/pub",method=RequestMethod.GET)
	public String pub(Model model){
		Set<Criterion> criterions = new LinkedHashSet<Criterion>();
		criterions.add(Restrictions.eq("isRoot", true));
		List<Menu> menus = menuDao.getScrollData(Menu.class, criterions);
		model.addAttribute("menus", menus);
		model.addAttribute(new Article());
		return "admin/article/pub";
	}
	/**
	 * 发表文章
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value="/pub",method=RequestMethod.POST)
	public String pub(int menuId,Model model,@Valid Article article,BindingResult br,HttpServletRequest req){
		log.debug("menuId"+menuId+"\tarticle info:"+article.toString());
		if(br.hasErrors()){
			return "admin/article/pub";
		}
		Menu menu = menuDao.load(Menu.class, menuId);
		article.setMenu(menu);
		articleDao.save(article);
		deleteTemp(req);
		return "redirect:/admin/article/list";
	}
	/**
	 * 存储临时文章
	 * @param tempArticle
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/temp/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addTemp(@RequestBody TempArticle tempArticle,Model model,HttpServletRequest req){
		log.debug(tempArticle.getContent()+"\t"+tempArticle.getTitle());
		PrivilegeUtil util = new PrivilegeUtil(req);
		Staff staff = util.getLoginUser();
		staff = staffDao.fetch(Staff.class, staff.getId());
		TempArticle temp =  staff.getTemp();
		if(temp!=null){
			temp.setContent(tempArticle.getContent());
			temp.setTitle(tempArticle.getTitle());
			temp.setUpdateDate(new Date());
		}else{
			temp = new TempArticle(tempArticle.getTitle(),tempArticle.getContent());
			tempDao.save(temp);
			staff.setTemp(temp);
		}
		staffDao.update(staff);
		Map<String, Object> modelMap = new HashMap<String, Object>(3);
		String success = "自动保存:"+new Date().toLocaleString();
		modelMap.put("success", success);  
		return modelMap;  
	}
	/**
	 * 获取临时文章
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/temp/fetch",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> fetchTemp(HttpServletRequest req){
		PrivilegeUtil util = new PrivilegeUtil(req);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Staff staff = util.getLoginUser();
		staff = staffDao.fetch(Staff.class, staff.getId());
		TempArticle temp = staff.getTemp();
		if(temp!=null){
			modelMap.put("hasTemp", true);
			modelMap.put("title", temp.getTitle());
			modelMap.put("content", temp.getContent());
		}else{
			modelMap.put("hasTemp", false);
		}
		return modelMap;
	}
	/**
	 * 删除临时文章
	 */
	public void deleteTemp(HttpServletRequest req){
		PrivilegeUtil util = new PrivilegeUtil(req);
		Staff staff = util.getLoginUser();
		staff = staffDao.fetch(Staff.class, staff.getId());
		TempArticle temp = staff.getTemp();
		staff.setTemp(null);
		staffDao.update(staff);
		if(temp!=null){
			tempDao.delete(temp);
		}
	}
	/**
	 * 编辑文章-界面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String edit(@PathVariable long id,Model model){
		Article data = articleDao.fetch(Article.class, id);
		model.addAttribute("article", data);
		Set<Criterion> criterions = new LinkedHashSet<Criterion>();
		criterions.add(Restrictions.eq("isRoot", true));
		List<Menu> menus = menuDao.getScrollData(Menu.class, criterions);
		model.addAttribute("menus", menus);
		return "admin/article/edit";
	}
	/**
	 * 编辑文章
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit/{id}",method=RequestMethod.POST)
	public String edit(@PathVariable long id,@Valid Article article,String pdate,BindingResult br,int menuId,Model model,HttpServletRequest req){
		if(br.hasErrors()){
			return "redirect:/admin/article/edit/"+id;
		}
		/*
		 * 从数据库中取出对应id的文章
		 */
		Article data = articleDao.fetch(Article.class, id);
		/*
		 * 编辑文章数据
		 */
		data.setTitle(article.getTitle());
		data.setContent(article.getContent());
//		log.debug(pubDate);
		log.debug(pdate);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date pd = fmt.parse(pdate);
			data.setPubDate(pd);
		} catch (ParseException e) {
			return "redirect:/admin/article/edit/"+id;
		}
		
		Menu m = menuDao.load(Menu.class, menuId);
		data.setMenu(m);
		/*
		 * 持久化到数据库
		 */
		articleDao.update(data);
		deleteTemp(req);
		return "redirect:/admin/article/list";
	}
	/**
	 * 删除文章
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable long id,HttpServletRequest req){
		/*
		 * 从数据库中取出对应id的文章
		 */
		Article data = articleDao.fetch(Article.class, id);
		 /* 从数据库中删除
		 */
		@SuppressWarnings("unused")
		String referer = req.getHeader("Referer");
		referer = referer.substring(referer.indexOf(req.getContextPath())+req.getContextPath().length());
		log.debug(referer);
		articleDao.delete(data);
		return "redirect:"+referer;
	}
	/**
	 * 隐藏
	 * @param articleId
	 * @return
	 */
	@RequestMapping(value="/hide/{articleId}",method=RequestMethod.GET)
	public String hide(@PathVariable int articleId,HttpServletRequest req){
		Article data = articleDao.fetch(Article.class,new Long(articleId));
		data.setVisible(false);
		articleDao.update(data);
		String referer = req.getHeader("Referer");
		referer = referer.substring(referer.indexOf(req.getContextPath())+req.getContextPath().length());
		return "redirect:"+referer;
	}
	/**
	 * 隐藏
	 * @param articleId
	 * @return
	 */
	@RequestMapping(value="/show/{articleId}",method=RequestMethod.GET)
	public String show(@PathVariable int articleId,HttpServletRequest req){
		Article data = articleDao.fetch(Article.class,new Long(articleId));
		data.setVisible(true);
		articleDao.update(data);
		String referer = req.getHeader("Referer");
		referer = referer.substring(referer.indexOf(req.getContextPath())+req.getContextPath().length());
		return "redirect:"+referer;
	}
}
