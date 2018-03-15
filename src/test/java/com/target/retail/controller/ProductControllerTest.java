package com.target.retail.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.retail.exception.DataNotFoundException;
import com.target.retail.json.entity.ErrorResponse;
import com.target.retail.model.CurrentPrice;
import com.target.retail.model.Product;
import com.target.retail.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = ProductController.class, secure = false)
public class ProductControllerTest 
{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired 
	private MockMvc mockMvc;
	
	@MockBean 
	ProductService productService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		//This initializes objects annotated with Mockito annotations for given testClass. 
	}
	
	@Test
	public void getProductDetailsByIdTest() throws Exception
	{
		//Create Mock Product with ID = 13860428
		Product product = new Product();
		product.setId("13860428");
		product.setName("The Big Lebowski (Blu-ray)");
		CurrentPrice currentPrice = new CurrentPrice();
		currentPrice.setCurrencycode("USD");
		currentPrice.setValue(13.49);
		product.setCurrentPrice(currentPrice);
		Mockito.when(productService.getProductDetailsById(Mockito.anyString())).thenReturn(product);
		//so whenever a productService.getProductDetailsById is called with an input parameter of any string, then return the mock product.

		//Compare MockMVC response for Product with ID = 13860428 with expected value.
		RequestBuilder builder = MockMvcRequestBuilders.get("/resources/1.0/products/13860428").accept(MediaType.APPLICATION_JSON_VALUE);
		//Creating a Request builder to be able to execute a get request to uri with accept header as “application/json”

		MvcResult actual = mockMvc.perform(builder).andReturn();
		//mockMvc is used to perform the request and return the response back.
		
		String expected = "{\"id\":\"13860428\",\"name\":\"The Big Lebowski (Blu-ray)\",\"current_price\":{\"value\": 13.49,\"currency_code\":\"USD\"}}";
		
		JSONAssert.assertEquals(expected, actual.getResponse().getContentAsString(), false);
		//We are using org.skyscreamer.jsonassert.JSONAssert. This allows us to do partial asserts against a JSON String. We are passing strict as false since we do not want to check for all fields in the response.
	}

	@Test
	public void getProductDetailsForWrongIdTest() throws Exception 
	{
		try {
			//Construct Mock Error Object.
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setErrorCode(404);
			errorResponse.setErrorMsg("productdata not found in Datastore for product ID = 45345345");

			//Since Product does not exist, DataNotFoundException will be thrown , convert DataNotFoundException to ErrorResponse Object 
			//and Compare ErrorMessage/ErrorCode thrown from Service against the local created ErrorResponse Object.
			String response = mockMvc.perform(MockMvcRequestBuilders.get("/resources/1.0/products/45345345"))
					.andReturn().getResponse().getContentAsString();
			//mockMvc is used to perform the request and return the response back.
			
			ErrorResponse mockErroresponse = new ObjectMapper().readValue(response, ErrorResponse.class);
			//mapper is a mocked object, and the mocked mapper is set to the instance you are trying to test.
			//can be used to parse or deserialize JSON content into a Java object.
			
			
			assertEquals(errorResponse.getErrorMsg(), mockErroresponse.getErrorMsg());
			
			assertEquals(errorResponse.getErrorCode(), mockErroresponse.getErrorCode());
			
		} 
		catch (DataNotFoundException e) 
		{
			logger.debug("Data not found for product in test sucess.");
		}
	}
}