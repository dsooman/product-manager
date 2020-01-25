package product.service.api;

import java.util.List;

import product.model.Product;
import product.model.ProductPurchaseRequirement;

/**
 * Service for managing Products and their rules.
 */
public interface ProductService {

	void block(String name);

	Product create(String name);

	void delete(String name);

	List<ProductPurchaseRequirement> getProductPurchaseRequirements();

	void setCurrentAmount(String name, Long amount);

	void setMinAmount(String name, Long amount);

	void unblock(String name);
}
