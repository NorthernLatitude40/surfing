package com.cc.owl.controller;

import com.cc.domain.Employee;
import com.cc.owl.service.EmployeeService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Wayne
 * @Date: 2019/12/20 22:23
 * @Version: 1.0
 */
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee")
    public List<Employee> getEmpAll(){
        return employeeService.getEmpAll();
    }

    @GetMapping("/employee/{id}")
    public Employee getEmp(@PathVariable("id") Integer id){
        return employeeService.getEmpById(id);
    };

    @PostMapping("/employee")
    public void addEmp(@RequestBody Employee employee){
        System.out.println(employee.toString());
        employeeService.addEmp(employee);
    }

    @Delete("/employee/{id}")
    public void removeEmp(@PathVariable("id") Integer id){
        employeeService.removeEmp(id);
    }

    @PutMapping("/employee/")
    public void updateEmp(Employee employee){
        employeeService.updateEmp(employee);
    }
}
