package cn.demo.service;

import java.util.List;

import cn.demo.model.PubAuthorities;

public interface PubAuthoritiesService {

	int deleteByPrimaryKey(String authorityId);

    int insert(PubAuthorities record);

    int insertSelective(PubAuthorities record);

    PubAuthorities selectByPrimaryKey(String authorityId);

    int updateByPrimaryKeySelective(PubAuthorities record);

    int updateByPrimaryKey(PubAuthorities record);
    
    List<PubAuthorities> getAuthoritiesByPage(int page,int row);
    
    int getAllAuthoritiesNum();
}
