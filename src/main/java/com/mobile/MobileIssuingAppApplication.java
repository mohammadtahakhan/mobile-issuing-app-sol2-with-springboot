package com.mobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.mobile"})
public class MobileIssuingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileIssuingAppApplication.class, args);
	}

}
