<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
  <head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <script type="text/javascript"	src="<%=basePath%>common/js/plugins/webUI/jquery-1.7.2.js"></script>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/loginfont.css">
    <script type="text/javascript">
      function changeValidateImg() {
	      document.getElementById('validateImg').src='/myframe/servlet/CheckCode?t='+new Date().getTime();
	  }  
      function validatetorLogin(){
    	  $('#user_login_loginForm').submit(); 
       }
	</script>
  </head>
<body>
	<div class="container">
	  <div class="profile">
	    <button class="profile__avatar" id="toggleProfile">
	     <img src="./common/images/headimg.jpg" alt="你的头像" /> 
	    </button>
	   <form id="user_login_loginForm" method="post" action="dologin.htm">
	    <div class="profile__form">
	      <div class="profile__fields">
	        <div class="field">
	          <input value="admin" type="text" id="fieldUser" name="userAccount" class="input" required pattern=.*\S.* />
	          <label for="fieldUser" class="label">用户名</label>
	        </div>
	        <div class="field">
	          <input value="admin" type="password" id="fieldPassword" name="userPassword" class="input" required pattern=.*\S.* />
	          <label for="fieldPassword" class="label">密码</label>
	        </div>
	          <c:if test="${sessionScope.showCheckCode==1}">
	         <div class="field" style="width: 35%;float:left;">
	          <input type="text" id="fieldvalidate" name="validateCode" class="input" required pattern=.*\S.* />
	          <label for="fieldvalidate" class="label">验证码</label>
	        </div>
	        <div class="field" style="width: 65%;float:left; height: 32px;">
	       
			      <div style="margin: 10px;">
				   <div>
			            <img id="validateImg" alt="无法显示验证码" src="/myframe/servlet/CheckCode" onclick="changeValidateImg();return false;"/>
			            <a href="javascript:void(0);" onclick="changeValidateImg();return false;" style="color: #497BE8;font-size: 10px;">换一张</a>
			        </div>
			       </div>
	        </div>
	        </c:if>
	        <div class="profile__footer">
	          	<button class="btn" onclick="validatetorLogin()">登录</button>
	        </div>
	        <c:if test="${sessionScope.showCheckCode==1}">
			<font color="#497BE8" style="font-size: 15px;"> ${sessionScope['SECURITY_LOGIN_EXCEPTION']}</font>
			</c:if>		           
	      </div>
	     </div>
	     </form>
	  </div>
	</div>
	<script src="./js/indexdefault.js"></script>
</body>
</html>
