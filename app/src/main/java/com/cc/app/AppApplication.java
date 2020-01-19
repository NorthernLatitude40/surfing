package com.cc.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@ComponentScan(basePackages = {"com.cc"})
//@MapperScan(basePackages = {"com.cc.mapper"})
@EnableCaching
@SpringBootApplication
public class AppApplication {
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

//	@Bean
//	public ServerEndpointExporter serverEndpointExporter(){
//		return new ServerEndpointExporter();
//	}
}
