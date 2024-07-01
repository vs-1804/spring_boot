package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Config {

	@Bean("cls")
	@Scope("prototype")
	String cls() {
       System.out.println("bean cls");
		return new String();
	}
	@Bean
	String cls1() {
		 System.out.println("bean cls1");
		return new String();
	}
}
