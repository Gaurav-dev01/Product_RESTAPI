package io.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SpringBootConfiguration
public class ProductRestapiApplication { 

	public static void main(String[] args) {
		SpringApplication.run(ProductRestapiApplication.class, args);
		System.out.println("\nWelcome to Product REST API Application.!\n");
	}

}