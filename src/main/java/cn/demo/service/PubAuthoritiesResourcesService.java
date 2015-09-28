package cn.demo.service;

import java.util.List;

import cn.demo.model.PubAuthorities;
import cn.demo.model.PubAuthoritiesResources;

public interface PubAuthoritiesResourcesService {
	 	int deleteByPrimaryKey(Integer id);

	    int insert(PubAuthoritiesResources record);

	    int insertSelective(PubAuthoritiesResources record);

	    PubAuthoritiesResources selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(PubAuthoritiesResources record);

	    int updateByPrimaryKey(PubAuthoritiesResources record);
	    
	    List<PubAuthoritiesResources> getAuthoritiesResourcesByPage(int page,int row);
	    
	    int getAllAuthoritiesResourcesNum();
}
