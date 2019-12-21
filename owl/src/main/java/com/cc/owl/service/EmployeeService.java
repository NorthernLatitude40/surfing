package com.cc.owl.service;

import com.cc.domain.Employee;

import java.util.List;

/**
 * @Author: Wayne
 * @Date: 2019/12/20 22:15
 * @Version: 1.0
 */
public interface EmployeeService {
    public List<Employee> getEmpAll();
    public Employee getEmpById(Integer id);
    public void addEmp(Employee employee);
    public void removeEmp(Integer id);
    public void updateEmp(Employee employee);
}
