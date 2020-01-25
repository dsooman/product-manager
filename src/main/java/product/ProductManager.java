package product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A Spring Boot microservice used to manage Products current and minimum
 * required stock levels and to generate a report that indicates Products and
 * amounts of Products to be ordered in order to meet minimum stock level
 * requirements.
 */
@SpringBootApplication
public class ProductManager {

	public static void main(final String[] args) {
		SpringApplication.run(ProductManager.class, args);
	}
}
