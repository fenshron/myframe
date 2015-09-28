package cn.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.demo.model.PubAuthorities;
import cn.demo.model.PubResources;
import cn.demo.service.PubAuthoritiesService;
import cn.demo.util.DataGrid;

@Controller
@RequestMapping("/admin")
public class AuthoritiesController extends BaseController{
	
	private static Logger logger = Logger.getLogger(AuthoritiesController.class);  
	
	@Resource
	private PubAuthoritiesService service;
	
	@RequestMapping("/authority")
	public String goAuthoritiesPage(){
		return "authorities/authorities";
	}
	
	@RequestMapping("/authority_alldata")
	public void getAllData(HttpServletRequest request, HttpServletResponse response){
			int rows = Integer.parseInt(request.getParameter("rows"));
			int page = (Integer.parseInt(request.getParameter("page"))-1)*rows;
			List<PubAuthorities> pubAuthorities = service.getAuthoritiesByPage(page,rows);
			DataGrid dg = new DataGrid();
			dg.setTotal(service.getAllAuthoritiesNum());
			dg.setRows(pubAuthorities);
			try {
				response.getWriter().write(super.writeJson(dg));
				response.getWriter().flush();
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	}
	
	@RequestMapping("/authority_del")
	public void doDel(HttpServletRequest request,HttpServletResponse response){
		String authorityId =request.getParameter("param.keys");
		int result=service.deleteByPrimaryKey(authorityId);
		if(result>0){
			myresponse(response,true,"删除成功！");
		}
	}
	
	@RequestMapping("/authority_add")
	public String AddPage(){
		 return "authorities/addAuthorities";
	}
	
	@RequestMapping("/authority_doadd")
	public void doAdd(PubAuthorities record,HttpServletRequest request,HttpServletResponse response){
		int result=service.insert(record);
		if(result>0){
			myresponse(response,true,"新增成功！");
		}
	}
	
}
