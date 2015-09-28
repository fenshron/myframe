//====================================================DataGrid begin===================================================

var userGrid2=null;

var user_addDialog2=null;

$(document).ready(function(){
	
 var toolbar2;
	 
     toolbar2=[{
		    text:'增加',
			iconCls: 'icon-add',
			handler: function(){
			   
				f_add2();
			}
			
		},'-',{
			text:'修改',
			iconCls: 'icon-edit',
			handler: function(){
												
				f_mod2();   
				
			}
		}		
		,'-',{
			text:'删除',
			iconCls: 'icon-remove',
			handler: function(){
			    
			    f_del2('dg_authorities','admin/authority_del.htm','authorityId');
			}
		
		}];
	
     userGrid2=$('#dg_authorities').datagrid({    
	    url:'admin/authority_alldata.htm', 
	    striped:true,//斑马线
	    singleSelect : true, 
	    width: 'auto',
	    toolbar: toolbar2,
	    pagination:true,
	    pageList:[5,10,15,20,25],
	    loadMsg : '数据装载中......',
	    onLoadError : function() {alert('数据加载失败!');},
	    rownumbers: true, 
	    columns:[[
	        {field:'authorityId',title:'authority_id',width:100},
	        {field:'authorityName',title:'authority_name',width:100},
	        {field:'authorityDesc',title:'authority_desc',width:100},
	        {field:'enabled',title:'enabled',width:100},
	        {field:'issys',title:'issys',width:100}
	    ]]
	   
	});
     
});

function f_add2(){
		
	user_addDialog2=$("<div/>").dialog({
		   title:'权限增加',
		   width:360,
		   height:350,
		   href:'admin/authority_add.htm',
		   modal: true,
		   buttons:[{
				  text:'保存',
				  iconCls:'icon-save',
				  handler:function(){
					  f_save2();
				  }
			  
		  },{
			  text:'放弃',
			  iconCls:'icon-save',
			  handler:function(){
				  user_addDialog2.dialog('close');
			  }
			  
		  }],
		  onLoad:function(){
			
			  $('#authorityId').focus();
				  		 
		  },
		  onClose:function(){
			  $(this).dialog("destroy");//
		  }
	      
	  });
	
}

function f_del2(gridId,urlValue,keyValue){
	
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

function f_mod2(){

	var row = $('#dg_authorities').datagrid('getSelected'); 
		
	//  console.info(rows);
	 if( f_getGridRows(userGrid)){	
	  var user_modDialog=$("<div/>").dialog({
		   title:'权限修改',
		   width:360,
		   height:350,
		   href:'admin/modAuthoritiesPage.htm',
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
							            $('#dg').datagrid('load',{});	//利用空参数，刷新数据	
							            //getRowIndex的参数可以是一行，也可以是idField列
							            var index=$('#dg').datagrid('getRowIndex',row);
							            
							           //console.info(index);
							           
							            $('#dg').datagrid('updateRow',{
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
				  user_modDialog2.dialog('close');
			  }
			  
		  }],
		  onLoad:function(){
			  $("#modResource").form("load",row);  
			  $('#resource_id').val(row.resource_id);
			  $('#resource_name').val(row.resource_name);
			  $('#resource_type').val(row.resource_type);
			  $('#priority').val(row.priority); 
			  $('#resource_string').val(row.resource_string);	  
			  $("#resource_desc").val(row.resource_desc);
			  $("#enabled").val(row.enabled);
			  $("#issys").val(row.issys);
			  $("#pid").val(row.pid);
		  },
		  onClose:function(){
			  $(this).dialog("destroy");//
		  }
	      
	  });
	  
	}
}

function f_save2(){
	
	$('#addauthorities').form({    
	   url:'admin/authority_doadd.htm',   
	   dataType:'json',	    
	   onSubmit: function(){
		 return $('#addauthorities').form('validate');
	   },
	   success:function(data){
		   data= $.parseJSON(data);
		   if (data.flag){  
			   user_addDialog2.dialog('close');//user_addDialog使用了全局变量
			   parent.$('#menutree').tree('reload');
			   $('#dg_authorities').datagrid('load',{});	//利用空参数，刷新数据
		       showMsg(data.msg);
		   }else{
			   showMsg("插入数据失败！")
		   }
	    },
	   error:function(data){
		   user_addDialog2.dialog('close');//user_addDialog使用了全局变量
		   showAlert(data);    	
	   }
	});    
	// submit the form    	
	$('#addauthorities').submit();  
  
}
//====================================================DataGrid end============================================
