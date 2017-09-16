package com._520it.crm.web.controller;

import com._520it.crm.domain.Employee;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.EmployeeQueryObject;
import com._520it.crm.service.IEmployeeService;
import com._520it.crm.util.RequestsResource;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by heyuanbo on 2017/7/10.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    //做我们员工页面的数据的显示
    @RequestMapping("/list")
    @ResponseBody//贴上我们的注解
    @RequiresPermissions("employee:list")//使用我们的shiro中的权限的注解
    @RequestsResource("员工的界面")//自定义的注解
    public PageResult list(EmployeeQueryObject qo){
        PageResult result = null;
        //查询我们的对象
        result = employeeService.queryByEmployee(qo);

        return result;
    }
    @RequestMapping("")
    public String show(){
        return "employee";
    }
    //做我们的员工的保存操作
    @RequestMapping("/save")
    @ResponseBody
    @RequiresPermissions("employee:save")//使用我们的shiro中的权限的注解
    @RequestsResource("员工的保存")//自定义的注解
    public AjaxResult save(Employee employee){
        try{
        //进行我们的保存操作
        employeeService.insert(employee);
        //打印我们的信息
            return new AjaxResult(true,"保存成功");

        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResult("保存失败");
        }
    }
    @RequestMapping("/update")
    @RequiresPermissions("employee:update")//使用我们的shiro中的权限的注解
    @RequestsResource("员工的更新")//自定义的注解
    @ResponseBody
    public AjaxResult update(Employee employee){
        try{
        //进行我们的保存操作
        employeeService.updateByPrimaryKey(employee);
        //打印我们的信息
            return new AjaxResult(true,"编辑成功");

        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResult("编辑失败");
        }
    }
    //做我们的离职操作
    @RequestMapping("/leaveState")
    @ResponseBody
    @RequiresPermissions("employee:leaveState")//使用我们的shiro中的权限的注解
    @RequestsResource("员工的离职")//自定义的注解
    public AjaxResult leaveState(Long empId){
        try{
            //进行我们的保存操作
            employeeService.leaveState(empId);
            //打印我们的信息
            return new AjaxResult(true,"离职成功");

        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResult("离职失败");
        }
    }

}
