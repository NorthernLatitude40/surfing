package com.cc.common;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest(classes = CommonApplication.class)
public class CommonApplicationTests {

	@Test
	public void contextLoads() {
		HashMap hashMap = new HashMap();
		hashMap.put("name","zhangsan");
	}
	@Test
	public void test1(){
		System.out.println("22");
	}

}