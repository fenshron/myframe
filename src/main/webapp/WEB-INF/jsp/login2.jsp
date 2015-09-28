<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>用户登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet"	href="<%=basePath%>common/js/plugins/webUI/jquery-easyui/themes/default/easyui.css" type="text/css"></link>
	<link rel="stylesheet"	href="<%=basePath%>common/js/plugins/webUI/jquery-easyui/themes/icon.css"	type="text/css"></link>
	<script type="text/javascript"	src="<%=basePath%>common/js/plugins/webUI/jquery-1.7.2.js"></script>
	<script type="text/javascript"	src="<%=basePath%>common/js/plugins/webUI/jquery-easyui/jquery.easyui.min.js"></script>
	
	 <style>
       body{
		  font-size:12px; 
		   padding:0px;
		   margin:0px;
		 
	   }
   
	   #login_div{
		   
		   margin: auto;
           position: absolute;
           top: 0; left: 0; bottom: 0; right: 0;
		    
		  
		   background-image:url(login_logo.jpg);
		 
		   width:800px;
           height:407px;
	   }
	   
       #login_div form label{
		   font-weight:bold
		   
		}
	
	    #login_div form{
			margin-top:100px;
			margin-left:420px;
		}
		
   </style>
    
   <script type="text/javascript">
       function changeValidateImg() {
	      document.getElementById('validateImg').src='/myframe/servlet/CheckCode?t='+new Date().getTime();
	  }  
       function validatetorLogin(){
    	   $('#user_login_loginForm').submit(); 
       	     
       }
   </script>
   
  </head>
  
  <body onload="window.document.forms[0].login_username.focus();">
     
      <div id="login_div">
          
         <form id="user_login_loginForm" method="post" action="dologin.htm">
         	 <label style="margin-left:50px;font-size:16px;">管理员登录</label><br/>
          	 <div style="margin: 10px;">
             <label>账号：</label> 
           	 <input class="easyui-textbox" type="text" name="userAccount" value="admin" id="login_username" data-options="required:true"/> 
             </div>
             <div style="margin: 10px;">
             <label>密码：</label>
             <input class="easyui-textbox" type="password" name="userPassword" value="admin" data-options="type:'password'" />
             </div>
            	    
          <c:if test="${sessionScope.showCheckCode==1}">
		      
		      <div style="margin: 10px;">
			
			   <div>
				<label>验证码:</label>
		   	         
			        <input  class="easyui-textbox"  id="validateCode" name="validateCode" type="text" id="vCode" style="width:118px;" tabindex="3" maxlength="4" />	
			        
		            <img id="validateImg" alt="无法显示验证码" src="/myframe/servlet/CheckCode" onclick="changeValidateImg();return false;"/>
		           
		            <a href="javascript:void(0);" onclick="changeValidateImg();return false;">换一张</a>
			       
			        <div style="margin-top:5px;margin-left:50px;">
			            <font color="red"> ${sessionScope['SECURITY_LOGIN_EXCEPTION']}</font>		           
			        </div>
			         
		        </div>
	    
		       
		       </div>
		
		 </c:if>
        	 <a id="btn" href="javascript:void(0)" onclick="validatetorLogin()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">登录</a>  
         </form>
             
     </div>
  </body>
</html>
