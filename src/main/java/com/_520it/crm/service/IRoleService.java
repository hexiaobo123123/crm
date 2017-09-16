package com._520it.crm.service;
import java.util.List;
import com._520it.crm.domain.Role;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.query.RoleQueryObject;

public interface IRoleService {
	int deleteByPrimaryKey(Long id);
    int insert(Role record);
    Role selectByPrimaryKey(Long id);
    List<Role> selectAll();
    int updateByPrimaryKey(Role record);
	PageResult queryPage(QueryObject qo);

    PageResult queryAllPermission(RoleQueryObject qo);

    PageResult queryByRoleWithSelfPermission(Long roleId);

    List queryRole();

    List<Long> queryByShowRole(Long empId);
}
