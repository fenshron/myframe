package cn.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.demo.model.PubResources;
import cn.demo.model.PubRoles;
import cn.demo.model.PubUsers;
import cn.demo.model.User;
import cn.demo.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	
	@Override
	public int insert(Object t) {
		return userDAO.insert((User)t);
	}
	
	@Override
	public List<PubRoles> findRoleName() {
		return userDAO.findRoleName();
	}

	@Override
	public List<PubResources> findResource(String roleName) {
		return userDAO.findResource(roleName);
	}

	@Override
	public PubUsers findUser(String userName) {
		return userDAO.findUsers(userName);
	}

	@Override
	public List<PubRoles> findRoleByName(String userName) {
		return userDAO.findRoleByName(userName);
	}
}
