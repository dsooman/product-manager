package product.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import product.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	@Modifying
	@Transactional
	Long deleteByName(String name);

	Product findByName(String name);
}
