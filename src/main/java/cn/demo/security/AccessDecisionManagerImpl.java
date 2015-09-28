package cn.demo.security;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
/**
 * 验证资源跟角色之间的关系
 * @author Fengshirong
 *
 */
public class AccessDecisionManagerImpl implements AccessDecisionManager {

	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> attributes)
			throws AccessDeniedException, InsufficientAuthenticationException {

		if(null==attributes){
			return;
		}
		
		for(ConfigAttribute attribute : attributes){
			String needRole = ((SecurityConfig) attribute).getAttribute();
			for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {  
                if (needRole.equals(grantedAuthority.getAuthority())){  
                    return;  
                }
            }  
		}
		throw new AccessDeniedException("权限不足!");
	}

	public boolean supports(ConfigAttribute arg0) {
		return true;
	}

	public boolean supports(Class<?> arg0) {
		return true;
	}

}
