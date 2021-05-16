package com.letslearn.eventify;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EventifyApplication {
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(EventifyApplication.class, args);
	}

}
