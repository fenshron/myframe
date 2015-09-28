package cn.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.demo.model.PubAuthoritiesResources;
import cn.demo.service.PubAuthoritiesResourcesService;
import cn.demo.util.DataGrid;

@Controller
@RequestMapping("/admin")
public class AuthoritiesResourcesController  extends BaseController{
	
	private static Logger logger = Logger.getLogger(AuthoritiesResourcesController.class);  
	
	@Resource
	private PubAuthoritiesResourcesService service;
	
	@RequestMapping("/authoritiesResources")
	public String goAuthoritiesResources(){
		return "authorities/authoritiesResources";
	}
	
	@RequestMapping("/authoritiesResources_alldata")
	public void getAllData(HttpServletRequest request, HttpServletResponse response){
			int rows = Integer.parseInt(request.getParameter("rows"));
			int page = (Integer.parseInt(request.getParameter("page"))-1)*rows;
			List<PubAuthoritiesResources> pubAuthorities = service.getAuthoritiesResourcesByPage(page,rows);
			DataGrid dg = new DataGrid();
			dg.setTotal(service.getAllAuthoritiesResourcesNum());
			dg.setRows(pubAuthorities);
			try {
				response.getWriter().write(super.writeJson(dg));
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	}
	
	@RequestMapping("/authoritiesResources_del")
	public void doDel(HttpServletRequest request,HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("param.keys"));
		int result=service.deleteByPrimaryKey(id);
		if(result>0){
			myresponse(response,true,"删除成功！");
		}
	}
	
	@RequestMapping("/authoritiesResources_add")
	public String AddPage(){
		 return "authorities/addAuthoritiesResources";
	}
	
	@RequestMapping("/authoritiesResources_doadd")
	public void doAdd(PubAuthoritiesResources record,HttpServletRequest request,HttpServletResponse response){
		int result=service.insert(record);
		if(result>0){
			myresponse(response,true,"新增成功！");
		}
	}
}
