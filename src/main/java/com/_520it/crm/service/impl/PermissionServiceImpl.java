package com._520it.crm.service.impl;

import java.util.*;

import com._520it.crm.util.RequestsResource;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.Permission;
import com._520it.crm.mapper.PermissionMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IPermissionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Service
public class PermissionServiceImpl implements IPermissionService {

	//注入我们的RequestMappingHandlerMapping 是用来获取我们的所有的方法
	@Autowired
	private RequestMappingHandlerMapping rmhm;

	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = permissionMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0, Collections.EMPTY_LIST);
		}
		List<Permission> result = permissionMapper.queryPageData(qo);
		PageResult pageResult = new PageResult(count.intValue(),result);
		return pageResult;
	}

	@Override
	public void reload() {
		//做我们权限的加载
		//1.第一步就是我们去重的操作
		//首先要获取我们的所有的权限
		List<Permission> permissions = permissionMapper.selectAll();
		//创建一个set集合来存放我们的数据
		Set<String> permissionSet = new HashSet<>();
		for (Permission permission : permissions) {
			//添加我们的数据
			permissionSet.add(permission.getResource());

		}
		//2.获取我们所有的方法(springmvc中提供我们获取的方法)
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = rmhm.getHandlerMethods();
		//再获取我们的值
		//我们所有方法的集合
		Collection<HandlerMethod> methods = handlerMethods.values();
		RequiresPermissions rp = null;
		String resource = null;
		RequestsResource rsn =null;
		String sn = null;
		Permission permission = new Permission();
		//在进行我们的遍历
		for (HandlerMethod method : methods) {
			//我们要去判断我们的方法上是否贴上我们的requestsPermission是注解
			 rp = method.getMethodAnnotation(RequiresPermissions.class);
			 //判断是否为空
			if(rp!=null){
				//获取我们的方法
				 resource = rp.value()[0];//因为我们返回的是我们的一个string的数据所以我们获取其中一个
				//再去判断我们的方法名是否在set集合了没有就要进行我们的保存操作
				if(!permissionSet.contains(resource)){
					//进行我们的保存操作(我们还要获取我们的sn)
					rsn = method.getMethodAnnotation(RequestsResource.class);
					//获取我们的值
					sn =rsn.value();
					//封装我们的数据
					permission.setSn(sn);
					permission.setResource(resource);
					//进行我们的保存的操作
					permissionMapper.insert(permission);

				}
			}
		}


	}
}
