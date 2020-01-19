package com.cc.owl.service.impl;

import com.cc.common.domain.AdminUser;
import com.cc.common.mapper.AdminUserMapper;
import com.cc.owl.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Wayne
 * @Date: 2019/12/24 12:34
 * @Version: 1.0
 */
@Service
public class AdminUserImpl implements AdminUserService {
    @Autowired
    AdminUserMapper adminUserMapper;

    @Cacheable(cacheNames = "user",key = "#root.methodName")
    @Override
    public List<AdminUser> getUserAll() {
        return adminUserMapper.getUserAll();
    }

    @Cacheable("user")
    @Override
    public AdminUser getUserById(Integer id) {
        return adminUserMapper.getUserById(id);
    }

    @CachePut(cacheNames = "user")
    @Override
    public void addUser(AdminUser employee) {
        adminUserMapper.insertUser(employee);
    }

    @CacheEvict("user")
    @Override
    public void removeUser(Integer id) {
        adminUserMapper.deleteUserById(id);
    }

    @CachePut(cacheNames = "user",key = "#employee.id")
    @Override
    public void updateUser(AdminUser employee) {
        adminUserMapper.updateUser(employee);
    }
}
