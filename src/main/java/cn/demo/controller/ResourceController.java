package cn.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.util.UUIDGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.demo.model.PubResources;
import cn.demo.service.PageService;
import cn.demo.util.AlterModel;
import cn.demo.util.ComboboxModel;
import cn.demo.util.DataGrid;

@Controller
@RequestMapping("/admin")
public class ResourceController extends BaseController {
	
	private static Logger logger = Logger.getLogger(ResourceController.class);  

	@Autowired
	private PageService pageService;
	
	@RequestMapping("/resource")
	public String resource(){
		return "resource/resource";
	}

	@RequestMapping("/allResource")
	public void getAllResource(HttpServletRequest request, HttpServletResponse response) {

        int rows = Integer.parseInt(request.getParameter("rows"));
		int page = (Integer.parseInt(request.getParameter("page"))-1)*rows;
		List<PubResources> pubResources = pageService.getResource(page,rows);
		DataGrid dg = new DataGrid();
		dg.setTotal(pageService.getAllResourceNum());
		dg.setRows(pubResources);
		try {
			response.getWriter().write(super.writeJson(dg));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/deleteResource")
	public void deleteResource(HttpServletRequest request, HttpServletResponse response){
		String resourceId =request.getParameter("param.keys");
		int deletenum = pageService.deleteById(resourceId);
//		AlterModel  alterModel = new AlterModel();
//		alterModel.setFlag(true);
//		alterModel.setMsg("删除成功！");
//		try {
//			response.getWriter().write(super.writeJson(alterModel));
//			response.getWriter().flush();
//			response.getWriter().close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		myresponse(response, true,"删除成功！");
		logger.info("delete pub_Resource table num:"+deletenum+",resource_id="+resourceId);
	}
	
	@RequestMapping("/addResourcePage")
	public String addResourcePage(){
		return "resource/addResource";
	}
	
	@RequestMapping("/doAddResource")
	public void doAddResource(PubResources pubResources,HttpServletResponse response){
		UUIDGenerator uuid=new UUIDGenerator();
		pubResources.setResource_id( uuid.randomUUID(true).toString());
		int resultNum = pageService.insert(pubResources);
//		AlterModel  alterModel = new AlterModel();
//		alterModel.setFlag(true);
//		alterModel.setMsg("插入成功！");
		if(resultNum>0){
//			try {
//				response.getWriter().write(super.writeJson(alterModel));
//				response.getWriter().flush();
//				response.getWriter().close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			myresponse(response, true,"插入成功！");
		}
	}
	
	@RequestMapping("/modResourcePage")
	public String modResourcePage(){
		return "resource/modResource";
	}
	
	@RequestMapping("/doModResource")
	public void doModResource(PubResources pubResources,HttpServletResponse response){
		int resultNum = pageService.updateByObj(pubResources);
//		AlterModel  alterModel = new AlterModel();
//		alterModel.setFlag(true);
//		alterModel.setMsg("修改成功！");
		if(resultNum>0){
//			try {
//				response.getWriter().write(super.writeJson(alterModel));
//				response.getWriter().flush();
//				response.getWriter().close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			myresponse(response, true,"修改成功！");
		}
	}
	
	
	@RequestMapping("/combobox")
	public void getResourceId(HttpServletResponse response){
		List<PubResources> pubresources=pageService.getAllResourceId();
		
		List<ComboboxModel> comboboxmodels =new ArrayList<ComboboxModel>();
		for(PubResources resource:pubresources){
			ComboboxModel comboboxmodel =new ComboboxModel();
			comboboxmodel.setId(resource.getResource_id());
			comboboxmodel.setText(resource.getResource_name());
			comboboxmodels.add(comboboxmodel);
		}
		try {
			response.getWriter().write(super.writeJson(comboboxmodels));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
