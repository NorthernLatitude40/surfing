package com.cc.gull;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootTest
class GullApplicationTests {

	@Test
	void contextLoads() {
	}

	@org.junit.Test
	public void contextLoads2(){

	}

	public static void main(String[] args) {
		HashMap<Object, Object> map = new HashMap<>();
		String put = (String) map.put("name", "zhangsan");
		int size = map.size();
		System.out.println(JSON.toJSON(map));
		System.out.println(put);
		System.out.println(size);

		ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
		concurrentHashMap.size();
	}


}
