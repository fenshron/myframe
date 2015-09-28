package cn.demo.dao;

import java.util.List;

import cn.demo.model.PubAuthorities;
import cn.demo.model.PubAuthoritiesResources;

public interface PubAuthoritiesResourcesDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(PubAuthoritiesResources record);

    int insertSelective(PubAuthoritiesResources record);

    PubAuthoritiesResources selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PubAuthoritiesResources record);

    int updateByPrimaryKey(PubAuthoritiesResources record);
    
    public List<PubAuthoritiesResources> getAuthoritiesResourcesByPage(int page,int row);
    
    public int getAllAuthoritiesResourcesNum();
}