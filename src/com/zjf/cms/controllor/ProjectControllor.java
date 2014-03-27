package com.zjf.cms.controllor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.zjf.cms.dao.ProjectDetailDao;
import com.zjf.cms.dao.ProjectInfoDao;
import com.zjf.cms.entity.Menu;
import com.zjf.cms.entity.ProjectDetail;
import com.zjf.cms.entity.ProjectInfo;

/**
 * ��Ŀ������
 * @author �Կ���
 *
 * Aug 28, 2012
 */
@Controller
@RequestMapping("/admin/project")
public class ProjectControllor {
	@Resource(name="projectInfoDao")
	private ProjectInfoDao projectInfoDao;
	@Resource(name="projectDetailDao")
	private ProjectDetailDao projectDetailDao;
	/**
	 * ��Ŀ��ҳ
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String projectList(HttpServletRequest req,Model model){
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
			orders.add(Order.desc("startDate"));
			List<ProjectInfo> datas = projectInfoDao.getScrollData(ProjectInfo.class, firstIndex, 20, null, orders);
			int total  = projectInfoDao.getTableSize(ProjectInfo.class, null);
			/*
			 * ������Ŀ������
			 */
			for(ProjectInfo pjf:datas){
				for(int t=1;t<=4;t++){
					Set<Criterion> criterions = new LinkedHashSet<Criterion>();
					criterions.add(Restrictions.eq("projectId", pjf.getId()));
					criterions.add(Restrictions.eq("type", t));
					List<ProjectDetail> dts = projectDetailDao.getScrollData(ProjectDetail.class, -1,-1,criterions, null);
					if(isFinish(dts)){
						pjf.setTypeFinish(t,true);
					}else{
						pjf.setTypeFinish(t,false);
					}
				}
			}
			model.addAttribute("datas", datas);
			model.addAttribute("total", total);
			model.addAttribute("nowPage", firstIndex/20+1);
			model.addAttribute(new ProjectInfo());
		}
		return "admin/project/list";
	}
	/**
	 * �����Ŀ-����
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute(new ProjectInfo());
		return "admin/project/add";
		
	}
	/**
	 * �����Ŀ
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(ProjectInfo projectInfo){
		projectInfoDao.save(projectInfo);
		return "redirect:/admin/project/list";
	}
	/**
	 * �༭��Ŀ-����
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String edit(@PathVariable int id,Model model){
		ProjectInfo projectInfo = projectInfoDao.fetch(ProjectInfo.class, id);
		model.addAttribute("projectInfo", projectInfo);
		return "admin/project/edit";
	}
	/**
	 * �༭��Ŀ
	 * @param projectInfo
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(ProjectInfo projectInfo,String pdate){
		int id = projectInfo.getId();
		ProjectInfo data = projectInfoDao.fetch(ProjectInfo.class, id);
		data.setDwgcwgzlhd(projectInfo.getDwgcwgzlhd());
		data.setDwhd(projectInfo.getDwhd());
		data.setFbhb(projectInfo.getFbhb());
		data.setName(projectInfo.getName());
		data.setCode(projectInfo.getCode());
		data.setZljdtb(projectInfo.getZljdtb());
		data.setZydyhb(projectInfo.getZydyhb());
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date pd = fmt.parse(pdate);
			data.setStartDate(pd);
		} catch (ParseException e) {
			return "redirect:/admin/project/list";
		}
		
		projectInfoDao.update(data);
		return "redirect:/admin/project/list";
		
	}
	/**
	 * ɾ����Ŀ
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable int id,HttpServletRequest req){
		ProjectInfo data = projectInfoDao.load(ProjectInfo.class, id);
		projectInfoDao.delete(data);
		String referer = req.getHeader("Referer");
		referer = referer.substring(referer.indexOf(req.getContextPath())+req.getContextPath().length());
		return "redirect:"+referer;
	}
	/**
	 * ��Ŀ��ϸ�б�
	 * @return
	 */
	@RequestMapping(value="/detail/{projectId}/{type}",method=RequestMethod.GET)
	public String detailList(@PathVariable int projectId,@PathVariable int type,Model model){
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
		return "admin/projectDetail/list";
	}
	/**
	 * �����Ŀ��ϸ
	 * @return
	 */
	@RequestMapping(value="/detail/add/{projectId}/{type}",method=RequestMethod.POST)
	public String add(@PathVariable int projectId,@PathVariable int type,ProjectDetail projectDetail){
		projectDetail.setFinish(false);
		projectDetail.setProjectId(projectId);
		projectDetail.setType(type);
		projectDetailDao.save(projectDetail);
		return "redirect:/admin/project/detail/"+projectId+"/"+type;
	}
	/**
	 * �༭��Ŀ��ϸ-����
	 * @param projectId
	 * @param type
	 * @param projectDetail
	 * @return
	 */
	@RequestMapping(value="/detail/edit/{detailId}",method=RequestMethod.GET)
	public String editDetail(@PathVariable int detailId,Model model){
		ProjectDetail detail = projectDetailDao.fetch(ProjectDetail.class, detailId);
		model.addAttribute("detail",detail);
		return "/admin/projectDetail/edit";
	}
	/**
	 * �༭��Ŀ��ϸ
	 * @param projectId
	 * @param type
	 * @param projectDetail
	 * @return
	 */
	@RequestMapping(value="/detail/edit/{detailId}",method=RequestMethod.POST)
	public String editDetail(@PathVariable int detailId,ProjectDetail projectDetail,Model model){
		ProjectDetail detail = projectDetailDao.fetch(ProjectDetail.class, detailId);
		detail.setDetail(projectDetail.getDetail());
		detail.setInfo(projectDetail.getInfo());
		detail.setUpdateDate(new Date());
		projectDetailDao.update(detail);
		model.addAttribute("detail",detail );
		return "/admin/projectDetail/edit";
	}
	/**
	 * ��עΪ�����
	 * @param detailId
	 * @param projectId
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/detail/finish/{detailId}/{projectId}/{type}",method=RequestMethod.GET)
	public String finish(@PathVariable int detailId,@PathVariable int projectId,@PathVariable int type){
		ProjectDetail detail = projectDetailDao.fetch(ProjectDetail.class, detailId);
		detail.setFinish(true);
		detail.setUpdateDate(new Date());
		projectDetailDao.update(detail);
		return "redirect:/admin/project/detail/"+projectId+"/"+type;
	}
	/**
	 * ��עΪδ���
	 * @param detailId
	 * @param projectId
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/detail/unfinish/{detailId}/{projectId}/{type}",method=RequestMethod.GET)
	public String unfinish(@PathVariable int detailId,@PathVariable int projectId,@PathVariable int type){
		ProjectDetail detail = projectDetailDao.fetch(ProjectDetail.class, detailId);
		detail.setFinish(false);
		detail.setUpdateDate(new Date());
		projectDetailDao.update(detail);
		return "redirect:/admin/project/detail/"+projectId+"/"+type;
	}
	/**
	 * ������Ŀ
	 * @param detailId
	 * @param projectId
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/detail/hide/{detailId}/{projectId}/{type}",method=RequestMethod.GET)
	public String hide(@PathVariable int detailId,@PathVariable int projectId,@PathVariable int type){
		ProjectDetail detail = projectDetailDao.fetch(ProjectDetail.class, detailId);
		detail.setVisible(false);
		detail.setUpdateDate(new Date());
		projectDetailDao.update(detail);
		return "redirect:/admin/project/detail/"+projectId+"/"+type;
	}
	/**
	 * ��ʾ��Ŀ
	 * @param detailId
	 * @param projectId
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/detail/show/{detailId}/{projectId}/{type}",method=RequestMethod.GET)
	public String show(@PathVariable int detailId,@PathVariable int projectId,@PathVariable int type){
		ProjectDetail detail = projectDetailDao.fetch(ProjectDetail.class, detailId);
		detail.setVisible(true);
		detail.setUpdateDate(new Date());
		projectDetailDao.update(detail);
		return "redirect:/admin/project/detail/"+projectId+"/"+type;
	}
	/**
	 * ɾ����ϸ��Ŀ
	 * @param detailId
	 * @param projectId
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/detail/delete/{detailId}/{projectId}/{type}",method=RequestMethod.GET)
	public String delete(@PathVariable int detailId,@PathVariable int projectId,@PathVariable int type){
		ProjectDetail detail = projectDetailDao.fetch(ProjectDetail.class, detailId);
		projectDetailDao.delete(detail);
		return "redirect:/admin/project/detail/"+projectId+"/"+type;
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
}
