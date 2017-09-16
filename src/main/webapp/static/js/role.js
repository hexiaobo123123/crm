$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var roleDatagrid,roleDialog,roleForm,allPermission,selfPermission;
	roleDatagrid = $("#role_datagrid");
	roleDialog = $("#role_dialog");
	roleForm = $("#role_form");
    allPermission = $("#allPermission");
    selfPermission = $("#selfPermission");
	/*
	 * 初始化数据表格 
	 */
	roleDatagrid.datagrid({
		url:"/role/list",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#role_datagrid_tb'
	});
	/*
	 * 初始化新增/编辑对话框 
	 */
	roleDialog.dialog({
		width:650,
		height:400,
		closed:true,
		buttons:'#role_dialog_bt'
	});
    allPermission.datagrid({
		title:"所有权限",
		url:"/role/queryAllPermission",
        rownumbers:true,
        width:300,
        height:250,
        singleSelect:true,
        fitColumns:true,
        columns:[
			[
				{field:"sn",title:"权限编号",width:1,align:"center"},
			]
		],
		//双击监听我们事件
        onDblClickRow:function (rowIndex, rowData) {
			//往我们的self中追加事件一行
            allPermission.datagrid("deleteRow",rowIndex)
            selfPermission.datagrid("appendRow",rowData);
        },
		//做我们的去重
        onLoadSuccess:function (data) {
			//先获取我们自身的权限
			var selfPermissions =selfPermission.datagrid("getRows");
			//获取我们的ids
			var ids = $.map(selfPermissions,function (item) {
				return item.id;
            })
			//获取我们的(现有)的所有的权限
			var allPermissionRows = data.rows;
			console.log(allPermissionRows)
			//遍历我们的权限
			for(var i=allPermissionRows.length-1;i>=0;i--){
				//判断是否在其中
				if($.inArray(allPermissionRows[i].id,ids)>=0){
					//删除我们的id
					allPermission.datagrid("deleteRow",i);
				}

			}
        }


	})
    selfPermission.datagrid({
        title:"自身权限",
        rownumbers:true,
        singleSelect:true,
        width:300,
        height:250,
        fitColumns:true,
        columns:[
            [
                {field:"sn",title:"权限编号",width:1,align:"center"},
            ]
        ],
        //双击监听我们事件
        onDblClickRow:function (rowIndex, rowData) {
            //往我们的self中追加事件一行
            selfPermission.datagrid("deleteRow",rowIndex)
            allPermission.datagrid("appendRow",rowData);
        },
		//做我们的表单的去重操作(在我们加载完成之后在进行)
        onLoadSuccess:function (data) {//我们所有的数据
			//使用我们的map来返回我们的一个ids的集合
			var ids = $.map(data.rows,function (item) {
				return item.id;
            })
			//然后在获取我们所有的权限
			var allPermissionsRow=allPermission.datagrid("getRows");
			//然后在遍历(做我们删除的时候一般是选择往后遍历的)
			for(var i=allPermissionsRow.length-1;i>=0;i--){
				//判断是否在这其中
				if($.inArray(allPermissionsRow[i].id,ids)>=0){
					//做我们的删除我们的已近有的值
					allPermission.datagrid("deleteRow",i);
				}
			}



			
        }



    })



	/*
	 * 对页面按钮进行统一监听
	 */
	$("a[data-cmd]").on("click",function(){
		var cmd = $(this).data("cmd");
		if(cmd){
			cmdObj[cmd]();
		}
	});
	/*
	 * 所有的操作封装到cmdObj对象中,方便管理
	 */
	var cmdObj = {
			 add:function(){
				roleForm.form("clear");
				//因为我们的datagrid 的诗句是不可form一样.所以我们自己来清除数据
                 allPermission.datagrid("reload");
                 //还要我们的self中的数据使用我们的loadData把我们的空字符串来取代我们原先的数据
                 selfPermission.datagrid("loadData",{rows:[]});
				roleDialog.dialog("setTitle","新增");
				roleDialog.dialog("open");
			},
			edit:function(){
				var rowData = roleDatagrid.datagrid("getSelected");
				if(rowData){
                    allPermission.datagrid("reload");
                    var option=selfPermission.datagrid("options");
                    //我们的url
					option.url='/role/queryByRoleWithSelfPermission?roleId='+rowData.id;
					//加载我们的页面
					selfPermission.datagrid("load");

                    //发送我们查询我们的自身的权限

					roleForm.form("clear");
					roleDialog.dialog("setTitle","编辑");
					roleDialog.dialog("open");
					if(rowData.dept)
						rowData["dept.id"] = rowData.dept.id;
					roleForm.form("load",rowData);
				}else{
					$.messager.alert("温馨提示","请选择需要编辑的数据!","warining");
				}
			},
			del:function(){
				var rowData = roleDatagrid.datagrid("getSelected");
				if(rowData){
					$.messager.confirm("温馨提示","您确定需要删除选中数据吗？",function(yes){
						if(yes){
							$.get("/role/delete?roleId="+rowData.id,function(data){
								if(data.success){
									$.messager.alert("温馨提示",data.msg,"info",function(){
										roleDatagrid.datagrid("reload");
									});
								}else{
									$.messager.alert("温馨提示",data.msg,"error");
								}
							},"json")
						}
					});
				}else{
					$.messager.alert("温馨提示","请选择需要删除的数据!","warining");
				}
			},
			reload:function(){
				roleDatagrid.datagrid("reload");
			},
			save:function(){
				var url;
				var idVal = $("[name='id']").val();
				if(idVal){
					url = "/role/update"
				}else{
					url = "/role/save";
				}
				roleForm.form("submit",{
					url:url,
					//提交我们的自定义的属性和值
                    onSubmit:function (param) {
                        //param是我忙呢要提交的数据
						//获取我们所有的行
						var rows = selfPermission.datagrid("getRows");
						//遍历
						for(var i=0;i<rows.length;i++){
							//提交我们的字节要的数据
                            param["perms["+i+"].id"]=rows[i].id;

						}

                    },
					success:function(data){
						data = $.parseJSON(data);
						if(data.success){
							$.messager.alert("温馨提示",data.msg,"info",function(){
								roleDialog.dialog("close");
								roleDatagrid.datagrid("reload");
							});
						}else{
							$.messager.alert("温馨提示",data.msg,"error");
						}
					}
				});
			},
			cancel:function (){
				roleDialog.dialog("close");
			}
	}
});
