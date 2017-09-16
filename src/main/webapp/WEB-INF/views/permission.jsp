<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>权限管理管理</title>
<%@include file="common/common.jsp" %>
<script type="text/javascript" src="/static/js/permission.js"></script>
</head>
<body>
	<!-- 数据表格 -->
	<table id="permission_datagrid">
		<thead>
			<tr>
				<th data-options="field:'sn',width:1,align:'center'">编码</th>
				<th data-options="field:'resource',width:1,align:'center'">权限表达式</th>
			</tr>
		</thead>
	</table>

	<!-- 数据表格CRUD按钮 -->
	<div id="permission_datagrid_tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">加载权限</a>
		</div>
	</div>

</body>
</html>