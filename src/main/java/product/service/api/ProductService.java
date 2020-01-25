package product.service.api;

import java.util.Set;

import product.model.Product;
import product.model.ProductPurchaseRequirement;

/**
 * Service for creating, blocking, deleting and setting values on
 * <code>Product</code> instances, the latter being used in rules that govern
 * whether they should be reordered or not in order to maintain specified stock
 * levels.
 */
public interface ProductService {

	/**
	 * Block a <code>Product</code> by name.
	 *
	 * @param name - name of the <code>Product</code>
	 */
	void block(String name);

	/**
	 * Create a new <code>Product</code> by initially specifying its name.
	 * <P>
	 * If this method is called on a Product name that is already present, then the
	 * existing Product will be deleted and a new Product will be created that has
	 * the default creation values.
	 *
	 * @param name - name of the <code>Product</code>
	 * @return the <code>Product</code> that has been created. Since this involves
	 *         the persistence of the <code>Product</code> in a relational database,
	 *         one of the populated fields will be a primary key value, which is not
	 *         directly used in the code at this time.
	 */
	Product create(String name);

	/**
	 * Deletes a <code>Product</code> by name.
	 *
	 * @param name - name of the <code>Product</code>
	 */
	void delete(String name);

	/**
	 * Gets a <code>Set</code> of all <code>ProductPurchaseRequirement</code> for
	 * all <code>Product</code> currently recorded. The logic governing if a
	 * <code>Product</code> appears in this set is contained within the
	 * <code>Product</code> class.
	 * <P>
	 * <code>Set</code> is unmodifiable because this is a read only report.
	 *
	 * @return <code>Set</code> of applicable
	 *         <code>ProductPurchaseRequirement</code> for all <code>Product</code>
	 *         currently recorded.
	 */
	Set<ProductPurchaseRequirement> getProductPurchaseRequirements();

	/**
	 * Sets the current amount held of the specified <code>Product</code>.
	 *
	 * @param name   - name of the <code>Product</code>
	 * @param amount - amount of the <code>Product</code> in stock
	 */
	void setCurrentAmount(String name, Long amount);

	/**
	 * Sets the minimum amount that should be held of the specified
	 * <code>Product</code>. The requirement to purchase additional stock of this
	 * <code>Product</code> can be overidden by blocking the <code>Product</code>.
	 *
	 * @param name   - name of the <code>Product</code>
	 * @param amount - minimum amount of the <code>Product</code> which should be in
	 *               stock.
	 */
	void setMinAmount(String name, Long amount);

	/**
	 * Unblock a <code>Product</code> by name.
	 *
	 * @param name - name of the <code>Product</code>
	 */
	void unblock(String name);
}