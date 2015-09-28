package cn.demo.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cn.demo.dao.UserDAO;
import cn.demo.model.PubRoles;
import cn.demo.model.PubUsers;
import cn.demo.service.UserService;

/**
 * 用户登录验证的类
 * @author Fengshirong
 *
 */
public class UserDetailsServiceImpl implements UserDetailsService{
	
 
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();  

		PubUsers users=userService.findUser(username);
 
		List<PubRoles> list =  userService.findRoleByName(username); 
          for (int i = 0; i < list.size(); i++) {  
          	auths.add(new SimpleGrantedAuthority(list.get(i).getRole_name()));
          }  
        		    		   		    
      boolean enables = true;
      boolean accountNonExpired = true;
      boolean credentialsNonExpired = true;
      boolean accountNonLocked = true;
      
      User userdetail = new User(users.getUser_account(), users.getUser_password(), enables, accountNonExpired, credentialsNonExpired, accountNonLocked, auths);
            
      return userdetail;
	}

 

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	

	 

}
