package com.cc.mapper;

import com.cc.domain.Employee;

/**
 * @Author: Wayne
 * @Date: 2019/12/19 17:14
 * @Version: 1.0
 */
public interface EmployeeMapper {
    public Employee getEmpById(Integer id);

    public void insertEmp(Employee employee);
}
