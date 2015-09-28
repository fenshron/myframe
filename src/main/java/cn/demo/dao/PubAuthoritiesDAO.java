package cn.demo.dao;

import java.util.List;

import cn.demo.model.PubAuthorities;


public interface PubAuthoritiesDAO {
    int deleteByPrimaryKey(String authorityId);

    int insert(PubAuthorities record);

    int insertSelective(PubAuthorities record);

    PubAuthorities selectByPrimaryKey(String authorityId);

    int updateByPrimaryKeySelective(PubAuthorities record);

    int updateByPrimaryKey(PubAuthorities record);
    
    public List<PubAuthorities> getAuthoritiesByPage(int page,int row);
    
    public int getAllAuthoritiesNum();
}