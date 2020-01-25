package product.service.api;

import product.model.Product;

/**
 * Service for managing Products and their rules.
 */
public interface ProductService {

	Product save(final Product product);

	Product getByName(final String name);
}
