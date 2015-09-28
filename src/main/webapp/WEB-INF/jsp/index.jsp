<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我的主页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<jsp:include page="baseJsCss.jsp"></jsp:include>
<head>
	<meta charset="UTF-8">
	<script type="text/javascript" src="<%=basePath%>common/js/MyJs/myframe.js"></script>
</head>
<body class="easyui-layout" style="background: linear-gradient(to bottom, #68EACC 0%, #497BE8 100%);">
	<div data-options="region:'north',border:false" style="height:60px;background:transparent;padding:10px">
		<div style="float:left;width:14%;height:40px;font-size:28px; font-weight: bold;">myframe</div>
		<div style="float:left;width:66%;height:40px"></div>
		<div style="float:left;width:19%;height:40px;text-align:right;line-height:25px;padding:0 5px;">
			<label id="time"></label>
			${sessionScope['login_account']}&nbsp;&nbsp;
			<a id="btn"  href="<%=basePath%>logout.htm" class="easyui-linkbutton" data-options="iconCls:'icon-back'">退出</a><br/>
			<a href="javascript:void(0)" onclick="closeAllTab()">关闭所有选项卡</a> 
		</div>
	<div></div>
	</div>
	<div data-options="region:'west',split:true,title:'West'" style="width:200px;padding:10px;">
		<ul class="easyui-tree" id="menutree"  url="/myframe/pageMenu.htm"  data-options="lines:true,parentField:'pid'"></ul>
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
	<div data-options="region:'south',border:false" style="height:40px;background:transparent;padding:10px;">south region</div>
	 
	<div data-options="region:'center'" >
		<div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
			<div title="Home" >
			    <div id="wordCloud" style="height:400px"></div>
			    <!-- ECharts单文件引入 -->
			    <script src="<%=basePath%>common/js/plugins/echarts-2.2.7/build/dist/echarts.js"></script>
			    <script src="<%=basePath%>common/js/MyJs/wordCloud.js"></script>
			</div>
		</div>

	</div>
	 
</body>
</html>