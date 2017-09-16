package com._520it.crm.web.controller;

import com._520it.crm.domain.Role;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.RoleQueryObject;
import com._520it.crm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	IRoleService roleService;
	
	@RequestMapping("")
	public String index(){
		return "role";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(RoleQueryObject qo){
		PageResult pageResult = null;
		pageResult = roleService.queryPage(qo);
		return pageResult;
	}
	@RequestMapping("/queryAllPermission")
	@ResponseBody
	public PageResult queryAllPermission(RoleQueryObject qo){
		PageResult pageResult = null;
		pageResult = roleService.queryAllPermission(qo);
		return pageResult;
	}
	//做我们自身权限的加载
	@RequestMapping("/queryByRoleWithSelfPermission")
	@ResponseBody
	public PageResult queryByRoleWithSelfPermission(Long roleId){
		PageResult pageResult = null;
		pageResult = roleService.queryByRoleWithSelfPermission(roleId);
		return pageResult;
	}
	//做我们员工角色的显示
	@RequestMapping("/queryRole")
	@ResponseBody
	public List queryRole(){
		List result = null;
		result = roleService.queryRole();
		return result;
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save(Role role){
		AjaxResult result = null;
		try{
			roleService.insert(role);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/update")
	@ResponseBody
	public AjaxResult update(Role role){
		AjaxResult result = null;
		try{
			roleService.updateByPrimaryKey(role);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long roleId){
		AjaxResult result = null;
		try{
			roleService.deleteByPrimaryKey(roleId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/queryByShowRole")
	@ResponseBody
	public List queryByShowRole(Long empId){
		List result = null;
		result = roleService.queryByShowRole(empId);
		return result;
	}

}
