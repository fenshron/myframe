var sy = $.extend({}, sy);/*定义一个全局变量*/

var grid;

var tab;

var Rowsgg=null;

//提示框变量
var title;
var message;

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
	if ($("#tab").tabs('exists', title)) {
		$('#tab').tabs('select', title);
		//$('#'+tabId).attr('src',url);//刷新
	} else {
		tab=$('#tab').tabs('add',{  
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


function f_addHref(tabId,title,hrefUrl){
	
	if ($("#tab").tabs('exists', tabId)) {
		$('#tab').tabs('select', tabId);
		//$('#'+tabId).attr('src',url);//刷新
	} else {
		tab=$('#tab').tabs('add',{  
			id:tabId,  
			title:title, 
			href:hrefUrl,
			//content:'<iframe src="'+url+'" frameborder="0" style="height:100%;width:100%;" "></iframe>',
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




/**************************  grid  **************************/

//datagrid的一些方法调用
function getKeys(rows,key){
	var keyArr="";//必须赋初始值，否则行第一次循环时的值为undefined
	
	if(rows.length!=0){

		for(var i=0;i<rows.length;i++){

			if(keyArr!==""){
				keyArr+=",";
			}
			keyArr+=rows[i][key];
		}
	}

	return keyArr;
}

/*
 * 判断是否有选中行
 */
function f_getGridRows(oGrid){
   	
	var rows=oGrid.datagrid('getSelections');
	
	if(rows.length==0){
		showAlert('没有选中行，请选择一行进行编辑操作！');
		return false;
	}else if(rows.length>1){
		showAlert('你选择了多行数据，只能选择一行数据进行编辑！');
		return false;
	}
	
	return true;
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






/**************************  window  **************************/

//以下是一些easyUI的信息提示扩展

//在屏幕右下角弹出
function showMsg(message){
	$.messager.show({
		title:'提示',
		msg:message,
		timeout:5000,
		showType:'slide'	
	});
}

//在正中央弹出
function showAlert(message){

	   $.messager.alert('警告',message);	
}

function RedAlert(message){
	 $.messager.alert('警告','<font color="'+color+'">'+message+'</font>');
}

/**************************  form  **************************/

/*将form表单内的元素序列化为对象，扩展Jquery的一个方法 === 来源easyui孙宇*/ 
sy.serializeObject = function (form) { 
    var o = {};
    $.each(form.serializeArray(), function (index) {
        if (o[this['name']]) {
            o[this['name']] = o[this['name']] + "," + this['value'];
        } else {
            o[this['name']] = this['value'];
        }
    });
    return o;
};

//验证密码是否一致
$.extend($.fn.validatebox.defaults.rules, {
	eqPwd : {/* 验证两次密码是否一致功能 */
		validator : function(value, param) {
			console.info(value);
			console.info(param);
			return value == $(param[0]).val();
		},
		message : '密码不一致！'
	}
});

//解决validatebox初始化验证问题
$(function () {
    $('input.easyui-validatebox').validatebox('disableValidation')
    .focus(function () { $(this).validatebox('enableValidation');})
    .blur(function () { 
    	$(this).validatebox('validate');
    });
 });


/************************** tree  ****************************/
$.fn.tree.defaults.loadFilter = function(data, parent) {
	var opt = $(this).data().tree.options;
	var idFiled, textFiled, parentField;
	if (opt.parentField) {
		idFiled = opt.idFiled || 'id';
		textFiled = opt.textFiled || 'text';
		parentField = opt.parentField;
		var i, l, treeData = [], tmpMap = [];
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idFiled]] = data[i];
		}
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['text'] = data[i][textFiled];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
			} else {
				data[i]['text'] = data[i][textFiled];
				treeData.push(data[i]);
			}
		}
		return treeData;
	}
	return data;
};
