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
     <form id="addauthorities" method="post" action="admin/authority_doadd.htm">
     <table>
	     <tr><td style="text-align: right;"><label>authority_id:</label></td><td><input required="true" missingMessage="不能为空" class="easyui-textbox" id="resource_name" name="authorityId" type="text" /> </td></tr>
	     <tr><td style="text-align: right;"><label>authorityName:</label></td><td> <input required="true" missingMessage="不能为空" class="easyui-textbox"  type="resource_type" name="authorityName" id="role"/></td></tr>
	     <tr><td style="text-align: right;"><label>authorityDesc:</label></td><td><input required="true" missingMessage="不能为空" class="easyui-textbox"  type="priority" name="authorityDesc" id="role"/></td></tr>
	     <tr><td style="text-align: right;"><label>enabled:</label></td><td><input required="true" missingMessage="不能为空" class="easyui-textbox"  type="resource_string" name="enabled" id="role"/></td></tr>
	     <tr><td style="text-align: right;"><label>issys:</label></td><td><input required="true" missingMessage="不能为空" class="easyui-textbox"  type="resource_desc" name="issys" id="role"/></td></tr>
	     </table>
     </form>
  </body>
</html>
	   