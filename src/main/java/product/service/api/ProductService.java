package product.service.api;

import product.model.Product;

/**
 * Service for managing Products and their rules.
 */
public interface ProductService {

	Product getByName(final String name);

	Product save(final Product product);
}
