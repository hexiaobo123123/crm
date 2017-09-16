<%--
  Created by IntelliJ IDEA.
  User: heyuanbo
  Date: 2017/7/10
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页面</title>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui/themes/icon.css">
    <script type="text/javascript" src="/static/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/static/jquery-easyui/jquery.easyui.min.js"></script>

    <script type="text/javascript">
        $(function () {
            $("#main_tree").tree({
                url:'static/data/menu.json',
                onClick:function (node) {

                    //判断我们的 面板是否应届存在
                    if($("#main_tabs").tabs("exists",node.text)){
                        //有流选择我们的目标
                        $("#main_tabs").tabs("select",node.text)
                    }
                    else{

                    $("#main_tabs").tabs("add",{
                        title:node.text,
                        fit:true,
                        closable:true,
                        //加载我们的neir使用我们的content
                        content:'<iframe src="'+node.attributes.url+'" style="width: 100%;height: 100%" frameborder="0"></iframe>'

                    })
                    }

                }
            })
        })


    </script>



</head>
<body  class="easyui-layout" fit="true">
<div data-options="region:'north',height:80" style="background-color:#99cdff">
    <div style="float: left">
    <h1>欢迎来到小波的系统</h1>
    </div>
    <div style="float: right;margin-top: 50px;margin-right: 15px">
        当期用户:小波
        <a href="/logout" style="text-decoration: none">退出</a>
    </div>

</div>
<div data-options="region:'south',height:30" style="background-color:#99cdff">
    <center>小波所有;切勿抄袭;违者必究</center>
</div>
<div data-options="region:'west',title:'菜单',width:180">
    <div id="aa" class="easyui-accordion" >
        <div title="Title1" data-options="iconCls:'icon-save'" style="overflow:auto">
            <h3 style="color:#0099FF;">Accordion for jQuery</h3>
            <p>关于小波</p>
        </div>
        <div title="系统菜单" data-options="iconCls:'icon-reload',selected:true" >
            <%--我们的树菜单--%>
            <ul id="main_tree"></ul>
        </div>
        <div title="相关">
            关于我们


        </div>
    </div>



</div>
<div data-options="region:'center'">
    <%--我们的tables--%>
    <div id="main_tabs" data-options="fit:true,closable:true" class="easyui-tabs">

    </div>

</div>
</body>



</html>
