package cn.demo.dao;

import java.util.List;

import cn.demo.model.PubResources;

public interface PageDAO {
	/**
	 * 主页下拉菜单
	 * @return
	 */
	public List<PubResources> getMenu(String useraccount);
	
	/**
	 * 所有分页资源
	 * @return
	 */
	public List<PubResources> getResource(int page,int row);
	
	/**
	 * 得到资源总数
	 * @return
	 */
	public int getAllResourceNum();
	
	/**
	 * 删除资源
	 * @param resourceId
	 * @return
	 */
	public int deleteResource(String resourceId);
	
	/**
	 * 新增资源
	 * @param pubResources
	 */
	public int addResource(PubResources pubResources);
	
	/**
	 * 修改资源
	 */
	public int updateResource(PubResources pubResources);
	
	/**
	 * 得到资源，用于下拉列表的选择
	 */
	public List<PubResources> getAllResourceId();
	
}
