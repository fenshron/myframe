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

     <form id="modResource" method="post" action="admin/doModResource.htm">
     <table>
	     <tr><td style="text-align: right;"><label>resource_id:</label></td><td><input required="true" readonly="readonly" missingMessage="不能为空" class="easyui-textbox" id="resource_id" name="resource_id" type="text" /> </td></tr>
	     <tr><td style="text-align: right;"><label>resource_name:</label></td><td><input required="true" missingMessage="不能为空" class="easyui-textbox" id="resource_name" name="resource_name" type="text" /> </td></tr>
	     <tr><td style="text-align: right;"><label>resource_type:</label></td><td> <input required="true" missingMessage="不能为空" class="easyui-textbox"  type="resource_type" name="resource_type" id="role"/></td></tr>
	     <tr><td style="text-align: right;"><label>priority:</label></td><td><input required="true" missingMessage="不能为空" class="easyui-textbox"  type="priority" name="priority" id="role"/></td></tr>
	     <tr><td style="text-align: right;"><label>resource_string:</label></td><td><input required="true" missingMessage="不能为空" class="easyui-textbox"  type="resource_string" name="resource_string" id="role"/></td></tr>
	     <tr><td style="text-align: right;"><label>resource_desc:</label></td><td><input required="true" missingMessage="不能为空" class="easyui-textbox"  type="resource_desc" name="resource_desc" id="role"/></td></tr>
	     <tr><td style="text-align: right;"><label>enabled:</label></td><td> <input required="true" missingMessage="不能为空" class="easyui-textbox"  type="enabled" name="enabled" id="role"/></td></tr>
	     <tr><td style="text-align: right;"><label>issys:</label></td><td><input required="true" missingMessage="不能为空" class="easyui-textbox"  type="issys" name="issys" id="role"/></td></tr>
	     <tr><td style="text-align: right;"><label>pid:</label></td><td><!-- <input  class="easyui-textbox" type="pid" name="pid" id="role"/> -->
	      <input class="easyui-combobox" 
			name="pid"
			data-options="
					url:'admin/combobox.htm',
					method:'get',
					valueField:'id',
					textField:'text',
					panelHeight:'auto'
					">
	     
	     </td></tr>
	     </table>
     </form>
  </body>
</html>
	   