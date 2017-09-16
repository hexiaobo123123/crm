$(function () {
    var employeeDatagrid = $("#employee_datagrid");
    var employeeDialog = $("#employee_dialog");
    var employeeForm = $("#employee_form");
    var editAndLeaveBt = $("#editbt,#leavebt");

    //加载我们的数据
    employeeDatagrid.datagrid({
        url:'/employee/list',
        fit:true,
        fitColumns:true,
        pagination:true,
        rownumbers:true,
        singleSelect:true,
        pageList:[3,10,20,30,50],
        toolbar:'#employee_dialog_bt',
        columns:[
            [
                {field:'username',title:'用户名',width:1},
                {field:'realname',title:'真实姓名',width:1},
                {field:'tel',title:'联系电话',width:1},
                {field:'email',title:'邮箱',width:1},
                {field:'dept',title:'所属部门',width:1,formatter:deptFormatter},
                {field:'inputtime',title:'录入时间',width:1},
                {field:'state',title:'状态',width:1,formatter:stateFormatter},
                {field:'admin',title:'超级管理员',width:1,formatter:adminFormatter}
            ]
        ],
        //做我们按钮的禁用
        onClickRow:function (rowIndex, rowData) {
            //判断是否是禁用状态
            if(rowData.state==-1){
                //禁用我们的anniu
                editAndLeaveBt.linkbutton("disable");
            }else{
                //恢复我们的按钮
                editAndLeaveBt.linkbutton("enable");

            }
        }
    });
   employeeDialog.dialog({
        width:300,
        height:300,
        buttons:"#employee_dialog_bb",
        closed:true,
    })
    //对我们的点击事件做同一的监听
    $("a[data-cmd]").on("click",function () {
        //先去获取我们的值
        var cmd = $(this).data("cmd");
        //加载我们的数据(首先我们要去判断一下是否有值)
        if(cmd){
            //调用其中的方法(调用其想对应的方法来实现我们的功能)
            cmdObj[cmd]();
        }
    })


    //定义一个变量来管理我们的方法
    var cmdObj={

        add:
            function() {
        //做我们的页面的增加
        //显示我们的页面
        employeeDialog.dialog("open");
        //设置我们的标题
        employeeDialog.dialog("setTitle","新增页面")
        //清空我们的表单
        employeeForm.form("clear");

    },
        edit:function() {
        //我们的更新的操作
        //获取我们的选择的对象
        var rowData = employeeDatagrid.datagrid("getSelected");
        //判断是否有值
        if(rowData){
            employeeDialog.dialog("open");
            //设置我们的标题
            employeeDialog.dialog("setTitle","编辑页面")
            //清空我们的表单
            employeeForm.form("clear");
            //因为我们的有些属性是不相符的所以要自定义属性
            if(rowData.dept){

                rowData['dept.id']=rowData.dept.id;
            }

            //我们数据的回显
            employeeForm.form("load",rowData);
            //做我们角色的回显(我们要发送请求去查询)
            $.get("/role/queryByShowRole?empId="+rowData.id,function (data) {
                //接受我们的数据并且设置显示
                $("#employee_role").combobox("setValues",data);//我们的数据就是我们的所需要的数据
            },"json")


        }else{
            $.messager.alert("温馨提示","你还没有选中数据,请选择","warning");
        }

    },
        leave:function() {
        //做我们的离职的操作
        //先判断我们是否选中数据
        var rowData = employeeDatagrid.datagrid("getSelected");
        if(rowData){
            //弹出我们的确认框
            $.messager.confirm("温馨提示","你确定要进行离职操作吗?",function () {
                //进行我们的离职操作
                $.get("employee/leaveState?empId="+rowData.id,function (data) {
                    if(data.success){
                        //刷新我们的数据
                        $.messager.alert("温馨提示",data.msg,"info");
                        employeeDatagrid.datagrid("reload");

                    }else{
                        $.messager.alert("温馨提示",data.msg,"error");

                    }
                })
            })

        }else {
            $.messager.alert("温馨提示","你还没有选中数据,请选择","warning");

        }

    },
        reload:function() {
        employeeDatagrid.datagrid("reload");

    },
        save:function() {
        //判断我们的是否有id
        //获取我们的数据
        if($("#employee_form input[name='id']").val()){
            url='employee/update';
        }else{
            url='employee/save';
        }


        //做我们的员工的保存
        //做我们表单 的提交
        employeeForm.form("submit",{
            url:url,
            //提交我们的角色的信息
            onSubmit:function (param) {
              //获取我们的信息
                var datas = $("#employee_role").combobox("getValues");//获取的是我们的id的数组
                //设置我们的在
                for(var i=0;i<datas.length;i++){
                    //设置我们自定义的参数
                    param["roles["+i+"].id"] = datas[i];

                }
            },
            success:function (data) {
                //判断我们是否有响应的数据
                //做我们数据的装换(转换成我们的json数据)
                data =$.parseJSON(data);
                if(data.success){

                    //当然要关闭我们的对话框
                    employeeDialog.dialog("close");
                    //重新加载我们的数据
                    employeeDatagrid.datagrid("reload");
                    $.messager.alert("温馨提示",data.msg,"info");
                }else{
                    $.messager.alert("温馨提示",data.msg,"error");

                }
            }
        })



    },
        cancel:function() {
        employeeDialog.dialog("close")

    },
//做我们的高级查询
        searchResult:function() {
        //获取我们的数据
        var keyword = $("[name='keyword']").val();
        //使用我们的方法来加载
        employeeDatagrid.datagrid("load",{
            keyword:keyword
        })

    }

    }
});
function deptFormatter(value,rowData,rowIndex) {
    //做我们的部门的显示(我们我们是有值)
    if(value){
        return value.name;
    }else{
        return value;
    }


}
//我们状态的方法
function stateFormatter(value,rowData,rowIndex) {
    if(value){
        return '<span style="color: red">离职</span>';
    }else{
        return '<span style="color: green">正常</span>';
    }

}
//我们管理员的方法
function adminFormatter(value,rowData,rowIndex) {
    if(value){
        return '<span style="color: green">是</span>';
    }else{
        return '<span style="color: red">否</span>';
    }

}




