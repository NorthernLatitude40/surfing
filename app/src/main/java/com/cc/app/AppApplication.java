package com.cc.app;

import javafx.application.Application;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Properties;

@ComponentScan(basePackages = {"com.cc"})
//@MapperScan(basePackages = {"com.cc.mapper"})
@SpringBootApplication
public class AppApplication {
	public static void main(String[] args) {
		Properties properties = System.getProperties();
		properties.setProperty("druid.url","jdbc:mysql://192.168.56.103:3306/wayne");
		properties.setProperty("druid.username","root");
		properties.setProperty("druid.password","123456");
		properties.setProperty("druid.driver-class-name","com.mysql.jdbc.Driver");
		System.setProperties(properties);
		SpringApplication.run(AppApplication.class, args);
	}

}
