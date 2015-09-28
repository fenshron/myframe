package cn.demo.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import cn.demo.dao.UserDAO;
import cn.demo.model.PubResources;
import cn.demo.model.PubRoles;
import cn.demo.security.util.UrlPathMatcher;
import cn.demo.service.UserService;

/**
 * 负责读取数据库中的url对应的权限
 * @author Fengshirong
 *
 */
public class SecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource  {
	
	/*
	  *resourceMap用static声明了，为了避免用户每请求一次都要去数据库读取资源、权限，这里只读取一次，将它保存起来
	  */
	  private static Map<String, Collection<ConfigAttribute>> resourceMap = null; 
	  
	  private UrlPathMatcher urlMatcher = new UrlPathMatcher();
	  
	  @Resource
	  private UserService userService;

	  // 1
	  //构造函数，因为服务器启动时会调用这个类，利用构造函数读取所有的url、角色
	  public SecurityMetadataSourceImpl(UserService userService) {   
	       //初始化，读取数据库所有的url、角色
		  this.userService=userService;
	       loadResourceDefine();  
	  }
	  
	  //2
	  //这个方法应该是要从数据库读取数据的，这里只用来测试
	  /*private void loadResourceDefine() {  
	    
	    System.out.println("metadata : loadResourceDefine");
	      resourceMap = new HashMap<String, Collection<ConfigAttribute>>();		   
	      Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();		   
	    
	    *//**
	     * 将这里的new SecurityConfig("ROLE_ADMIN")值改为ROLE_USER，登录成功也不允许访问index.jsp了,
	     * 因为在applicationContext-security.xml设置了只允许角色为ROLE_ADMIN的访问。
	     *  <intercept-url pattern="/**" access="ROLE_ADMIN" />
	     *//*
	      ConfigAttribute ca = new SecurityConfig("ROLE_ADMIN");		   
	      atts.add(ca);		   
	     
	      //ca为访问的权限，下面为url地址赋予ca中的权限
	      resourceMap.put("/i.jsp", atts);   
	      resourceMap.put("/index.jsp", atts);		  
	  
	  }*/
	  
	  //这个方法在url请求时才会调用，服务器启动时不会执行这个方法，前提是需要在<http>标签内设置  <custom-filter>标签
	  //getAttributes这个方法会根据你的请求路径去获取这个路径应该是有哪些权限才可以去访问。
	  @Override
	  public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
	  
	    //object getRequestUrl 是获取用户请求的url地址		
	    String url = ((FilterInvocation) object).getRequestUrl();   
	    
	    //resourceMap保存了loadResourceDefine方法加载进来的数据
	    Iterator<String> ite = resourceMap.keySet().iterator();   
	    
	    while (ite.hasNext()) {	 
	       
	       //取出resourceMap中读取数据库的url地址
	       String resURL = ite.next();   
	    
	       //如果两个 url地址相同，那么将返回resourceMap中对应的权限集合，然后跳转到MyAccessDecisionManager类里的decide方法，再判断权限
	      if (urlMatcher.pathMatchesUrl(url, resURL)) {
	         return resourceMap.get(resURL);   //返回对应的url地址的权限 ，resourceMap是一个主键为地址，值为权限的集合对象
	      }  
	    }
	    
	    //如果上面的两个url地址没有匹配，返回return null，不再调用MyAccessDecisionManager类里的decide方法进行权限验证，代表允许访问页面
	    return null;				
	  }

	  // 4
	  @Override
	  public Collection<ConfigAttribute> getAllConfigAttributes() {
	  
	    return null;
	  }
	  
	  // 3
	  @Override 
	  public boolean supports(Class<?> clazz) {
	  
	    return true;
	    
	  }
	  
	  private void loadResourceDefine() {
	   List<PubRoles> rolesItems = userService.findRoleName();
	   resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
	    for(PubRoles auth:rolesItems){
	    	ConfigAttribute ca = new SecurityConfig(auth.getRole_name());
	    	List<PubResources> resources = userService.findResource(auth.getRole_name());
	    	for(PubResources res : resources){
	    		 String url = res.getResource_string();
	    		 if (resourceMap.containsKey(url)) {
	    			 Collection<ConfigAttribute> value = resourceMap.get(url); //取出这个url的权限集合
	    			 value.add(ca);
	    			 resourceMap.put(url, value);
	    		 }else {
	    			 Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
	    			 atts.add(ca);
	    			 resourceMap.put(url, atts);
	    		 }
	    	}
	    	
	    }
	  }

 

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	  
	
}
