package com._520it.crm.service.impl;

import com._520it.crm.domain.Department;
import com._520it.crm.domain.Employee;
import com._520it.crm.mapper.DepartmentMapper;
import com._520it.crm.mapper.EmployeeMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.DepartmentQueryObjetc;
import com._520it.crm.service.IDepartmentService;
import com._520it.crm.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by heyuanbo on 2017/7/10.
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService {
    //使用我们的注解来注入我们的值
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Department record) {
        return departmentMapper.insert(record);
    }

    @Override
    public Department selectByPrimaryKey(Long id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> selectAll() {
        return departmentMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Department record) {
        return departmentMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Department> queryDepartment() {
        return departmentMapper.selectAll();
    }

    @Override
    public PageResult queryAllDepartment(DepartmentQueryObjetc qo) {

        //获取我们的数据(总数据条数)
       Long total = departmentMapper.queryTotal(qo);
       if(total==0L){
           return new PageResult(0, Collections.EMPTY_LIST);
       }
       //获取我们的总数据
        List lowsData = departmentMapper.queryListData(qo);

        return new PageResult(total.intValue(),lowsData);
    }

    @Override
    public void saveDepartment(Department department) {
        //做我们的保存操作
        //保存我们的状态
        department.setState(Department.EXIST);
        departmentMapper.insert(department);

    }

    @Override
    public void updateDepartment(Department department) {
        departmentMapper.updateByPrimaryKey(department);
    }

    @Override
    public void leaveDepartment(Long deptId) {
        //做我们的离职操作
        departmentMapper.changeState(deptId);
    }
}
