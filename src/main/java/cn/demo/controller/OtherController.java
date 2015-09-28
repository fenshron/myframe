package cn.demo.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.squareup.okhttp.Response;

import cn.demo.util.Mail;
import cn.demo.util.Weather;

@Controller
@RequestMapping("/user")
public class OtherController  extends BaseController {
	private static Logger logger = Logger.getLogger(OtherController.class);  
	@RequestMapping("/showtime")
	public void showtime(HttpServletResponse response){
		Date date=new Date();
		int hour=date.getHours();
		try {
			if(hour < 6){
				response.getWriter().write("凌晨好！");
			}else if(hour < 9){
				response.getWriter().write("早上好！");
			}else if(hour < 12){
				response.getWriter().write("上午好！");
			}else if(hour < 14){
				response.getWriter().write("中午好！");
			}else if(hour < 17){
				response.getWriter().write("下午好！");
			}else if(hour < 19){
				response.getWriter().write("傍晚好！");
			}else if(hour < 22){
				response.getWriter().write("晚上好！");
			}else{
				response.getWriter().write("夜里好！");
			}
		} catch (IOException e) {
			logger.error("获取时间段出错："+e);
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/echarts")
	public String echarts(){
		return "echarts/showdata";
		
	}
	
	@RequestMapping("/map")
	public String map(){
		return "other/map";
	}
	
	@RequestMapping("/makepoint")
	public String makepoint(){
		return "echarts/makepoint";
	}
	
	@RequestMapping("/removeaddress")
	public String removeaddress(){
		return "echarts/removeaddress";
	}
	
	@RequestMapping("/email")
	public String email(){
		return "email/email";
	}
	
	@RequestMapping("/uploadFile")
	public String upload(){
		return "email/uploadFile";
	}
	
	@RequestMapping("/bar1")
	public String bar1(){
		return "echarts/bar1";
	}
	
	@RequestMapping("/sendemail")
	public void sendemail(HttpServletRequest request,HttpServletResponse response){
		String smtp = Mail.SMTP_QQ;  
		String from = "502109651@qq.com";
 	    String username = "502109651@qq.com";  
 	    String password = "loading__==....4";  
 	    String to = request.getParameter("to");
 	    String copyto =request.getParameter("copyto");
 	    String subject = request.getParameter("subject");
 	    String content = request.getParameter("content");
 	    String url=request.getParameter("filename");
 	    String filename="";
 	    if(!"".equals(url)){
 	    	filename=request.getRealPath("/")+(url.replace("/", "\\"));
 	    }
 	  //  String filename = "C:"+File.separator +"Users"+File.separator +"feng-pc"+File.separator +"Desktop"+File.separator +"loginfont.css"; 
 	   boolean result=false;
 	    if(!"".equals(filename)&&!"".equals(copyto)){
 	    	  result = Mail.sendAndCc(smtp, from, to, copyto, subject, content, username, password, filename);  
 	    }
 	    
 	   if("".equals(filename)&&!"".equals(copyto)){
	    	  result = Mail.sendAndCc(smtp, from, to, copyto, content, username, password, filename);  
	    }
       if("".equals(filename)&&"".equals(copyto)){
    	  result = Mail.send(smtp, from, to, content, username, password, filename);  
 	  	}
	   
	    if(result){
	    	myresponse(response, true, "邮件发送成功！");
	    }else{
	    	myresponse(response, true, "邮件发送失败！");
	    }
	}
	
	@RequestMapping("/weather")
	public String weather(){
		return "other/weather";
	}
	
	@RequestMapping("/cityweath")
	public void getCityWeath(HttpServletResponse response){
		Weather w=new Weather();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String newtime=format.format(new Date());
		try {
			String result=w.getCityWeather("101280601", "深圳",newtime);
			response.getWriter().write(result);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/girlpage")
	public String girlpage(){
		return "other/girl";
	}
	
	@RequestMapping("/girl")
	public void girlImg(HttpServletResponse response) throws IOException{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String newtime=format.format(new Date());
		URL u=new URL("http://route.showapi.com/197-1?showapi_appid=10261&showapi_timestamp="+newtime+"&num=20&page=1&showapi_sign=09ce6a76e9a749c0be4c4eba6b890671");
		InputStream in=u.openStream();
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		try {
			byte buf[]=new byte[1024];
			int read = 0;
			while ((read = in.read(buf)) > 0) {
				out.write(buf, 0, read);
			}
		}  finally {
			if (in != null) {
				in.close();
			}
		}
		byte b[]=out.toByteArray( );
		String result=new String(b,"utf-8");
		response.getWriter().write(result);
		response.getWriter().flush();
		response.getWriter().close();
	}

}
