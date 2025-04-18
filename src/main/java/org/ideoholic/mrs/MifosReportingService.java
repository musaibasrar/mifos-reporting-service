package org.ideoholic.mrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Springdoc OpenAPI documentation: http://localhost:8080/swagger-ui.html
 * OpenAPI specification (in JSON format): http://localhost:8080/v3/api-docs
 * To access H2 database: http://localhost:8080/h2-console
 */
@SpringBootApplication
public class MifosReportingService {

	public static void main(String[] args) {
		SpringApplication.run(MifosReportingService.class, args);
	}

}
