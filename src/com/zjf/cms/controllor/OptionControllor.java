package com.zjf.cms.controllor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zjf.cms.entity.SiteOption;
import com.zjf.cms.util.ConfigUtil;

/**
 * 配置控制器
 * @author 赵俊夫
 *
 * Aug 26, 2012
 */
@Controller
@RequestMapping("/admin/option")
public class OptionControllor {
	/**
	 * 显示配置列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(Model model){
		ConfigUtil util = new ConfigUtil();
		SiteOption option = new SiteOption();
		option.setSiteName(util.getValue("siteName"));
		option.setUploadType(util.getValue("uploadType"));
		model.addAttribute("option", option);
		return "admin/option/list";
		
	}
	/**
	 * 修改配置
	 * @param option
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit(SiteOption option){
		ConfigUtil util = new ConfigUtil();
		util.setValue("siteName", option.getSiteName());
		util.setValue("uploadType", option.getUploadType());
		util.commit();
		return "redirect:/admin/option/list";
		
	}
}
