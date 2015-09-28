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
	window.setInterval("initData()",1000000); 
});
function initData(){
	console.info("initData()");
	$.ajax({  		    		
		url: "<%=path%>/user/cityweath.htm", 
		type : "post",
		cache:false,
		dataType:"json",
		success:function(data){
			$("#weath").children('li').remove();
			$("#weath2").children('li').remove();
			$("#weath3").children('li').remove();
			$("#weath4").children('li').remove();
			 
			//alert(data.showapi_res_body.cityInfo.c1);
			$("#weath").append(
					"<li>"+(data.showapi_res_body.time).substring(0,4)+"-"+(data.showapi_res_body.time).substring(4,6)+"-"+(data.showapi_res_body.time).substring(6,8)+"&nbsp;星期"+(data.showapi_res_body.f1.weekday)+"&nbsp;&nbsp;"+data.showapi_res_body.now.temperature_time+"</li>"+
					"<li>"+data.showapi_res_body.cityInfo.c7+""+data.showapi_res_body.cityInfo.c3+"&nbsp;"+data.showapi_res_body.now.weather+"&nbsp;"+data.showapi_res_body.now.wind_power+""+data.showapi_res_body.now.wind_direction+"</li>"+
					"<li style='text-align: center;'><img src='"+data.showapi_res_body.now.weather_pic+"'/></li>"+
					"<li style='text-align: center;'><span style='font-size:20px;font-weight: bold;'>"+data.showapi_res_body.now.temperature+"℃</span></li>"+
					"<li style='text-align: center;'>实时空气质量："+data.showapi_res_body.now.aqiDetail.quality+"</li>"
			
			);
			 
			$("#weath2").append(
					"<li>"+(data.showapi_res_body.f1.day).substring(0,4)+"-"+(data.showapi_res_body.f1.day).substring(4,6)+"-"+(data.showapi_res_body.f1.day).substring(6,8)+"&nbsp;星期"+(data.showapi_res_body.f1.weekday)+"</li>"+
					"<li>"+data.showapi_res_body.cityInfo.c7+""+data.showapi_res_body.cityInfo.c3+"&nbsp;"+data.showapi_res_body.f1.day_weather+"&nbsp;"+data.showapi_res_body.f1.day_wind_power+""+data.showapi_res_body.f1.day_wind_direction+"</li>"+
					"<li style='text-align: center;'><img src='"+data.showapi_res_body.f1.day_weather_pic+"'/></li>"+
					"<li style='text-align: center;'><span style='font-size:20px;font-weight: bold;'>"+data.showapi_res_body.f1.day_air_temperature+"℃</span></li>"
			);
			$("#weath3").append(
					"<li>"+(data.showapi_res_body.f2.day).substring(0,4)+"-"+(data.showapi_res_body.f2.day).substring(4,6)+"-"+(data.showapi_res_body.f2.day).substring(6,8)+"&nbsp;星期"+(data.showapi_res_body.f2.weekday)+"</li>"+
					"<li>"+data.showapi_res_body.cityInfo.c7+""+data.showapi_res_body.cityInfo.c3+"&nbsp;"+data.showapi_res_body.f2.day_weather+"&nbsp;"+data.showapi_res_body.f2.day_wind_power+""+data.showapi_res_body.f2.day_wind_direction+"</li>"+
					"<li style='text-align: center;'><img src='"+data.showapi_res_body.f2.day_weather_pic+"'/></li>"+
					"<li style='text-align: center;'><span style='font-size:20px;font-weight: bold;'>"+data.showapi_res_body.f2.day_air_temperature+"℃</span></li>"
			);
			$("#weath4").append(
					"<li>"+(data.showapi_res_body.f3.day).substring(0,4)+"-"+(data.showapi_res_body.f3.day).substring(4,6)+"-"+(data.showapi_res_body.f3.day).substring(6,8)+"&nbsp;星期"+(data.showapi_res_body.f3.weekday)+"</li>"+
					"<li>"+data.showapi_res_body.cityInfo.c7+""+data.showapi_res_body.cityInfo.c3+"&nbsp;"+data.showapi_res_body.f3.day_weather+"&nbsp;"+data.showapi_res_body.f3.day_wind_power+""+data.showapi_res_body.f3.day_wind_direction+"</li>"+
					"<li style='text-align: center;'><img src='"+data.showapi_res_body.f3.day_weather_pic+"'/></li>"+
					"<li style='text-align: center;'><span style='font-size:20px;font-weight: bold;'>"+data.showapi_res_body.f3.day_air_temperature+"℃</span></li>"
			);
			
		},
		error:function(data){
			alert("error")	
		}
	});
}

</script>
<style>
ul{padding: 10px;font-size: 13px;float: left; 
 -webkit-box-shadow:0 0 10px #335D5D;  
  -moz-box-shadow:0 0 10px #335D5D;  
  box-shadow:0 0 10px #335D5D;  }
li{list-style-type:none;color:#fff;}
</style>
</head>
<body style="background-image: url('../common/images/weathbg.png');">
<h3 style="color:#fff;">易源接口-天气预报</h3>
 <ul id="weath" style='font-size:14px;font-weight: bold;padding-left: 70px;padding-right: 50px;'></ul>
 <ul id="weath2"></ul>
 <ul id="weath3"></ul>
 <ul id="weath4"></ul>
</body>
</html>