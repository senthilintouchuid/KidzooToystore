package com.kidzoo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ToyConfiguration {
	
	@Bean("toyRestTemplate")
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
