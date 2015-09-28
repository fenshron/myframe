package cn.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.demo.dao.PubAuthoritiesResourcesDAO;
import cn.demo.model.PubAuthorities;
import cn.demo.model.PubAuthoritiesResources;
import cn.demo.service.PubAuthoritiesResourcesService;
@Service("pubAuthoritiesResourcesService")
public class PubAuthoritiesResourcesServiceImpl implements
		PubAuthoritiesResourcesService {

	@Autowired
	private PubAuthoritiesResourcesDAO  dao;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(PubAuthoritiesResources record) {
		return dao.insert(record);
	}

	@Override
	public int insertSelective(PubAuthoritiesResources record) {
		return insertSelective(record);
	}

	@Override
	public PubAuthoritiesResources selectByPrimaryKey(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(PubAuthoritiesResources record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(PubAuthoritiesResources record) {
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public List<PubAuthoritiesResources> getAuthoritiesResourcesByPage(int page, int row) {
		return dao.getAuthoritiesResourcesByPage(page, row);
	}

	@Override
	public int getAllAuthoritiesResourcesNum() {
		return dao.getAllAuthoritiesResourcesNum();
	}

}
