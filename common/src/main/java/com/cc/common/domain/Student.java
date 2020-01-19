package com.cc.common.domain;

import com.cc.common.mapper.DepartmentMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class Student implements BeanPostProcessor {
    private String name;
    private String age;
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//        GenericBeanDefinition student = (GenericBeanDefinition) configurableListableBeanFactory.getBeanDefinition("student");
//        student.setBeanClass(DepartmentMapper.class);
//        System.err.println("我执行了-----------"+student.toString());
//    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("我被执行了e---------------"+beanName);
        return null;
    }


//    @Override
//    public void setBeanName(String s) {
//        System.err.println("设置beanname-------------"+ s);
//    }
}
