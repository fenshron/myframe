package cn.example.test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.tribes.util.UUIDGenerator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.demo.controller.MenuController;
import cn.demo.model.PubResources;
import cn.demo.model.PubRoles;
import cn.demo.model.PubUsers;
import cn.demo.model.User;
import cn.demo.pageModel.ResourcesMenu;
import cn.demo.service.PageService;
import cn.demo.service.UserService;


public class crudTest {


	private UserService userService;
	
	private PageService pageService;

	@Before
	public void before(){                                                                    
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring-mybatis.xml"});
		userService = (UserService) context.getBean("userService");
		pageService = (PageService) context.getBean("pageService");
	}
	
	@Test
	public void addUser(){
		User user = new User();
		user.setName("测试3");
		int insertnum=userService.insert(user);
		System.out.println(insertnum);
	}
	
	@Test
	public void findRoleName(){
		List<PubRoles> roleIntems = userService.findRoleName();
		for(PubRoles role:roleIntems){
			System.out.println(role.getRole_name());
		}
	}
	
	@Test
	public void findRoleResources(){
		String roleName="ROLE_ADMIN";
		List<PubResources> resources= userService.findResource(roleName);
		for(PubResources resource:resources){
			System.out.println(resource.getResource_name());
		}
	}
	
	@Test
	public void findUsers(HttpServletRequest request){
//		PubUsers users=userService.findUser("admin");
//		System.out.println(users.getUser_account());
		 String url= request.getRealPath("/");
		 System.out.println(url);
	}
	
	@Test
	public void findRoleByName(){
		List<PubRoles> pubRoles = userService.findRoleByName("admin");
		for(PubRoles resource:pubRoles){
			System.out.println(resource.getRole_name());
		}
	}
	
	@Test
	public void getMenu(){
//		MenuController c=new MenuController();
//		List<ResourcesMenu> list = c.getTreeMenu();
//		List<PubResources> pubResources = pageService.getMenu();
//		
//		for(PubResources resource:pubResources){
//			System.out.println(resource.getResource_string());
//		}
	}
	
	@Test
	public void insertOBJ(){
		PubResources pubResources=new PubResources();
		pubResources.setEnabled("1");
		pubResources.setIssys(1);
		pubResources.setPid("role");
		pubResources.setPriority("0");
		pubResources.setResource_desc("test");
		pubResources.setResource_id("test");
		pubResources.setResource_name("test");
		pubResources.setResource_string("ttest");
		pubResources.setResource_type("jsp");
		 pageService.insert(pubResources);
	}
	 
	
	public static void main(String[] args) {
		 UUIDGenerator uuid=new UUIDGenerator();
		System.out.println(uuid.randomUUID(true));
	}
}
