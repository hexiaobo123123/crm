
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工页面</title>
    <%@include file="common/common.jsp" %>
    <script type="text/javascript" src="/static/js/department.js"></script>

</head>
<body>
<%--使用我们的datagrid来做我们的渲染--%>
<table id="department_datagrid"></table>
<div id="department_dialog_bt">
<div>
<a class="easyui-linkbutton"  data-options="iconCls:'icon-add',plain:true" onclick="add()">新增</a>
<a class="easyui-linkbutton" id="editbt" data-options="iconCls:'icon-edit',plain:true" onclick="edit()">编辑</a>
<a class="easyui-linkbutton" id="leavebt" data-options="iconCls:'icon-delete',plain:true" onclick="leave()">离职</a>
<a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" onclick="reload()">刷新</a>
</div>
<div>

    关键字:<input type="text" name="keyword" >
    <a onclick="searchResult()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
</div>
</div>
<div id="department_dialog_bb">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="save()">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cancel()">取消</a>
</div>
<div id="department_dialog">
    <center>

    <form  id="department_form" method="post" action="/department/save" style="margin-top: 30px">
        <table>
            <input type="hidden" name="id">
            <tr>
                <td>编号:</td>
                <td><input type="text" name="sn"></td>
            </tr>

            <tr>
                <td>名称:</td>
                <td><input type="text" name="name"></td>
            </tr>

        </table>

    </form>
    </center>
</div>
</body>
</html>
