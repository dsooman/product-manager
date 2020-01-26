package product.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import product.model.ProductPurchaseRequirement;

public interface ProductPurchaseRequirementRepository extends CrudRepository<ProductPurchaseRequirement, Long> {

	@Query(value = "select max(time) from product_purchase_requirement", nativeQuery = true)
	Long findMaxTime();
	
	@Query(value = "select * from product_purchase_requirement where time = :time", nativeQuery = true)
	Set<ProductPurchaseRequirement> findProductPurchaseRequirementsByTime(Long time);
}
