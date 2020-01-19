package com.cc.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommonApplication {


	public static void main(String[] args) {

		SpringApplication.run(CommonApplication.class, args);
	}

//	@Bean
//	public Redisson redisson(){
//		//此为单机模式
//		Config config = new Config();
//		config.useSingleServer().setAddress("redis://192.168.56.103:6379").setDatabase(0);
//		return (Redisson) Redisson.create(config);
//	}
}
