package com.target.retail;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RetailServiceWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(RetailServiceWebApplication.class, args);
	}

	@Bean
	//to give you more control over the API documentation generation process.
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.target.retail.controller"))
				.paths(PathSelectors.ant("/resources/1.0/products/*")).build().apiInfo(apiInfo());
	}
	
	//ApiInfo for customizing the application

	private ApiInfo apiInfo() {
		return new ApiInfo("Target Retail REST API", "API to Retrieve/Update Product Information.", "2.0",
				"Terms of service", new Contact("Chilukuri Mounica", "", ""), "", "", Collections.emptyList());
	}
}