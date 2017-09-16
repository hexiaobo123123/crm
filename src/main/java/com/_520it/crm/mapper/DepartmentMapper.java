package com._520it.crm.mapper;

import com._520it.crm.domain.Department;
import com._520it.crm.query.DepartmentQueryObjetc;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);

    Long queryTotal(DepartmentQueryObjetc qo);

    List queryListData(DepartmentQueryObjetc qo);

    void changeState(Long deptId);
}