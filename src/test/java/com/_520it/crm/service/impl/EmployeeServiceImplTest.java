package com._520it.crm.service.impl;

import com._520it.crm.domain.Employee;
import com._520it.crm.service.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by heyuanbo on 2017/7/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-mvc.xml")
public class EmployeeServiceImplTest {
    @Autowired
    private IEmployeeService employeeService;
    @Test
    public void deleteByPrimaryKey() throws Exception {
    }

    @Test
    public void insert() throws Exception {
        Employee employee = new Employee();
        employee.setUsername("何远波");
        employee.setEmail("@1231");
        employee.setPassword("1122");
        employeeService.insert(employee);
    }

    @Test
    public void selectByPrimaryKey() throws Exception {
    }

    @Test
    public void selectAll() throws Exception {
    }

    @Test
    public void updateByPrimaryKey() throws Exception {
    }

}