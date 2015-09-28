package cn.demo.security.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cn.demo.dao.UserDAO;
import cn.demo.model.PubUsers;
import cn.demo.security.util.MD5Utils;
import cn.demo.service.UserService;

public class MyUsernamePasswordAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {
	public static final String VALIDATE_CODE = "validateCode";  
    public static final String USERNAME = "userAccount";  
    public static final String PASSWORD = "userPassword";  
    private int showCheckCode = 0;
    
 	public int getShowCheckCode() {
 		return showCheckCode;
	}

	public void setShowCheckCode(int showCheckCode) {
		this.showCheckCode = showCheckCode;
	}

	private MD5Utils md5 =new MD5Utils();
      
	@Resource
    private UserDAO userDAO;  

	@Resource
	private UserService userService;
	 
	
	public MD5Utils getMd5() {
		return md5;
	}

	public void setMd5(MD5Utils md5) {
		this.md5 = md5;
	}

	@Override  
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {  
        if (!request.getMethod().equals("POST")) {  
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());  
        }  
        //检测验证码  
        checkValidateCode(request);  
          
        String username = obtainUsername(request);  
        String password = obtainPassword(request);  
          
        //验证用户账号与密码是否对应  
        username = username.trim();  
          
        PubUsers users=userService.findUser(username);      
        HttpSession session = request.getSession();
        session = request.getSession(false);//false代表不创建新的session，直接获取当前的session
        
        //将用户名存进session，如果登录成功，显示在主页
        session.setAttribute("login_account",username);
        
        if(users == null) {       	 

         	session.setAttribute("showCheckCode" ,"1" );  
         	session.setAttribute("SECURITY_LOGIN_EXCEPTION" ,
					"用户名或密码错误！" );  
         	
      	
            throw new AuthenticationServiceException("用户名或密码错误！"); 
            
        }else if(users.getUser_password()=="" || users.getUser_password()==null){
        	
        	session.setAttribute("showCheckCode" ,"1" );  
        	session.setAttribute("SECURITY_LOGIN_EXCEPTION" ,
					"用户名或密码错误！" ); 
        	
            throw new AuthenticationServiceException("用户名或密码错误！");
            
        	
        }else if(!users.getUser_password().equals(md5.MD5Encode(password))){// password加密后再进行验证
   	
         	session.setAttribute("showCheckCode" ,"1" ); 
         	session.setAttribute("SECURITY_LOGIN_EXCEPTION" ,
					"用户名或密码错误！" ); 
 	
            throw new AuthenticationServiceException("用户名或密码错误！");
        
        }else{
        	
        	if(session.getAttribute("showCheckCode")=="1"){
        	    session.setAttribute("showCheckCode" ,	"0" ); 
        	}
        	session.setAttribute("useraccount",users.getUser_account());
        }
        
        
      
        //UsernamePasswordAuthenticationToken实现 Authentication  
        //这里要注意了，我第二个参数是用自己的md5加密了密码再去传参的，因为我的密码都是加密后存进数据库的。
        //如果这里不加密，那么和在数据库取出来的不匹配，最终即使登录账号和密码都正确，也将无法登录成功。
        //因为在AbstractUserDetailsAuthenticationProvider里还会对用户和密码验证，分别是
        //user = retrieveUser(username, (UsernamePasswordAuthenticationToken) authentication);//这个通过才能顺利通过
        //另一个是 additionalAuthenticationChecks(user, (UsernamePasswordAuthenticationToken) authentication);//如果retrieveUser方法验证不通过，将无法访问
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, md5.MD5Encode(password));  
        // Place the last username attempted into HttpSession for views  
          
        // 允许子类设置详细属性  
        setDetails(request, authRequest);  
          
       
        
        // 运行UserDetailsService的loadUserByUsername 再次封装Authentication  
        return this.getAuthenticationManager().authenticate(authRequest);  
    }  
      
    protected void checkValidateCode(HttpServletRequest request) {   
        HttpSession session = request.getSession();  
          
        String sessionValidateCode = obtainSessionValidateCode(session);   
       
       if( session.getAttribute("showCheckCode")=="1"){
        
        //让上一次的验证码失效  
          session.setAttribute(VALIDATE_CODE, null);  
          String validateCodeParameter = obtainValidateCodeParameter(request); 
          
          //判断输入的验证码和保存在session中的验证码是否相同，这里不区分大小写进行验证
          if (StringUtils.isEmpty(validateCodeParameter) || !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {    
         
        	session = request.getSession(false);//false代表不创建新的session，直接获取当前的session
        	session.setAttribute("SECURITY_LOGIN_EXCEPTION" ,
					"验证码错误" );   
        	
        	throw new AuthenticationServiceException("验证码错误！");   
           }  
       }
    }  
      
    private String obtainValidateCodeParameter(HttpServletRequest request) {  
        Object obj = request.getParameter(VALIDATE_CODE);  
        return null == obj ? "" : obj.toString();  
    }  
  
    protected String obtainSessionValidateCode(HttpSession session) {  
        Object obj = session.getAttribute(VALIDATE_CODE);  
        return null == obj ? "" : obj.toString();  
    }  
  
    @Override  
    protected String obtainUsername(HttpServletRequest request) {  
        Object obj = request.getParameter(USERNAME);  
        return null == obj ? "" : obj.toString();  
    }  
  
    @Override  
    protected String obtainPassword(HttpServletRequest request) {  
        Object obj = request.getParameter(PASSWORD);  
        return null == obj ? "" : obj.toString();  
    }

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

   
}  
