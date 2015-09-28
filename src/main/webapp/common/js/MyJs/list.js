var userGrid;

var user_addDialog=null;

$(document).ready(function(){
	
 var toolbar;
	 
     toolbar=[{
		    text:'增加',
			iconCls: 'icon-add',
			handler: function(){
			   
				f_addUser();
			}
			
		},'-',{
			text:'修改',
			iconCls: 'icon-edit',
			handler: function(){
												
				f_modUser();   
				
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
	    toolbar: toolbar,
	    //sortName:'',
	    //sortOrder:'',
	    pagination:true,
	    pageList:[5,10,15,20,25],
	    loadMsg : '数据装载中......',
	    onLoadError : function() {alert('数据加载失败!');},
	    idField:'',
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

function f_addUser(){
		
	user_addDialog=$("<div/>").dialog({
		   title:'资源路径增加',
		   width:500,
		   height:400,
		   href:'admin/addResourcePage.htm',
		   modal: true,
		   buttons:[{
				  text:'保存',
				  iconCls:'icon-save',
				  handler:function(){
					  f_saveUser();
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
	
function  f_user_search(){
	
	//console.info( sy.serializeObject( $("#user_searchForm").form() )  );
		
	$('#dg').datagrid('load',
			sy.serializeObject( $("#user_searchForm").form() ) //将searchForm表单内的元素序列为对象传递到后台
	);	
	
}

function f_user_clearSearchForm(){ 
   $('#user_searchForm').form('clear');//清空增加的表单

}

function f_modUser(){

//第一种修改方法，不用给每个input赋值。动态创建dialog，注意关闭时要销毁动态创建的这个dialog内存，否则每创建一个就在内存生成一个	

	/*
	 * 取选中行的值时也可以用getChecked这种，不过这是返回一个数组类型，这种取值语法是如：rows[0]，这取出一行，取列值 ：rows[0].userId
	 * 我个人更喜欢用getSelected，直接返回一个对象，这种取值语法是如：rows，这取出一行，取列值 ：rows.userId
	 */
//var rows = $('#userGrid').datagrid('getChecked');
  
  var row = $('#dg').datagrid('getSelected'); 
	
//  console.info(rows);
 if( f_getGridRows(userGrid)){	
  var user_modDialog=$("<div/>").dialog({
	   title:'用户资料修改',
	   width:300,
	   height:200,
	   href:'user/modUser.jsp',
	   modal: true,
	   buttons:[{
			  text:'保存',
			  iconCls:'icon-save',
			  handler:function(){
			      
				  if($('#user_modForm').form('validate')){
					
						$('#user_modForm').form('submit', {   
							url:'user/updateUser.action',
							dataType:'json',
						    success: function(data){ 
						    	//console.info(data);
						    	 data= $.parseJSON(data);
						    	 //data = eval('(' + data + ')');  // change the JSON string to javascript object 
						    	  
						    	//console.info(data);
						        if (data.success){    
						        	showMsg(data.msg); 
						            user_modDialog.dialog('close');
			            
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
				  
		  $("#user_modForm").form("load",row);  
	  
	 
  
		   $("#mod_seleRole").val(row.userDesc);
	  },
	  onClose:function(){
		  $(this).dialog("destroy");//
	  }
      
  });
 }

 /* 第二种修改方法，这种要给每一个input赋值
  * 
  * var row = $('#userGrid').datagrid('getSelected'); 

  if( f_getGridRows(userGrid)){
  
	   $('#user_addOrModDiv').dialog('open');
	 
	   $('#id').val(row.userId);
	   $('#userAccount').val(row.userAccount);
	   $('#userName').val(row.userName);
	   $('#userPassword').val(row.userPassword);//虽然密码不在这里修改，但也要赋值，因为不能为空，后台sql不更改保存的密码
	   $('#role').val(row.userDesc);	  
	    	  
	   $("#seleRole").val(row.userDesc);

	   $('#userAccount').focus();
  }
       
	if( $('#password_tr').css('visibility')=='visible'){
		 
	    $('#password_tr').css('visibility','hidden');//隐藏密码栏
	    				    			    
	}*/

}

function f_saveUser(){
	$('#addResource').form({    
	   url:'admin/doAddResource.htm',   
	   dataType:'text',	    
	   onSubmit: function(){  
		 return userBeforSubmit();  
	   },
	   success:function(data){  
		   user_addDialog.dialog('close');//user_addDialog使用了全局变量
		   parent.$('#menutree').tree('reload');
		   $('#dg').datagrid('load',{});	//利用空参数，刷新数据
	       showMsg(data);
	    },
	   error:function(data){
		   user_addDialog.dialog('close');//user_addDialog使用了全局变量
		   showAlert(data);    	
	   }
	});    
	// submit the form    	
	$('#addResource').submit();  
  
}

function userBeforSubmit(){
	
//	$('#add_role').val($("#add_seleRole").val());	  
//	  
//	if($('#add_role').val()==""){
//		showAlert("角色不能为空！请选择角色。");
//		return false;
//	}
		
	return true;
}



