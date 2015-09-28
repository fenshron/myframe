<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet"	href="<%=basePath%>common/js/plugins/webUI/jquery-easyui/themes/gray/easyui.css" type="text/css"></link>
<link rel="stylesheet"	href="<%=basePath%>common/js/plugins/webUI/jquery-easyui/themes/icon.css"	type="text/css"></link> 
<script type="text/javascript"	src="<%=basePath%>common/js/plugins/webUI/jquery-1.7.2.js"></script>
<script type="text/javascript"	src="<%=basePath%>common/js/plugins/webUI/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/plugins/webUI/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/MyJs/easyui.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/MyJs/sys/sys.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/MyJs/authorities/authorities.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/MyJs/authorities/authoritiesResources.js"></script>
<script type="text/javascript" src="<%=basePath%>common/js/MyJs/resource/resource.js"></script>

