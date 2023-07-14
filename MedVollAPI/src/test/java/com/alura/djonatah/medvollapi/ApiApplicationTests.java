package com.alura.djonatah.medvollapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.assertj.core.api.WithAssertions;

@SpringBootTest
class ApiApplicationTests {



	@Test
	void contextLoads() {

		System.setProperty("JWT_SECRET", "foo");
	}

}
