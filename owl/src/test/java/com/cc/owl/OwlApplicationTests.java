package com.cc.owl;

import com.cc.domain.Employee;
import com.cc.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@MapperScan("com.cc.mapper")
@SpringBootTest(classes = OwlApplication.class)
class OwlApplicationTests {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    private ApplicationContext applicationContext;
	@Test
	void contextLoads() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name: beanDefinitionNames) {
            System.out.println("/////////////////"+name);
        }
    }

    @Test
    void contextLoads1() {
        Employee emp = employeeMapper.getEmpById(1);
        System.out.println(emp);
	}

}
