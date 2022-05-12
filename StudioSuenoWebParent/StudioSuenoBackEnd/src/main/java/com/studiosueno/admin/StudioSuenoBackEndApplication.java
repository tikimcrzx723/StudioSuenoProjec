package com.studiosueno.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.studiosueno.common.entity", "com.studiosueno.admin.user"})
public class StudioSuenoBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudioSuenoBackEndApplication.class, args);
	}

}
