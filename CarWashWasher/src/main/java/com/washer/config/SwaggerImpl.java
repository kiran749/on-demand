package com.washer.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerImpl {

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.washer"))
				.build()
				.apiInfo(apiDetails());
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Address book API for Washer Service", 
				"<h1>This is sample documentation for the Washer Service App<h1>", 
				"1.0", 
				"Free to use",
				new springfox.documentation.service.Contact("Kiran Reddy", "http://codeShark.com","gkiranreddy64@gmail.com"),
				"API License", 
				"http://codeShark.com", 
				Collections.emptyList());

	}	
	
	
}
