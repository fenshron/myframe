package cn.demo.service;

import java.util.List;

import cn.demo.model.PubResources;
import cn.demo.model.PubRoles;
import cn.demo.model.PubUsers;


public interface UserService extends BaseService<Object, Object>{

	public List<PubRoles> findRoleName();
	
	public List<PubRoles> findRoleByName(String userName);
	
	public PubUsers findUser(String userName);
	
	public List<PubResources> findResource(String roleName);
}
