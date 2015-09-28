<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="UTF-8">
    <title>ueditor demo</title>
    <script type="text/javascript"	src="<%=basePath%>common/js/plugins/webUI/jquery-1.7.2.js"></script>
    <jsp:include page="../baseJsCss.jsp"></jsp:include>
    <style>
    label,a{
        font: normal 12px Verdana, "Microsoft YaHei";padding:5px;
    }
     
    .pd{padding:5px 0px;}
    </style>
    <script>
    $(function(){
    	$('#cs2').hide(); 
    	 
    });
    function blockcs(){
    	$('#cs').hide();
    	$('#cs2').show();
    }
    function blockcs2(){
    	$('#copyto').val("");
    	$('#cs2').hide();
    	$('#cs').show();
    }
    
    
    
    function sendEmail(){
    	$.messager.progress({ 
    	    title: 'Please waiting', 
    	    msg: 'Loading data...', 
    	    text: 'PROCESSING.......' 
    	});
     
    	$('#email').form({    
    	   url:'sendemail.htm',   
    	   dataType:'json',	    
    	   onSubmit: function(){
    		 return  $('#email').form('validate');
    	   },
    	   success:function(data){
    		   $.messager.progress('close'); 
    		   data= $.parseJSON(data);
    		   if (data.flag){  
    			   $.messager.alert('提示',data.msg); 
    		   }else{
    			   
    			   $.messager.alert('提示',"发送失败！"); 
    		   }
    	    },
    	   error:function(data){
    		   $.messager.progress('close'); 
    		   showAlert(data.msg);    	
    	   }
    	});    
    	// submit the form    	
    	$('#email').submit();  
    	
    	
    	
    	/* $.ajax({
            cache: true,
            type: "POST",
            url:"user/sendemail.htm",
            data:$('#email').serialize(),// 你的formid
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
                $("#commonLayout_appcreshi").parent().html(data);
            }
        }); */
      
    }
    </script>
</head>

<body>
	<form id="email" name="form1" method="post">
	<div class="pd">
    <label>收件人</label><input required="true" missingMessage="不能为空" type="text" name="to"  class="easyui-textbox"/><br/>
    <a id="cs" style="margin-left:3.5em;" href="javascript:void(0)" onclick="blockcs()" >添加抄送</a>
    </div>
    <div class="pd" id="cs2">
    <label style="margin-left: 1em;">抄送</label><input type="text" id="copyto" name="copyto"  class="easyui-textbox" /><br/>
    <a style="margin-left:3.5em;" href="javascript:void(0)" onclick="blockcs2()">删除抄送</a>
    </div>
    <div class="pd">
    <label style="margin-left: 1em;">主题</label><input required="true" missingMessage="不能为空" type="text" name="subject"  class="easyui-textbox" />
    </div>
     <div class="pd">
    <label style="margin-left: 1em;">附件</label><input readonly="readonly" name="filename" type="text" id="file" style="border-radius: 5px 5px 5px 5px;padding:2px 0;border: 1px solid #D0D0D0;" />
    <a href="javascript:void(0);" onclick="upFiles();">上传文件</a>
    <a href="javascript:void(0);" onclick="delFiles();">删除文件</a>
    </div>
    <script id="container" name="content" type="text/plain" style="height: 300px;"></script>
    <a id="btn"  href="javascript:void(0)" onclick="sendEmail()" class="easyui-linkbutton" data-options="iconCls:'icon-email'">发送</a> 
    </form>
    <script type="text/javascript" src="../common/js/plugins/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="../common/js/plugins/ueditor/ueditor.all.js"></script>
    <script type="text/javascript">
      
        $(function(){
        	  var ue = UE.getEditor('container',{
      			toolbars: [
      				['bold', 'italic', 'underline', 'fontfamily', 'fontsize','forecolor','backcolor','justifyleft','justifyright','justifycenter','justifyjustify','insertorderedlist','insertunorderedlist','blockquote','link','attachment','source']
      			],
      			autoHeightEnabled: true,
      			autoFloatEnabled: true
      		});
        });
       
    </script>
    
        <script type="text/plain" id="upload_ue"></script>  
	    <script type="text/javascript">
			var _editor;
			$(function() {
			    //重新实例化一个编辑器，防止在上面的editor编辑器中显示上传的图片或者文件
			    _editor = UE.getEditor('upload_ue',{
	      			toolbars: [
	      	      				['attachment']
	      	      			],
	      	      			autoHeightEnabled: true,
	      	      			autoFloatEnabled: true
	      	      		});
			    _editor.ready(function () {
			        //设置编辑器不可用
			        //_editor.setDisabled();
			        //隐藏编辑器，因为不会用到这个编辑器实例，所以要隐藏
			        _editor.hide();
			        //侦听文件上传，取上传文件列表中第一个上传的文件的路径
			        _editor.addListener('afterUpfile', function (t, arg) {
			        	
			            $("#file").attr("value", arg[0].url);
			        })
			    });
			});    
		 
			//弹出文件上传的对话框
			function upFiles() {
			    var myFiles = _editor.getDialog("attachment");
			    myFiles.open();
			}
			function delFiles() {
				 $("#file").attr("value", "");
			}
			
		</script>
</body>

</html>