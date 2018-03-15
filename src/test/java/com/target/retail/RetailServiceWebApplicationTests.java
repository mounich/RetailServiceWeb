package com.target.retail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.target.retail.controller.ProductControllerTest;


//JUnit test suite allows us to aggregate all test cases from multiple classes in one place and run it together.


@RunWith(Suite.class)
@Suite.SuiteClasses({ ProductControllerTest.class})
//Create a suite and specify the list of test classes 

@SpringBootTest
//The @SpringBootTest annotation tells Spring Boot to go and look for a main configuration class (one with @SpringBootApplication for instance), 
//and use that to start a Spring application context. 

@AutoConfigureMockMvc
//auto-configure MockMvc in a non-@WebMvcTest - will bring up a mock servlet context for testing the MVC layer.

public class RetailServiceWebApplicationTests 
{
	// This class remains mostly empty, it is used only as a holder for the above annotations	
	
	@Test
	public void contextLoads() {
	}
	
}