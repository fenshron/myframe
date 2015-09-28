package cn.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.demo.model.PubResources;
import cn.demo.pageModel.ResourcesMenu;
import cn.demo.service.PageService;

@Controller
public class MenuController extends BaseController {
	private static Logger logger = Logger.getLogger(MenuController.class);  
	@Autowired
	PageService pageService;
	
	@RequestMapping("/pageMenu")
	public void getMenu(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String treemenujson = super.writeJson(getTreeMenu(request));
	     response.getWriter().write(treemenujson);
	     response.getWriter().flush();
	     response.getWriter().close();
	}
	
	public List<ResourcesMenu> getTreeMenu(HttpServletRequest request){
		HttpSession session = request.getSession();
		session = request.getSession(false);
		List<ResourcesMenu> menu=new ArrayList<ResourcesMenu>();
		
		
		List<PubResources> pubResources = pageService.getMenu(session.getAttribute("useraccount").toString());
		//遍历树数据
		for(PubResources resources:pubResources){
			ResourcesMenu resourcesMenu=new ResourcesMenu();
			resourcesMenu.setId(resources.getResource_id());
			resourcesMenu.setText(resources.getResource_name());
			Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("url", resources.getResource_string());
			resourcesMenu.setAttributes(attributes);
			if(resources.getPid()!=""){
				resourcesMenu.setPid(resources.getPid());
			}
			menu.add(resourcesMenu);
		}
		return menu;
	}
}
