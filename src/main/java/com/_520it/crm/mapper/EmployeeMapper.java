package com._520it.crm.mapper;

import com._520it.crm.domain.Employee;
import com._520it.crm.query.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    Long queryByTotal(EmployeeQueryObject qo);

    List queryByRows(EmployeeQueryObject qo);

    void changeState(Long empId);

    void insertRole(@Param("empId") Long empId, @Param("roleId") Long roleId);

    void deleteByRoleId(Long id);
}