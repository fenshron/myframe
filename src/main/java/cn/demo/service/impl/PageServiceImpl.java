package cn.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.demo.model.PubResources;
import cn.demo.service.PageService;

@Service("pageService")
public class PageServiceImpl extends BaseServiceImpl implements PageService {

	@Override
	public int deleteById(Object pk) {
		return pageDAO.deleteResource((String)pk);
	}
	
	@Override
	public int insert(Object t) {
		return pageDAO.addResource((PubResources)t);
	}
	
	@Override
	public int updateByObj(Object t) {
		return pageDAO.updateResource((PubResources)t);
	}
	
	@Override
	public List<PubResources> getMenu(String useraccount) {
		return pageDAO.getMenu(useraccount);
	}

	@Override
	public List<PubResources> getResource(int page,int row) {
		return pageDAO.getResource(page,row);
	}

	@Override
	public int getAllResourceNum() {
		return pageDAO.getAllResourceNum();
	}
	
	@Override
	public List<PubResources> getAllResourceId(){
		return pageDAO.getAllResourceId();
	}

//	@Override
//	public int deleteResource(String resourceId) {
//		return pageDAO.deleteResource(resourceId);
//	}
//
//	@Override
//	public int addResource(PubResources pubResources) {
//		return pageDAO.addResource(pubResources);
//	}

}
