//====================================================DataGrid begin===================================================

var userGrid=null;

var user_addDialog=null;

$(document).ready(function(){
	
 var toolbar;
	 
     toolbar=[{
		    text:'增加',
			iconCls: 'icon-add',
			handler: function(){
			   
				f_addauthresource();
			}
			
		},'-',{
			text:'修改',
			iconCls: 'icon-edit',
			handler: function(){
												
				f_modauthresource();   
				
			}
		}		
		,'-',{
			text:'删除',
			iconCls: 'icon-remove',
			handler: function(){
			    
			    f_delauthresource('dg_authoritiesresources','admin/authoritiesResources_del.htm','id');
			}
		
		}];
	
     userGrid=$('#dg_authoritiesresources').datagrid({    
	    url:'admin/authoritiesResources_alldata.htm', 
	    striped:true,//斑马线
	    singleSelect : true, 
	    width: 'auto',
	    toolbar: toolbar,
	    pagination:true,
	    pageList:[5,10,15,20,25],
	    pageSize:15,
	    loadMsg : '数据装载中......',
	    onLoadError : function() {alert('数据加载失败!');},
	    rownumbers: true, 
	    columns:[[
	        {field:'id',title:'id',width:100,hidden:true},  //,hidden:true
	        {field:'authorityId',title:'authorityId',width:100},
	        {field:'resourceId',title:'resourceId',width:100}
	    ]]
	   
	});
     
});

function f_addauthresource(){
		
	user_addDialog=$("<div/>").dialog({
		   title:'角色资源路径增加',
		   width:360,
		   height:350,
		   href:'admin/authoritiesResources_add.htm',
		   modal: true,
		   buttons:[{
				  text:'保存',
				  iconCls:'icon-save',
				  handler:function(){
					  f_saveauthresource();
				  }
			  
		  },{
			  text:'放弃',
			  iconCls:'icon-save',
			  handler:function(){
				  user_addDialog.dialog('close');
			  }
			  
		  }],
		  onLoad:function(){
			
			  $('#id').focus();
				  		 
		  },
		  onClose:function(){
			  $(this).dialog("destroy");//
		  }
	      
	  });
	
}

function f_delauthresource(gridId,urlValue,keyValue){
	
//	console.info(gridId);
	
	var rows=[];	
	rows=$('#'+gridId).datagrid('getSelections');
			
	var keys= getKeys(rows,keyValue);
	//console.info(keys);

	if(keys!='' && keys!=null){
		$.messager.confirm('警告','您确认要删除选中的行吗？',function(r){    
		    if (r){   
		    	$.ajax({  		    		
					url:urlValue+"?param.keys="+keys,
					type : "post",
					dataType:"json",
					success:function(data){
						if(data.flag){
							parent.$('#menutree').tree('reload');
						    $('#'+gridId).datagrid('load',{});	//利用空参数，刷新数据	
						     showMsg(data.msg);	
						}else if(data.err){
							showAlert(data.err); 
						}
						
					},
					error:function(data){
						showAlert(data.err);    	
					}
				});
		    			    	
		    }    
		});
	}else{
		showAlert('没有选中任何行，请选择行后再操作!');    
	}
		
};

function f_modauthresource(){

	var row = $('#dg_authoritiesresources').datagrid('getSelected'); 
		
	//  console.info(rows);
	 if( f_getGridRows(userGrid)){	
	  var user_modDialog=$("<div/>").dialog({
		   title:'资源修改',
		   width:360,
		   height:350,
		   href:'admin/modResourcePage.htm',
		   modal: true,
		   buttons:[{
				  text:'保存',
				  iconCls:'icon-save',
				  handler:function(){
				      
					  if($('#modResource').form('validate')){
						
							$('#modResource').form('submit', {   
								url:'admin/doModResource.htm',
								dataType:'json',
							    success: function(data){ 
							    	//console.info(data);
							    	 data= $.parseJSON(data);
							    	 //data = eval('(' + data + ')');  // change the JSON string to javascript object 
							    	  
							    	//console.info(data);
							        if (data.flag){    
							        	showMsg(data.msg); 
							            user_modDialog.dialog('close');
							            $('#dg_authoritiesresources').datagrid('load',{});	//利用空参数，刷新数据	
							            //getRowIndex的参数可以是一行，也可以是idField列
							            var index=$('#dg_authoritiesresources').datagrid('getRowIndex',row);
							            
							           //console.info(index);
							           
							            $('#dg_authoritiesresources').datagrid('updateRow',{
							    			index: index,	// 索引从0开始
							    			row: data.obj
							    		});
						           
							        }    
							    }    
							});  

						  }				  
				  }
			  
		  },{
			  text:'放弃',
			  iconCls:'icon-save',
			  handler:function(){
				  user_modDialog.dialog('close');
			  }
			  
		  }],
		  onLoad:function(){
			  $("#dg_authoritiesresources").form("load",row);  
			  $('#authorityId').val(row.authorityId);
			  $('#resourceId').val(row.resourceId);
			  $('#id').val(row.id);
			 
		  },
		  onClose:function(){
			  $(this).dialog("destroy");//
		  }
	      
	  });
	  
	}
}

function f_saveauthresource(){
	
	$('#AuthoritiesResources').form({    
	   url:'admin/authoritiesResources_doadd.htm',   
	   dataType:'json',	    
	   onSubmit: function(){
		 return $('#AuthoritiesResources').form('validate');
	   },
	   success:function(data){
		   data= $.parseJSON(data);
		   if (data.flag){  
			   user_addDialog.dialog('close');//user_addDialog使用了全局变量
			   parent.$('#menutree').tree('reload');
			   $('#dg_authoritiesresources').datagrid('load',{});	//利用空参数，刷新数据
		       showMsg(data.msg);
		   }else{
			   showMsg("插入数据失败！")
		   }
	    },
	   error:function(data){
		   user_addDialog.dialog('close');//user_addDialog使用了全局变量
		   showAlert(data);    	
	   }
	});    
	// submit the form    	
	$('#AuthoritiesResources').submit();  
  
}
//====================================================DataGrid end============================================
