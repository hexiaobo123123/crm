package com._520it.crm.web.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com._520it.crm.domain.Permission;
import com._520it.crm.page.AjaxResult;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.PermissionQueryObject;
import com._520it.crm.service.IPermissionService;

@Controller
@RequestMapping("/permission")
public class PermissionController {
	@Autowired
	IPermissionService permissionService;
	
	@RequestMapping("")
	public String index(){
		return "permission";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(PermissionQueryObject qo){
		PageResult pageResult = null;
		pageResult = permissionService.queryPage(qo);
		return pageResult;
	}
	@RequestMapping("/reload")
	@ResponseBody
	public AjaxResult reload(){
		AjaxResult result = null;
		try{
			permissionService.reload();
			result = new AjaxResult(true,"加载成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("加载失败,请联系管理员！");
		}
		return result;
	}


}
