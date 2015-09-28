package cn.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.demo.dao.PubAuthoritiesDAO;
import cn.demo.model.PubAuthorities;
import cn.demo.service.PubAuthoritiesService;
@Service("pubAuthoritiesService")
public class PubAuthoritiesServiceImpl implements PubAuthoritiesService {

	@Autowired
	public PubAuthoritiesDAO pubAuthoritiesDAO;
	
	@Override
	public int deleteByPrimaryKey(String authorityId) {
		return 	pubAuthoritiesDAO.deleteByPrimaryKey(authorityId);
	}

	@Override
	public int insert(PubAuthorities record) {
		return pubAuthoritiesDAO.insert(record);
	}

	@Override
	public int insertSelective(PubAuthorities record) {
		return pubAuthoritiesDAO.insertSelective(record);
	}

	@Override
	public PubAuthorities selectByPrimaryKey(String authorityId) {
		return pubAuthoritiesDAO.selectByPrimaryKey(authorityId);
	}

	@Override
	public int updateByPrimaryKeySelective(PubAuthorities record) {
		return pubAuthoritiesDAO.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(PubAuthorities record) {
		return pubAuthoritiesDAO.updateByPrimaryKey(record);
	}

	@Override
	public List<PubAuthorities> getAuthoritiesByPage(int page, int row) {
		return pubAuthoritiesDAO.getAuthoritiesByPage(page, row);
	}

	@Override
	public int getAllAuthoritiesNum() {
		return pubAuthoritiesDAO.getAllAuthoritiesNum();
	}

}
