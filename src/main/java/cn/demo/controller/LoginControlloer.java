package cn.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginControlloer {
	
	private static Logger logger = Logger.getLogger(LoginControlloer.class);  
	
	@RequestMapping("index/going")
	public String dologin(HttpServletRequest request){
	    HttpSession session = request.getSession();
		session = request.getSession(false);
		if(session.getAttribute("useraccount")!=null&&!"".equals(session.getAttribute("useraccount"))){
			return "index";
		}else{
			return "sessionTimeOut";
		}
	}
	
	@RequestMapping("login")
	public String login(HttpServletRequest request){
		 HttpSession session = request.getSession();
		 session = request.getSession(false);
		if(session.getAttribute("useraccount")!=null){
			return "redirect:/index/going.htm";
		}else{
			return "login";	
		}
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
		 HttpSession session = request.getSession();
		 session = request.getSession(false);
		 session.setAttribute("useraccount", null);
		return "login";
	}
	
	@RequestMapping("sessiontimeout")
	public String sessiontimeout(){
		return "sessionTimeOut";
	}

}
