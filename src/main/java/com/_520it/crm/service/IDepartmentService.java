package com._520it.crm.service;

import com._520it.crm.domain.Department;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.DepartmentQueryObjetc;

import java.util.List;

/**
 * Created by heyuanbo on 2017/7/10.
 */
public interface IDepartmentService {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);

    List<Department> queryDepartment();

    PageResult queryAllDepartment(DepartmentQueryObjetc qo);

    void saveDepartment(Department department);

    void updateDepartment(Department department);

    void leaveDepartment(Long deptId);
}
