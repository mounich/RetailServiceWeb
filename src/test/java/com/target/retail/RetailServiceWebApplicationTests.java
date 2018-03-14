package com.target.retail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.target.retail.controller.ProductControllerTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ProductControllerTest.class})
@SpringBootTest
@AutoConfigureMockMvc
public class RetailServiceWebApplicationTests {
	@Test
	public void contextLoads() {
	}
}