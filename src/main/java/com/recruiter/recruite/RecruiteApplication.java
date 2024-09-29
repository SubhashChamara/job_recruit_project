package com.recruiter.recruite;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RecruiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruiteApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Job Recruitment Project")
						.version("1.0.0")
						.description("API documentation for the job recruitment microservice project services"));
	}

}
