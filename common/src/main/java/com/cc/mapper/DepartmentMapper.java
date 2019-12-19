package com.cc.mapper;

import com.cc.domain.Department;
import org.apache.ibatis.annotations.*;

/**
 * @Author: Wayne
 * @Date: 2019/12/19 16:13
 * @Version: 1.0
 */
public interface DepartmentMapper {
    @Select("select * from department where id=#{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from department where id=#{id}")
    public int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(department_name) values(#{departmentName})")
    public int insertDept(Department department);

    @Update("update department set department_name=#{departmentName} where id=#{id}")
    public int updateDept(Department department);
}
