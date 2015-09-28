package cn.demo.dao;

import java.util.List;

import cn.demo.model.PubResources;
import cn.demo.model.PubRoles;
import cn.demo.model.PubUsers;
import cn.demo.model.User;

public interface UserDAO extends BaseDAO<User,Integer> {

	/**
	 * 添加新用户
	 * @param user
	 * @return
	 */
	public int insertUser(User user);
	
	/**
	 * 查询用户情况（security有自己的登陆验证不使用这个）
	 * @param username
	 * @return
	 */
	public User findAuthByUsername(String username);
	
	/**
	 * 查询所有角色
	 * @return
	 */
	public List<PubRoles> findRoleName();
	
	/**
	 * 查询用户拥有的角色
	 * @param username
	 * @return
	 */
	public List<PubRoles> findRoleByName(String username);
	
	/**
	 * 查询用户
	 * @param userName
	 * @return
	 */
	public PubUsers findUsers(String userName);
	
	/**
	 * 查询用户拥有的权限地址
	 * @param roleName
	 * @return
	 */
	public List<PubResources> findResource(String roleName);
	
	
}
