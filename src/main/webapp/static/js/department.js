$(function () {
    //加载我们的数据
    $("#department_datagrid").datagrid({
        url:'/department/list',
        fit:true,
        fitColumns:true,
        pagination:true,
        rownumbers:true,
        singleSelect:true,
        pageList:[3,10,20,30,50],
        toolbar:'#department_dialog_bt',
        columns:[
            [
                {field:'sn',title:'编号',width:1},
                {field:'name',title:'名称',width:1},
                {field:'state',title:'状态',width:1,formatter:stateFormatter},
            ]
        ],
        //做我们按钮的禁用
        onClickRow:function (rowIndex, rowData) {
            //判断是否是禁用状态
            if(rowData.state==-1){
                //禁用我们的anniu
                $("#editbt,#leavebt").linkbutton("disable");
            }else{
                //恢复我们的按钮
                $("#editbt,#leavebt").linkbutton("enable");

            }
        }
    });
    $("#department_dialog").dialog({
        width:300,
        height:300,
        buttons:"#department_dialog_bb",
        closed:true,
    })
})


//我们状态的方法
function stateFormatter(value,rowData,rowIndex) {
    if(value){
        return '<span style="color: red">离职</span>';
    }else{
        return '<span style="color: green">正常</span>';
    }

}

function add() {
    //打开我们的对话框
    $("#department_dialog").dialog("open");
    //设置我们的标题
    $("#department_dialog").dialog("setTitle","新增页面");
    //清空我们的列表
    $("#department_form").form("clear");

}
function edit() {
    //我们的编辑操作
    var rowsData =$("#department_datagrid").datagrid("getSelected");
    if(rowsData){
        $("#department_dialog").dialog("open");
        //设置我们的标题
        $("#department_dialog").dialog("setTitle","编辑页面");
        //清空我们的列表
        $("#department_form").form("clear");
        //数据的回显
        $("#department_form").form("load",rowsData);

    }else{
        $.messager.alert("温馨提示","你还没有选择任何数据;请选择","warning")
    }

}
function leave() {
    //做我们的离职操作
    var rowsData =$("#department_datagrid").datagrid("getSelected");
/*    if(rowsData){
        $.messager.confirm("温馨提示","你确定要离职操作吗?",function () {
            if(data.success){
                $.get("/department/leaveDepartment?deptId="+rowsData.id,function (data) {

                    $.messager.alert("温馨提示",data.msg,"info");
                    $("#department_datagrid").datagrid("reload")
                })
            }else{
                    $.messager.alert("温馨提示",data.msg,"error");

            }
        })

    }else {
        $.messager.alert("温馨提示","你还没有选择任何数据;请选择","warning");
    }*/
    if(rowsData){
            $.messager.confirm("温馨提示","你确定要离职操作吗?",function () {

        $.get("/department/leaveDepartment?deptId="+rowsData.id,function (data){
            if(data.success){
                $.messager.alert("温馨提示",data.msg,"info");
                $("#department_datagrid").datagrid("reload")
            }else{
                $.messager.alert("温馨提示",data.msg,"error");

            }
        })
            })

    }else {
        $.messager.alert("温馨提示","你还没有选择任何数据;请选择","warning");
    }


}
function reload() {
    $("#department_datagrid").datagrid("reload");

}
function save() {
    //做我们的保存操作
    //才能判断我们的是否有id
    if($("#department_form input[name='id']").val()){
        url = '/department/update';
    }else{
        url = '/department/save';
    }
    //做我们的表单的提交操作
    $("#department_form").form("submit",{
        url:url,
        success:function (data) {
            //做我们的数据的装换
            data =$.parseJSON(data);
        if(data.success){
            //关闭我们的对话框
            $("#department_dialog").dialog("close");
            //刷新我们的页面
            $("#department_datagrid").datagrid("reload")
            $.messager.alert("温馨提示",data.msg,"info")
        }else{
            $.messager.alert("温馨提示",data.msg,"error")

        }
        }
    })



}
function cancel() {
    $("#department_dialog").dialog("close")

}
//做我们的高级查询
function searchResult() {
    //取出我们的数据

    var keyword = $("[name='keyword']").val();
    //加载我们的数据
    $("#department_datagrid").datagrid("load",{
        keyword:keyword

    })


}



