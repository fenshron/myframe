package cn.demo.service;

import java.util.List;

import cn.demo.model.PubResources;
import cn.demo.model.User;

public interface PageService extends BaseService<Object, Object> {
	
	public List<PubResources> getMenu(String useraccount);
	
	public List<PubResources> getResource(int page,int row);
	
	public int getAllResourceNum();
	
	public List<PubResources> getAllResourceId(); 
	
	//public int deleteResource(String resourceId);
	
	//public int addResource(PubResources pubResources);

}
