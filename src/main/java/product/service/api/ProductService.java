package product.service.api;

import product.model.Product;

/**
 * Service for working with Products.
 */
public interface ProductService {

	Product save(final Product product);

	Product getByName(final String name);
}
