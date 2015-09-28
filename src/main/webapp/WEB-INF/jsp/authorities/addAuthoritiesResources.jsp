<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  	<jsp:include page="../baseJsCss.jsp"></jsp:include>  
<body>
     <form id="AuthoritiesResources" method="post" action="admin/authoritiesResources_doadd.htm">
     <table>
	    <!--  <tr><td style="text-align: right;"><label>id:</label></td><td><input required="true" missingMessage="不能为空" class="easyui-textbox"  name="id" type="text" /> </td></tr> -->
	     <tr><td style="text-align: right;"><label>authorityId:</label></td><td> <input required="true" missingMessage="不能为空" class="easyui-textbox" name="authorityId" id="authorityId"/></td></tr>
	     <tr><td style="text-align: right;"><label>resourceId:</label></td><td><input required="true" missingMessage="不能为空" class="easyui-textbox"  name="resourceId" id="resourceId"/></td></tr>
	     </table>
     </form>
  </body>
</html>
	   