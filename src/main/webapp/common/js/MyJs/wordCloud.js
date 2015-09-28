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
                'echarts/chart/wordCloud'
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                
                var myChart2 = ec.init(document.getElementById('wordCloud')); 
                
                function createRandomItemStyle() {
                    return {
                        normal: {
                            color: 'rgb(' + [
                                Math.round(Math.random() * 160),
                                Math.round(Math.random() * 160),
                                Math.round(Math.random() * 160)
                            ].join(',') + ')'
                        }
                    };
                }

               var  option1 = {
                    title: {
                        text: 'Google Trends WordCloud',
                        link: 'http://www.google.com/trends/hottrends'
                    },
                    tooltip: {
                        show: false
                    },
                    series: [{
                        name: 'Google Trends',
                        type: 'wordCloud',
                        size: ['80%', '80%'],
                        textRotation : [0, 45, 90, -45],
                        textPadding: 0,
                        autoSize: {
                            enable: true,
                            minSize: 14
                        },
                        data: [
                            {
                                name: "我的框架",
                                value: 10000,
                                itemStyle: {
                                    normal: {
                                        color: 'black'
                                    }
                                }
                            },
                            {
                                name: "这个是我做的",
                                value: 6181,
                                itemStyle: createRandomItemStyle()
                            },
                            {
                                name: "不相信你可以问我",
                                value: 4386,
                                itemStyle: createRandomItemStyle()
                            },
                            {
                                name: "Java",
                                value: 4055,
                                itemStyle: createRandomItemStyle()
                            },
                            {
                                name: "Charter Communications",
                                value: 2467,
                                itemStyle: createRandomItemStyle()
                            },
                            {
                                name: "Ueditor",
                                value: 2244,
                                itemStyle: createRandomItemStyle()
                            },
                            {
                                name: "SpringMVC",
                                value: 1898,
                                itemStyle: createRandomItemStyle()
                            },
                            {
                                name: "Spring security",
                                value: 1484,
                                itemStyle: createRandomItemStyle()
                            },
                            {
                                name: "MyBatis",
                                value: 1112,
                                itemStyle: createRandomItemStyle()
                            },
                            {
                                name: "MYSQL",
                                value: 965,
                                itemStyle: createRandomItemStyle()
                            },
                            {
                                name: "Jquery",
                                value: 847,
                                itemStyle: createRandomItemStyle()
                            },
                            {
                                name: "EasyUI",
                                value: 582,
                                itemStyle: createRandomItemStyle()
                            },
                            {
                                name: "接口",
                                value: 555,
                                itemStyle: createRandomItemStyle()
                            },
                            {
                                name: "服务",
                                value: 550,
                                itemStyle: createRandomItemStyle()
                            },
                            {
                                name: "eclipse",
                                value: 462,
                                itemStyle: createRandomItemStyle()
                            },
                            {
                                name: "tomcat",
                                value: 366,
                                itemStyle: createRandomItemStyle()
                            },
                            {
                                name: "Echarts",
                                value: 360,
                                itemStyle: createRandomItemStyle()
                            },
                            {
                                name: "深圳",
                                value: 282,
                                itemStyle: createRandomItemStyle()
                            },
                            {
                                name: "测试",
                                value: 273,
                                itemStyle: createRandomItemStyle()
                            },
                            {
                                name: "天气预报",
                                value: 265,
                                itemStyle: createRandomItemStyle()
                            }
                        ]
                    }]
                };
                
                myChart2.setOption(option1); 
            }
        );
 