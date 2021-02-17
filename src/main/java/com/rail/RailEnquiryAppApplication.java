package com.rail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:ValidationMessages.properties")
public class RailEnquiryAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RailEnquiryAppApplication.class, args);
	}

}
