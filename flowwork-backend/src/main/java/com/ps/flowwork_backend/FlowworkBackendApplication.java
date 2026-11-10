package com.ps.flowwork_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class FlowworkBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowworkBackendApplication.class, args);
	}

}
