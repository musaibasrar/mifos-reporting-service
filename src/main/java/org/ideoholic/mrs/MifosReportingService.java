package org.ideoholic.mrs;

import org.ideoholic.mrs.dao.ItemRepository;
import org.ideoholic.mrs.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Springdoc OpenAPI documentation: http://localhost:8080/swagger-ui.html
 * OpenAPI specification (in JSON format): http://localhost:8080/v3/api-docs
 * To access H2 database: http://localhost:8080/h2-console
 */
@SpringBootApplication
public class MifosReportingService implements CommandLineRunner {

	@Autowired
	ItemRepository itemRepository;

	public static void main(String[] args) {
		SpringApplication.run(MifosReportingService.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		itemRepository.save(new Item("Item 1"));
		itemRepository.save(new Item("Item 2"));
		itemRepository.save(new Item("Item 3"));
		itemRepository.save(new Item("Item 4"));
		itemRepository.save(new Item("Item 5"));
	}
}
