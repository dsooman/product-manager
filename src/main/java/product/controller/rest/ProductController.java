package product.controller.rest;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import product.model.Product;
import product.model.ProductPurchaseRequirement;
import product.service.api.ProductService;

/**
 * REST controller for managing Products.
 */
@RestController
@RequestMapping("/v1/")
public class ProductController {

	/**
	 * In order to have a controller light design, we will just be doing straight
	 * delegation to this service within the controller method bodies..
	 */
	@Autowired
	ProductService productService;

	/**
	 * Block a Product.
	 * 
	 * @param name - name of the Product to block.
	 */
	@PatchMapping("/block/{name}")
	public void block(@PathVariable(value = "name") String name) {
		// TODO Auto-generated method stub

	}

	/**
	 * Create a Product.
	 * 
	 * @param name- name of the Product to create.
	 * @return Product that was created
	 */
	@PostMapping("/create")
	public Product create(@RequestBody String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Delete a Product.
	 * 
	 * @param name - name of the Product to be deleted.
	 */
	@DeleteMapping
	public void delete(@RequestBody final String name) {
		// TODO Auto-generated method stub

	}

	/**
	 * Gets the Last generated Product purchase requirements. Will return 404 if
	 * /productPurchaseRequirements has never been ran.
	 * 
	 * @return Set of Product purchase requirements
	 */
	@GetMapping("/latestProductPurchaseRequirements")
	public Set<ProductPurchaseRequirement> getLatestProductPurchaseRequirements() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Generates and gets Product purchase requirements, based on rules that have
	 * been defined for Products.
	 * 
	 * @return Set of Product purchase requirements
	 */
	@GetMapping("/productPurchaseRequirements")
	public Set<ProductPurchaseRequirement> getProductPurchaseRequirements() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Overrides a Product amount to purchase, regardless if the Product is blocked
	 * or has a different minimum stock level already set.
	 * 
	 * @param name   - name of the Product
	 * @param amount - amount of the Product that is required
	 */
	@PatchMapping("/override/{name}/{amount}")
	public void overrideMinAmount(@PathVariable(value = "name") String name,
			@PathVariable(value = "amount") Long amount) {
		// TODO Auto-generated method stub

	}

	/**
	 * Sets the current amount of a Product in stock.
	 * 
	 * @param name   - name of the Product
	 * @param amount - amount of the Product in stock
	 */
	@PatchMapping("/current/{name}/{amount}")
	public void setCurrentAmount(@PathVariable(value = "name") String name,
			@PathVariable(value = "amount") Long amount) {
		// TODO Auto-generated method stub

	}

	/**
	 * Sets the minimum required amount of the Product to have in stock.
	 * 
	 * @param name   - name of the Product
	 * @param amount - amount of the Product required to be in stock
	 */
	@PatchMapping("/min/{name}/{amount}")
	public void setMinAmount(@PathVariable(value = "name") String name, @PathVariable(value = "amount") Long amount) {
		// TODO Auto-generated method stub

	}

	/**
	 * Unblock a Product.
	 * 
	 * @param name - name of the Product to unblock.
	 */
	@PatchMapping("/unblock/{name}")
	public void unblock(@PathVariable(value = "name") String name) {
		// TODO Auto-generated method stub

	}
}
