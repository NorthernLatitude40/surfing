package com.cc.common.mapper;

import com.cc.common.domain.AdminUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: Wayne
 * @Date: 2019/12/24 12:28
 * @Version: 1.0
 */
public interface AdminUserMapper {
    @Select("SELECT * FROM admin_user")
    public List<AdminUser> getUserAll();
    @Select("SELECT * FROM admin_user WHERE user_id = #{id}")
    public AdminUser getUserById(Integer id);
    @Insert("INSERT INTO admin_user (username,passpord,salt,realname,avatar,phone,email,sex,locked,ctime) VALUES (#{username},#{passpord},#{salt},#{realname},#{avatar},#{phone},#{email},#{sex},#{locked},#{ctime})")
    public void insertUser(AdminUser employee);
    @Delete("DELETE FROM admin_user WHERE id = #{id}")
    public void deleteUserById(Integer id);
    @Update("UPDATE admin_user SET username=#{username},password=#{password},salt=#{salt},realname=#{realname},avatar=#{avatar},phone=#{phone},email=#{email},sex=#{sex},locked=#{locked},ctime=#{ctime} WHERE user_id=#{id}")
    public void updateUser(AdminUser employee);
}
