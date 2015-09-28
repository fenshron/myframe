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


//=======================tab begin=================================
function closeAllTab(){
    var allTabs = $("#tabs").tabs('tabs');
    for(var i = 0, len = allTabs.length; i < len; i++) {
        //因为tabs删除之后会重新对其元素进行排序，所以在删除方法时候只需要进行删除1即可(因为我想保留第一个元素，如果不想保留就改成0即可)
    $("#tabs").tabs('close', 1);
    }
	 
}
//=======================tab end=====================================

//====================================================time begin===============================================
$(document).ready(function(){
	loadTimeInit();

});
function loadTimeInit(){
	  $.ajax({ 
	       type: "post", 
	       url: getRootPath()+"/user/showtime.htm", 
	       cache:false, 
	       async:true, 
	       dataType: "text", 
         success: function(data){ 
      	 $('#time').text(data);
         } 
		});
}
//=====================================================time end=============================================