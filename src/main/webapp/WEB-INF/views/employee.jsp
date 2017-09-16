<%--
  Created by IntelliJ IDEA.
  User: heyuanbo
  Date: 2017/7/10
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工页面</title>
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/jquery-easyui/themes/icon.css">
    <script type="text/javascript" src="/static/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/static/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/static/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="/static/js/Employee.js"></script>

</head>
<body>
<%--使用我们的datagrid来做我们的渲染--%>
<table id="employee_datagrid"></table>
<div id="employee_dialog_bt">
<div>
<a class="easyui-linkbutton"  data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
<a class="easyui-linkbutton" id="editbt" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
<a class="easyui-linkbutton" id="leavebt" data-options="iconCls:'icon-delete',plain:true" data-cmd="leave">离职</a>
<a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
</div>
<div>

    关键字:<input type="text" name="keyword" >
    <a data-cmd="searchResult" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>
</div>
</div>
<div id="employee_dialog_bb">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
<div id="employee_dialog">
    <center>

    <form  id="employee_form" method="post" action="/employee/save" style="margin-top: 30px">
        <table>
            <input type="hidden" name="id">
            <tr>
                <td>姓名:</td>
                <td><input type="text" name="username"></td>
            </tr>

            <tr>
                <td>真实姓名:</td>
                <td><input type="text" name="realname"></td>
            </tr>

            <tr>
                <td>联系电话:</td>
                <td><input type="text" name="tel"></td>
            </tr>

            <tr>
                <td>邮箱:</td>
                <td><input type="text" name="email"></td>
            </tr>

            <tr>
                <td>所属部门:</td>
                <td>
                    <input class="easyui-combobox" name="dept.id"
                           data-options="valueField:'id',textField:'name',url:'department/queryDepartment'" />
                </td>
            </tr>

            <tr>
                <td>角色:</td>
                <td>
                    <input id ="employee_role" class="easyui-combobox" name="role"
                           data-options="multiple:true, valueField:'id',textField:'name',url:'role/queryRole'" />
                </td>
            </tr>

        </table>

    </form>
    </center>
</div>
</body>
</html>
