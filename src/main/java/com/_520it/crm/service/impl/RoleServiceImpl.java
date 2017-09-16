package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com._520it.crm.domain.Permission;
import com._520it.crm.mapper.PermissionMapper;
import com._520it.crm.query.RoleQueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.Role;
import com._520it.crm.mapper.RoleMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IRoleService;
@Service
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private PermissionMapper permissionMapper;

	public int deleteByPrimaryKey(Long id) {
		return roleMapper.deleteByPrimaryKey(id);
	}

	public int insert(Role record) {
		int insert = roleMapper.insert(record);
		//维护我们中间表的关系
		//获取我们的权限
		List<Permission> perms = record.getPerms();
		for (Permission perm : perms) {
		roleMapper.insertPermission(record.getId(),perm.getId());

		}
		return insert;
	}

	public Role selectByPrimaryKey(Long id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	public List<Role> selectAll() {
		return roleMapper.selectAll();
	}

	public int updateByPrimaryKey(Role record) {
		//先删除我们的中间表中的关系
		roleMapper.deleteByRole(record.getId());
		//先插入我们的中间表中的关系
		List<Permission> perms = record.getPerms();
		for (Permission perm : perms) {
			roleMapper.insertPermission(record.getId(),perm.getId());

		}

		return roleMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPage(QueryObject qo) {
		Long count = roleMapper.queryPageCount(qo);
		if(count<=0){
			return new PageResult(0, Collections.EMPTY_LIST);
		}
		List<Role> result = roleMapper.queryPageData(qo);
		PageResult pageResult = new PageResult(count.intValue(),result);
		return pageResult;
	}

	@Override
	public PageResult queryAllPermission(RoleQueryObject qo) {
		List<Permission> permissions = permissionMapper.queryPageData(qo);

		return new PageResult(100,permissions);
	}

	@Override
	public PageResult queryByRoleWithSelfPermission(Long roleId) {
		List<Permission> permissions = permissionMapper.queryByRoleWithSelfPermission(roleId);

		return new PageResult(100,permissions);
	}

	@Override
	public List queryRole() {
		return roleMapper.selectAll();
	}

	@Override
	public List<Long> queryByShowRole(Long empId) {
		return roleMapper.queryByShowRole(empId);
	}
}
