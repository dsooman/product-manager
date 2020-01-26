package product.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import product.model.Product;

/**
 * CrudRepository for working with <code>Product</code> instances.
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

	/**
	 * Delete a <code>Product</code> by name.
	 *
	 * @param name - name of the <code>Product</code> to delete
	 * @return ID of the <code>Product</code> deleted
	 */
	@Modifying
	@Transactional
	Long deleteByName(String name);

	/**
	 * Finds and returns a <code>Product</code> by name.
	 *
	 * @param name - name of the <code>Product</code> to find and return
	 * @return the <code>Product</code> if it has found based on the specified name
	 */
	Product findByName(String name);
}
