<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
  	<script type="text/javascript"	src="<%=basePath%>common/js/plugins/webUI/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="../common/js/plugins/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="../common/js/plugins/ueditor/ueditor.all.js"></script>


	<script type="text/javascript">
	var editor;
	$(function() {
	    //重新实例化一个编辑器，防止在上面的editor编辑器中显示上传的图片或者文件
	    _editor = UE.getEditor('upload_ue');
	    _editor.ready(function () {
	        //设置编辑器不可用
	        _editor.setDisabled();
	        //隐藏编辑器，因为不会用到这个编辑器实例，所以要隐藏
	        _editor.hide();
	        //侦听文件上传，取上传文件列表中第一个上传的文件的路径
	        _editor.addListener('afterUpfile', function (t, arg) {
	            $("#file").attr("value", _editor.options.filePath + arg[0].url);
	        })
	    });
	});    
 
	//弹出文件上传的对话框
	function upFiles() {
	    var myFiles = _editor.getDialog("attachment");
	    myFiles.open();
	}
	</script>
</head>


<body>
<script type="text/plain" id="upload_ue"></script>              
调用的页面： 
<input type="text" id="file" /><a href="javascript:void(0);" onclick="upFiles();">上传文件</a>
</body>
</html>