package com._520it.crm.service.impl;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Role;
import com._520it.crm.mapper.EmployeeMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.EmployeeQueryObject;
import com._520it.crm.service.IEmployeeService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by heyuanbo on 2017/7/10.
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {
    //使用我们的注解来注入我们的值
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {

        //先删我们中间表单关系
        employeeMapper.deleteByRoleId(id);
        return employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Employee record) {
        //做我们逻辑上保存
        record.setInputtime(new Date());
        record.setState(Employee.EXIST);
        record.setAdmin(false);
        //设置密码
        SimpleHash simpleHash = new SimpleHash("md5","666",record.getUsername(),1);
        record.setPassword(simpleHash.toString());
        int insert = employeeMapper.insert(record);
        //维护我们的中间表
        List<Role> roles =record.getRoles();
        for (Role role : roles) {
            employeeMapper.insertRole(record.getId(),role.getId());
        }


        return insert;
    }

    @Override
    public Employee selectByPrimaryKey(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> selectAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Employee record) {

        //作我们角色远员工的保存
        //我们先删除我们中间表中的关系
        employeeMapper.deleteByRoleId(record.getId());
        //再去保存我们中间表的关系
        List<Role> roles =record.getRoles();
        for (Role role : roles) {
            employeeMapper.insertRole(record.getId(),role.getId());
        }


        return employeeMapper.updateByPrimaryKey(record);
    }
    //我们的数据的查询以及我们的分页的数据
    @Override
    public PageResult queryByEmployee(EmployeeQueryObject qo) {
        //获取我们的总条数
       Long total = employeeMapper.queryByTotal(qo);
       //判断是否为空
        if(total==0L){
            //返回一个空的结果
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        //获取我们的数据
        List rows = employeeMapper.queryByRows(qo);


        return new PageResult(total.intValue(),rows);
    }

    @Override
    public void leaveState(Long empId) {
        //做我们的离职的操作
        employeeMapper.changeState(empId);
    }
}
