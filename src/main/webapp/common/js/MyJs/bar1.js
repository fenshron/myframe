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

// 路径配置
        require.config({
            paths: {
                echarts: getRootPath()+'/common/js/plugins/echarts-2.2.7/build/dist'
            }
        });
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/line',
                'echarts/chart/bar'
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('linebar')); 
                var option = {
                	    title : {
                	        text: '未来一周气温变化',
                	        subtext: '纯属虚构'
                	    },
                	    tooltip : {
                	        trigger: 'axis'
                	    },
                	    legend: {
                	        data:['最高气温','最低气温']
                	    },
                	    toolbox: {
                	        show : true,
                	        feature : {
                	            mark : {show: true},
                	            dataView : {show: true, readOnly: false},
                	            magicType : {show: true, type: ['line', 'bar']},
                	            restore : {show: true},
                	            saveAsImage : {show: true}
                	        }
                	    },
                	    calculable : true,
                	    xAxis : [
                	        {
                	            type : 'category',
                	            boundaryGap : false,
                	            data : ['周一','周二','周三','周四','周五','周六','周日']
                	        }
                	    ],
                	    yAxis : [
                	        {
                	            type : 'value',
                	            axisLabel : {
                	                formatter: '{value} °C'
                	            }
                	        }
                	    ],
                	    series : [
                	        {
                	            name:'最高气温',
                	            type:'line',
                	            data:[11, 11, 15, 13, 12, 13, 10],
                	            markPoint : {
                	                data : [
                	                    {type : 'max', name: '最大值'},
                	                    {type : 'min', name: '最小值'}
                	                ]
                	            },
                	            markLine : {
                	                data : [
                	                    {type : 'average', name: '平均值'}
                	                ]
                	            }
                	        },
                	        {
                	            name:'最低气温',
                	            type:'line',
                	            data:[1, -2, 2, 5, 3, 2, 0],
                	            markPoint : {
                	                data : [
                	                    {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
                	                ]
                	            },
                	            markLine : {
                	                data : [
                	                    {type : 'average', name : '平均值'}
                	                ]
                	            }
                	        }
                	    ]
                	};
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
 