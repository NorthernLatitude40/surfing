package com.cc.owl.service;

import com.cc.common.domain.AdminUser;

import java.util.List;

/**
 * @Author: Wayne
 * @Date: 2019/12/24 12:31
 * @Version: 1.0
 */
public interface AdminUserService {
    public List<AdminUser> getUserAll();
    public AdminUser getUserById(Integer id);
    public void addUser(AdminUser user);
    public void removeUser(Integer id);
    public void updateUser(AdminUser user);
}
