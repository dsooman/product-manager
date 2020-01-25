package product.repository;

import org.springframework.data.repository.CrudRepository;

import product.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	Product findByName(String name);
}
