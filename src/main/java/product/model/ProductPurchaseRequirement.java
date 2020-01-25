package product.model;

public class ProductPurchaseRequirement {

	/**
	 * The amount of the <code>Product</code> to be ordered in order to meet the
	 * minimum stock level requirement.
	 */
	private Long amount;

	/**
	 * The name of the <code>Product</code>
	 */
	private String name;

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
}
