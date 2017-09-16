package com._520it.crm.service;

import com._520it.crm.domain.Employee;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.EmployeeQueryObject;

import java.util.List;

/**
 * Created by heyuanbo on 2017/7/10.
 */
public interface IEmployeeService {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    PageResult queryByEmployee(EmployeeQueryObject qo);

    void leaveState(Long empId);
}
