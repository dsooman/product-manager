package product.repository;

import org.springframework.data.repository.CrudRepository;

import product.model.ProductPurchaseRequirement;

public interface ProductPurchaseRequirementRepository extends CrudRepository<ProductPurchaseRequirement, Long> {

}
