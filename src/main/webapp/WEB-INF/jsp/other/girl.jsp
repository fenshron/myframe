<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"	src="<%=basePath%>common/js/plugins/webUI/jquery-1.7.2.js"></script>
<script>
$(function(){
	initData();
});
function initData(){
	console.info("initData()");
	$.ajax({  		    		
		url: "<%=path%>/user/girl.htm", 
		type : "post",
		cache:false,
		dataType:"json",
		success:function(data){
			for(var i=1;i<=10;i++){
				$("#weath"+i).children('li').remove();
			}
			for(var i=0;i<=10;i++){
			//alert(data.showapi_res_body.cityInfo.c1);
				$("#weath"+i).append(
						"<li>"+(data.showapi_res_body[i].title)+"</li>"+
						"<li><a target='_blank' href='"+(data.showapi_res_body[i].url)+"'><img src='"+(data.showapi_res_body[i].picUrl)+"'/></a></li>"+
						"<li>"+(data.showapi_res_body[i].description)+"</li>"
				);
			}
		},
		error:function(data){
			console.info("get girl images error!");
		}
	});
}

function reload(){
	initData();
}
</script>
<style>
ul{padding: 10px 18px;font-size: 13px;float: left; background-color:#E5F1E4;margin:10px 10px;
 -webkit-box-shadow:0 0 10px #335D5D;  
  -moz-box-shadow:0 0 10px #335D5D;  
  box-shadow:0 0 10px #335D5D;  }
li{list-style-type:none;color:#000; text-align: center;}
img{width: 190px;height: 290px;}
</style>
</head>
<body style="background-image: url('../common/images/weathbg.png');">
<h3 style="color:#fff;">易源接口-美女图片<a style="color:#FBAA53"  href="javascript:void(0)" onclick="reload()">刷新</a> </h3>
	<ul id="weath1"></ul>
	<ul id="weath2"></ul>
	<ul id="weath3"></ul>
	<ul id="weath4"></ul>
	<ul id="weath5"></ul>
	<ul id="weath6"></ul>
	<ul id="weath7"></ul>
	<ul id="weath8"></ul>
	<ul id="weath9"></ul>
	<ul id="weath10"></ul>
</body>
</html>