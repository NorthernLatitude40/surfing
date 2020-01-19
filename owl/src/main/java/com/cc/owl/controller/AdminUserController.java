package com.cc.owl.controller;

import com.cc.common.domain.AdminUser;
import com.cc.owl.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Wayne
 * @Date: 2019/12/24 12:30
 * @Version: 1.0
 */
@RestController
public class AdminUserController {
    @Autowired
    AdminUserService adminUserService;

    @GetMapping("/user")
    public List<AdminUser> getEmpAll(){
        return adminUserService.getUserAll();
    }

    @GetMapping("/user/{id}")
    public AdminUser getEmp(@PathVariable("id") Integer id){
        return adminUserService.getUserById(id);
    };

    @PostMapping("/user")
    public void addEmp(@RequestBody AdminUser employee){
        System.out.println(employee.toString());
        adminUserService.addUser(employee);
    }

    @DeleteMapping("/user/{id}")
    public void removeEmp(@PathVariable("id") Integer id){
        adminUserService.removeUser(id);
    }

    @PutMapping("/user")
    public void updateEmp(@RequestBody AdminUser employee){
        adminUserService.updateUser(employee);
    }
}
