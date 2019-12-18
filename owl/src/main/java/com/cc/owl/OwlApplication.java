package com.cc.owl;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class OwlApplication {


	public static void main(String[] args) {

		SpringApplication.run(OwlApplication.class, args);
	}

//	@Bean
//	public Redisson redisson(){
//		//此为单机模式
//		Config config = new Config();
//		config.useSingleServer().setAddress("redis://192.168.56.103:6379").setDatabase(0);
//		return (Redisson) Redisson.create(config);
//	}
}
