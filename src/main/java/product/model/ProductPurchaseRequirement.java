package product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProductPurchaseRequirement represents a requirement to make a Product
 * purchase in order to satisfy a minimum level of stock of the Product.
 */
@Entity
@Table(name = "product_purchase_requirement")
public class ProductPurchaseRequirement {

	/**
	 * The amount of the <code>Product</code> to be ordered in order to meet the
	 * minimum stock level requirement.
	 */
	@Column(name = "amount", nullable = false)
	private Long amount;

	/**
	 * Database primary key value.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long ID;

	/**
	 * The name of the <code>Product</code>
	 */
	@Column(name = "name", nullable = false)
	private String name;

	/**
	 * The time that this <code>ProductPurchaseRequirement</code> was generated.
	 */
	@Column(name = "time", nullable = false)
	private Long time;

	/**
	 * Get the amount of the <code>Product</code> to be ordered.
	 *
	 * @return the amount of the <code>Product</code> to be ordered
	 */
	public Long getAmount() {
		return this.amount;
	}

	/**
	 * Gets the name of the <code>Product</code>.
	 *
	 * @return the name of the <code>Product</code>
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the time that this <code>ProductPurchaseRequirement</code> was
	 * generated.
	 *
	 * @return time that this <code>ProductPurchaseRequirement</code> was generated
	 */
	public Long getTime() {
		return this.time;
	}

	/**
	 * Sets the amount of the <code>Product</code> to be ordered.
	 *
	 * @param amount the amount of the <code>Product</code> to be ordered
	 */
	public void setAmount(final Long amount) {
		this.amount = amount;
	}

	/**
	 * Sets the name of the <code>Product</code>.
	 *
	 * @param name the name of the <code>Product</code>
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Sets the time that this <code>ProductPurchaseRequirement</code> was
	 * generated.
	 *
	 * @param time - time that this <code>ProductPurchaseRequirement</code> was
	 *             generated.
	 */
	public void setTime(final Long time) {
		this.time = time;
	}
}
