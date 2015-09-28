//=================================================tree begin====================================================

var sy = $.extend({}, sy);/*定义一个全局变量*/

var tab;

//提示框变量
var title;

//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    
    //按需求返回变量名
    return(projectName);
}

$(document).ready(function(){

	//点击树节点，添加tab页面
  $('#menutree').tree({
	onClick: function(node){
		
		//console.info(node);
		
		if(node.attributes.url.trim()!="/"){
		  
			//发送请示时，一般都要在url前面加上项目名称作为请求路径，否则会找不到页面，以前我用时可以不加也没问题，没仔细研究
			var url=getRootPath()+node.attributes.url;		 
			//console.info(url);
			
		    f_addTab(node.id,node.id,node.text,url);
		
		}
	}
  });

});

//添加tab
function f_addTab(name,tabId,title,url){

	//console.info(tabId);
	if ($("#tabs").tabs('exists', title)) {
		$('#tabs').tabs('select', title);
		//$('#'+tabId).attr('src',url);//刷新
	} else {
		tab=$('#tabs').tabs('add',{  
			id:tabId,  
			title:title, 
			//href:url,
			content:'<iframe name="'+name+'" src="'+url+'" frameborder="0" style="height:100%;width:100%;"></iframe>',
			closable:true ,
			cache:true
			/*tools:[{    
        iconCls:'icon-mini-refresh',    
        handler:function(){    
            alert('refresh');    
        }    
    }]  */  
		});
	}	
		
}
//=================================================tree end======================================================

//====================================================DataGrid begin===================================================

var userGrid=null;

var user_addDialog=null;

$(document).ready(function(){
	
 var toolbar;
	 
     toolbar=[{
		    text:'增加',
			iconCls: 'icon-add',
			handler: function(){
			   
				f_add();
			}
			
		},'-',{
			text:'修改',
			iconCls: 'icon-edit',
			handler: function(){
												
				f_mod();   
				
			}
		}		
		,'-',{
			text:'删除',
			iconCls: 'icon-remove',
			handler: function(){
			    
			    f_del('dg','admin/deleteResource.htm','resource_id');
			}
		
		}];
	
     userGrid=$('#dg').datagrid({    
	    url:'admin/allResource.htm', 
	    striped:true,//斑马线
	    singleSelect : true, 
	    width: 'auto',
	    toolbar: toolbar,
	    pagination:true,
	    pageList:[5,10,15,20,25],
	    loadMsg : '数据装载中......',
	    onLoadError : function() {alert('数据加载失败!');},
	    rownumbers: true, 
	    columns:[[
	        {field:'resource_id',title:'resource_id',width:100},  //,hidden:true
	        {field:'resource_name',title:'resource_name',width:100},
	        {field:'resource_type',title:'resource_type',width:100},
	        {field:'priority',title:'priority',width:100},
	        {field:'resource_string',title:'resource_string',width:100},
	        {field:'resource_desc',title:'resource_desc',width:100},
	        {field:'enabled',title:'enabled',width:100},
	        {field:'issys',title:'issys',width:100},
	        {field:'pid',title:'pid',width:100}
	    ]]
	   
	});
     
});

function f_add(){
		
	user_addDialog=$("<div/>").dialog({
		   title:'资源路径增加',
		   width:360,
		   height:350,
		   href:'admin/addResourcePage.htm',
		   modal: true,
		   buttons:[{
				  text:'保存',
				  iconCls:'icon-save',
				  handler:function(){
					  f_save();
				  }
			  
		  },{
			  text:'放弃',
			  iconCls:'icon-save',
			  handler:function(){
				  user_addDialog.dialog('close');
			  }
			  
		  }],
		  onLoad:function(){
			
			  $('#add_userAccount').focus();
				  		 
		  },
		  onClose:function(){
			  $(this).dialog("destroy");//
		  }
	      
	  });
	
}

function f_del(gridId,urlValue,keyValue){
	
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

function f_mod(){

	var row = $('#dg').datagrid('getSelected'); 
		
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
				  user_modDialog.dialog('close');
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

function f_save(){
	
	$('#addResource').form({    
	   url:'admin/doAddResource.htm',   
	   dataType:'json',	    
	   onSubmit: function(){
		 return $('#addResource').form('validate');
	   },
	   success:function(data){
		   data= $.parseJSON(data);
		   if (data.flag){  
			   user_addDialog.dialog('close');//user_addDialog使用了全局变量
			   parent.$('#menutree').tree('reload');
			   $('#dg').datagrid('load',{});	//利用空参数，刷新数据
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
	$('#addResource').submit();  
  
}
//====================================================DataGrid end============================================


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
			    
			    f_del2('dg_authorities','admin/deleteResource.htm','resource_id');
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
		   title:'资源路径增加',
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
			   user_addDialog.dialog('close');//user_addDialog使用了全局变量
			   parent.$('#menutree').tree('reload');
			   $('#dg_authorities').datagrid('load',{});	//利用空参数，刷新数据
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
	$('#addauthorities').submit();  
  
}
//====================================================DataGrid end============================================
//====================================================time begin===============================================
$(document).ready(function(){
	loadTimeInit();

});
function loadTimeInit(){
	  $.ajax({ 
	       type: "post", 
	       url: "user/showtime.htm", 
	       cache:false, 
	       async:true, 
	       dataType: "text", 
         success: function(data){ 
      	 $('#time').text(data);
         } 
		});
}
//=====================================================time end=============================================