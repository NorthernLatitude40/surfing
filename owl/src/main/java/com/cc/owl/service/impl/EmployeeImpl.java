package com.cc.owl.service.impl;

import com.cc.common.domain.Employee;
import com.cc.common.mapper.EmployeeMapper;
import com.cc.owl.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Wayne
 * @Date: 2019/12/20 22:16
 * @Version: 1.0
 */
@Service
public class EmployeeImpl implements EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    @Cacheable(cacheNames = "emp",key = "#root.methodName")
    @Override
    public List<Employee> getEmpAll() {
        return employeeMapper.getEmpAll();
    }

    @Cacheable("emp")
    @Override
    public Employee getEmpById(Integer id) {
        return employeeMapper.getEmpById(id);
    }

    @CachePut(cacheNames = "emp")
    @Override
    public void addEmp(Employee employee) {
        employeeMapper.insertEmp(employee);
    }

    @CacheEvict("emp")
    @Override
    public void removeEmp(Integer id) {
        employeeMapper.deleteEmpById(id);
    }

    @CachePut(cacheNames = "emp",key = "#employee.id")
    @Override
    public void updateEmp(Employee employee) {
        employeeMapper.updateEmp(employee);
    }
}
