package com.alura.djonatah.medvollapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiApplicationTests {



	@Test
	void contextLoads() {
		System.setProperty("JWT_SECRET", "foo");
	}

}
