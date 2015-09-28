<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
</head>

<body>
    <div id="linebar" style="height:400px"></div> 
    <script src="<%=basePath%>common/js/plugins/echarts-2.2.7/build/dist/echarts.js"></script>
    <script src="<%=basePath%>common/js/MyJs/bar1.js"></script>
</body>