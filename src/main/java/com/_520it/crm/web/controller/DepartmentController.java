package com._520it.crm.web.controller;

import com._520it.crm.domain.Department;
import com._520it.crm.domain.Employee;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.DepartmentQueryObjetc;
import com._520it.crm.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by heyuanbo on 2017/7/10.
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;
    @RequestMapping("/queryDepartment")
    @ResponseBody
    public List queryDepartment(){
       List<Department> depts = departmentService.queryDepartment();
        return depts;
    }

    //做我们页面的展示
    @RequestMapping("")
    public String index(){
        return "department";
    }

    //做我们首页数据的显示
    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(DepartmentQueryObjetc qo){
        PageResult result = null;
        result = departmentService.queryAllDepartment(qo);
        return result;

    }
    //做我们页面 的保存操作
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult save(Department department){
        //做我们的保存操作
        try{
            departmentService.saveDepartment(department);
            return new AjaxResult(true,"保存成功");

        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResult("保存失败");
        }



    }
    //做我们页面 的更新操作
    @RequestMapping("/update")
    @ResponseBody
    public AjaxResult update(Department department){
        //做我们的保存操作
        try{
            departmentService.updateDepartment(department);
            return new AjaxResult(true,"编辑成功");

        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResult("编辑失败");
        }



    }
    //做我们页面 的更新操作
    @RequestMapping("/leaveDepartment")
    @ResponseBody
    public AjaxResult leaveDepartment(Long deptId){
        //做我们的保存操作
        try{
            departmentService.leaveDepartment(deptId);
            return new AjaxResult(true,"离职成功");

        }catch (Exception e){
            e.printStackTrace();
            return new AjaxResult("离职失败");
        }



    }
}
