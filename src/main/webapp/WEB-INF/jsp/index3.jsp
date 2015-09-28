<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<jsp:include page="baseJsCss.jsp"></jsp:include>
	
	<style>

       .l-link {font-size:18px;display:block; line-height:30px; height:25px; padding-left:5px; border:1px solid white; margin:4px; text-decoration:none }

       .l-topmenu-welcome{position:absolute; height:24px; line-height:24px;  right:30px; top:5px;color:#070A0C; }
       .l-topmenu-welcome a{ color:blue; text-decoration:underline;font-size:15px} 
   
       #account_div{
           position:absolute;
           right:30px;
           font-size:14px;
      
           top:28px;
       }
       
    </style>
 
	  
  <body>
       
       
       <div class="easyui-layout" fit="true">
        <div data-options="region:'north'" style="height:50px">
     
           <div class="l-topmenu-welcome">
              <a href="<%=basePath%>login.htm" >登录</a>
              <span>|</span> 
           
              <a href="javascript:void(0)" onclick="openChangePW()">修改密码</a>
              <span>|</span>              
             
              <a href="<%=basePath%>logout.htm">安全退出</a>
           </div>
           
           <div id="account_div">
               <label>登录账号：${sessionScope['login_account']}</label> 
           </div>
        
        </div>
        
        <div data-options="region:'south',split:true" style="height:50px;"></div>
         <!--
        <div data-options="region:'east',split:true" title="East" style="width:100px;"></div>
        -->
        <div data-options="region:'west',split:true" title="West" style="width:220px;">
          
        <div class="easyui-accordion" style="" fit="true">
        
          <div title="导航" data-options="iconCls:'icon-search'" style="padding:20px;" >
           
            <ul class="easyui-tree" id="menutree"  url="/myframe/pageMenu.htm"  data-options="lines:true,parentField:'pid'"></ul>
          
          </div>
          
          <div title="test"></div>
        </div>
       
     </div>
   
        
        <div data-options="region:'center',title:'',iconCls:'icon-ok'">
            <div class="easyui-tabs" style="" fit="true" id="tab">
            
            <div title="主页" style="padding:10px">
            </div>
           
            </div>
        
        </div>
    
      </div>
  </body>
</html>
