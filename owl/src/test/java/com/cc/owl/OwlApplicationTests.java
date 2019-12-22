package com.cc.owl;

import com.cc.domain.Employee;
import com.cc.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@MapperScan("com.cc.mapper")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OwlApplication.class)
public class OwlApplicationTests {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    private ApplicationContext applicationContext;
	@Test
	public void contextLoads() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name: beanDefinitionNames) {
            System.out.println("/////////////////"+name);
        }
    }

    @Test
    public void contextLoads1() {
        Employee emp = employeeMapper.getEmpById(1);
        System.out.println(emp);
	}

}
