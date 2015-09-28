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
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
     <div id="map1" style="height:400px"></div> 
    <!-- ECharts单文件引入 -->
    <script src="<%=basePath%>common/js/plugins/echarts-2.2.7/build/dist/echarts.js"></script>
    <script src="<%=basePath%>common/js/MyJs/makepoint.js"></script>
</body>