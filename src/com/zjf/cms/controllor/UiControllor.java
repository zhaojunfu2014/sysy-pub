package com.zjf.cms.controllor;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zjf.cms.dao.ArticleDao;
import com.zjf.cms.dao.MenuDao;
import com.zjf.cms.dao.ProjectDetailDao;
import com.zjf.cms.dao.ProjectInfoDao;
import com.zjf.cms.entity.Article;
import com.zjf.cms.entity.Menu;
import com.zjf.cms.entity.ProjectDetail;
import com.zjf.cms.entity.ProjectInfo;

/**
 * ���������
 * @author �Կ���
 *
 * Aug 15, 2012
 */
@Controller
@RequestMapping("/")
public class UiControllor {
	@Resource(name="articleDao")
	private ArticleDao articleDao;
	@Resource(name="menuDao")
	private MenuDao menuDao;
	@Resource(name="projectInfoDao")
	private ProjectInfoDao projectInfoDao;
	@Resource(name="projectDetailDao")
	private ProjectDetailDao projectDetailDao;
	/**
	 * ҳ�����������
	 * @param model
	 */
	public void dataSend(Model model){
		Set<Order> orders =  new LinkedHashSet<Order>();
		orders.add(Order.asc("level"));
		Set<Criterion> criterions = new LinkedHashSet<Criterion>();
		criterions.add(Restrictions.eq("isRoot", true));
//		criterions.add(Restrictions.eq("visible", true));
		List<Menu> menus = menuDao.getScrollData(Menu.class, -1, -1, criterions, orders);
		for(Menu m:menus){
			List<Article> datas = m.getArticles();
			if(m.getChilds()!=null && m.getChilds().size()>=1){
				for(Menu menu:m.getChilds()){
					datas.addAll(menu.getArticles());
				}
			}
			model.addAttribute("article"+m.getId(), datas);
			model.addAttribute("menu"+m.getId(), m);
		}
		model.addAttribute("menus",menus);
		model.addAttribute("isoDate", new Date());
	}
	/**
	 * ǰ̨��ҳ
	 * @return
	 */
	@RequestMapping
	public String index(Model model){
		
		dataSend(model);
		return "index";
	}
	/**
	 * �б�
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list/{id}")
	public String list(HttpServletRequest req,@PathVariable int id,Model model){
		int firstIndex  = 0;
		try{
		firstIndex = Integer.parseInt(req.getParameter("pager.offset"));
		}catch(Exception e){
			firstIndex  = 0;
		}finally{
			/*
			 * ������Ҫ
			 */
			
			dataSend(model);
			Menu thisMenu = menuDao.load(Menu.class, id);
			
			/*
			 * ��ѯ����
			 */
			Set<Order> orders1 =  new LinkedHashSet<Order>();
			orders1.add(Order.desc("pubDate"));
			Set<Criterion> criterions1 = new LinkedHashSet<Criterion>();
			criterions1.add(Restrictions.eq("menu", thisMenu));
			List<Article> datas = articleDao.getScrollData(Article.class, firstIndex, 20, criterions1, orders1);
			/*
			 * ����б���ֻ��һƪ���£�����ת����ϸҳ��
			 
			if(datas.size()==1){
				return "redirect:/detail/"+datas.get(0).getId();
			}*/
			int total = articleDao.getTableSize(Article.class, criterions1);
			/*
			 * ������ݵ�view
			 */
			model.addAttribute("datas", datas);
			model.addAttribute("total", total);
			model.addAttribute("this", thisMenu);
			model.addAttribute("nowPage", firstIndex/20+1);
			
		}
		return "list";
		
	}
	/**
	 * ������ϸ��Ϣ
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="detail/{id}")
	public String detail(@PathVariable long id,Model model){
		/*
		 * ������Ҫ
		 */
		dataSend(model);
		Article data = articleDao.fetch(Article.class, id);
		data.setCount(data.getCount()+1);
		articleDao.update(data);
		model.addAttribute("data", data);
		return "detail";
	}
	/**
	 *	��Դ��ѯ
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="trace")
	public String listTrace(HttpServletRequest req,Model model){
		dataSend(model);
//		int firstIndex  = 0;
//		try{
//		firstIndex = Integer.parseInt(req.getParameter("pager.offset"));
//		}catch(Exception e){
//			firstIndex  = 0;
//		}finally{
//			/*
//			 * ������Ҫ
//			 */
//			
//			dataSend(model);
//			/*
//			 * ��ѯ����
//			 */
//			Set<Order> orders =  new LinkedHashSet<Order>();
//			orders.add(Order.desc("startDate"));
//			List<ProjectInfo> datas = projectInfoDao.getScrollData(ProjectInfo.class, firstIndex, 20, null, orders);
//			int total  = projectInfoDao.getTableSize(ProjectInfo.class, null);
//			/*
//			 * ������Ŀ������
//			 */
//			for(ProjectInfo pjf:datas){
//				for(int t=1;t<=4;t++){
//					Set<Criterion> criterions = new LinkedHashSet<Criterion>();
//					criterions.add(Restrictions.eq("projectId", pjf.getId()));
//					criterions.add(Restrictions.eq("type", t));
//					List<ProjectDetail> dts = projectDetailDao.getScrollData(ProjectDetail.class, -1,-1,criterions, null);
//					if(isFinish(dts)){
//						pjf.setTypeFinish(t,true);
//					}else{
//						pjf.setTypeFinish(t,false);
//					}
//				}
//			}
//			model.addAttribute("datas", datas);
//			model.addAttribute("total", total);
//			model.addAttribute("nowPage", firstIndex/20+1);
//			model.addAttribute(new ProjectInfo());
//		}
		return "listTrace";
	}
	/**
	 * վ������
	 * @return
	 */
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String search(HttpServletRequest req,String search,Model model){
		/*
		 * ������Ҫ
		 */
		
		dataSend(model);
		int firstIndex  = 0;
		try{
		firstIndex = Integer.parseInt(req.getParameter("pager.offset"));
		}catch(Exception e){
			firstIndex  = 0;
		}finally{
		/* ��ѯ����
		 */
		Set<Order> orders =  new LinkedHashSet<Order>();
		orders.add(Order.desc("pubDate"));
		Set<Criterion> criterions = new LinkedHashSet<Criterion>();
		criterions.add(Restrictions.like("title", search,MatchMode.ANYWHERE));
		List<Article> datas = articleDao.getScrollData(Article.class, firstIndex, -1, criterions, orders);
		int total = articleDao.getTableSize(Article.class, criterions);
		/*
		 * ������ݵ�view
		 */
		model.addAttribute("datas", datas);
		model.addAttribute("total", total);
		model.addAttribute("nowPage", firstIndex/20+1);
		}
		return "list";
		
	}
	
	private boolean isFinish(List<ProjectDetail> dts){
		if(dts.size()==0){
			return false;
		}
		for(ProjectDetail pdt:dts){
			if(!pdt.isFinish()){
				return false;
			}
		}
		return true;
	}
	/**
	 * ��Ŀ��ϸ�б�
	 * @param projectId
	 * @param type
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/project/{projectId}/{type}",method=RequestMethod.GET)
	public String detailList(@PathVariable int projectId,@PathVariable int type,Model model){
		/*
		 * ������Ҫ
		 */
		
		dataSend(model);
		ProjectInfo info = projectInfoDao.fetch(ProjectInfo.class, projectId);
		/*
		 * ��ѯ����
		 */
		Set<Order> orders =  new LinkedHashSet<Order>();
		orders.add(Order.desc("updateDate"));
		/*
		 * Լ������
		 */
		Set<Criterion> criterions = new LinkedHashSet<Criterion>();
		criterions.add(Restrictions.eq("projectId", projectId));
		criterions.add(Restrictions.eq("type", type));
		List<ProjectDetail> datas = projectDetailDao.getScrollData(ProjectDetail.class, -1,-1,criterions, orders);
		
		model.addAttribute("datas", datas);
		model.addAttribute("info",info);
		model.addAttribute("type", type);
		return "listProjectDetail";
	}
	/**
	 * ��ʾ��Ŀ��ϸ��������
	 * @param detailId
	 * @param projectDetail
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/project/detail/{detailId}",method=RequestMethod.GET)
	public String showDetail(@PathVariable int detailId,Model model){
		/*
		 * ������Ҫ
		 */
		
		dataSend(model);
		ProjectDetail detail = projectDetailDao.fetch(ProjectDetail.class, detailId);
		model.addAttribute("detail",detail );
		return "showProjectDetail";
	}
	@RequestMapping(value="admin/top")
	public String top(){
		return "admin/top";
	}
	@RequestMapping(value="admin/foot")
	public String foot(){
		return "admin/foot";
	}
	@RequestMapping(value="admin/left")
	public String left(){
		return "admin/left";
	}
	@RequestMapping(value="admin/right")
	public String right(){
		return "admin/right";
	}
	@RequestMapping(value="admin/center")
	public String center(){
		return "admin/center";
	}
	@RequestMapping(value="admin/main")
	public String main(){
		return "admin/main";
	}
}
