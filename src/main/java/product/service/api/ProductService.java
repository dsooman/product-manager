package product.service.api;

import java.util.List;

import product.model.ProductPurchaseRequirement;

/**
 * Service for managing Products and their rules.
 */
public interface ProductService {

	void create(String name);

	void delete(String name);

	void block(String name);

	void setCurrentAmount(String name, Long amount);

	void setMinAmount(String name, Long amount);

	List<ProductPurchaseRequirement> getProductPurchaseRequirements();

}
