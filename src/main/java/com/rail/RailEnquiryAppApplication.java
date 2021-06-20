package com.rail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:ValidationMessages.properties")
public class RailEnquiryAppApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(RailEnquiryAppApplication.class, args);
	}

}
