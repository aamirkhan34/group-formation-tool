package com.group1.SDCapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = {SecurityAutoConfiguration.class})
public class SdCapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SdCapplicationApplication.class, args);
	}

}
