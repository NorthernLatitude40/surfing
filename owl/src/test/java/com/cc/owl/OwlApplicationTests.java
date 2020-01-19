package com.cc.owl;

import com.cc.common.domain.Employee;
import com.cc.common.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;

@MapperScan("com.cc.common.mapper")
//@RunWith(SpringRunner.class)
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

	@org.junit.Test
	public void contextLoads2(){
//        HashMap<Object, Object> map = new HashMap<>();
//        map.put("name","zhangsan");
        ArrayList<Object> list = new ArrayList<>();
        int[] i = new int[10];
        for (int j = 0; j < i.length; j++) {
            i[j] = j;
        }
        System.out.println(i);
    }

}
