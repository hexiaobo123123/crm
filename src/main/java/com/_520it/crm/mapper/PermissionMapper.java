package com._520it.crm.mapper;

import com._520it.crm.domain.Permission;
import com._520it.crm.query.QueryObject;
import java.util.List;

import com._520it.crm.query.RoleQueryObject;
import org.apache.ibatis.annotations.Param;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Permission record);
    Permission selectByPrimaryKey(Long id);
    List<Permission> selectAll();
    int updateByPrimaryKey(Permission record);
	Long queryPageCount(QueryObject qo);
	List<Permission> queryPageData(QueryObject qo);

    List<Permission> queryByRoleWithSelfPermission(Long roleId);
}