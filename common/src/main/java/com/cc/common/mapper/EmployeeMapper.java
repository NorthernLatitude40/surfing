package com.cc.common.mapper;

import com.cc.common.domain.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: Wayne
 * @Date: 2019/12/19 17:14
 * @Version: 1.0
 */
public interface EmployeeMapper {
    @Select("SELECT * FROM employee")
    public List<Employee> getEmpAll();
    @Select("SELECT * FROM employee WHERE id = #{id}")
    public Employee getEmpById(Integer id);
    @Insert("INSERT INTO employee (lastName,email,gender,d_id) VALUES (#{lastName},#{email},#{gender},#{dId})")
    public void insertEmp(Employee employee);
    @Delete("DELETE FROM employee WHERE id = #{id}")
    public void deleteEmpById(Integer id);
    @Update("UPDATE employee SET lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} WHERE id=#{id}")
    public void updateEmp(Employee employee);

}
