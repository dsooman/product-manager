package product.model;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Product represents a product that can possibly be reordered, depending on
 * business rules.
 */
@Entity
@Table(name = "product")
public class Product {

	/**
	 * <code>true</code> if this <code>Product</code> is blocked and should not be
	 * purchased, even if the minimum required stock level is below the current
	 * stock level.
	 */
	@Column(name = "blocked", nullable = true)
	private Boolean blocked;

	/**
	 * The current amount of stock of this <code>Product</code>
	 */
	@Column(name = "current_amount", nullable = true)
	private Long currentAmount;

	/**
	 * Database primary key value.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long ID;

	/**
	 * The specified minimum amount of stock of this <code>Product</code> that
	 * should be present
	 */
	@Column(name = "min_amount", nullable = true)
	private Long minAmount;

	/**
	 * The name of this <code>Product</code>
	 */
	@Column(name = "name", nullable = false)
	private String name;

	/**
	 * Gets if the <code>Product</code> is blocked from further ordering.
	 * 
	 * @return <code>true</code> if the <code>Product</code> is blocked from further
	 *         ordering.
	 */
	public Boolean getBlocked() {
		return this.blocked;
	}

	/**
	 * Gets the current amount of the <code>Product</code>.
	 * 
	 * @return the current amount of the <code>Product</code>
	 */
	public Long getCurrentAmount() {
		return this.currentAmount;
	}

	/**
	 * Gets the ID of the <code>Product</code>.
	 * 
	 * @return the ID of the <code>Product</code>
	 */
	public Long getID() {
		return this.ID;
	}

	/**
	 * Get the minimum amount of stock of this <code>Product</code> that should be
	 * present.
	 * 
	 * @return the minimum amount of stock of this <code>Product</code> that should
	 *         be present
	 */
	public Long getMinAmount() {
		return this.minAmount;
	}

	/**
	 * Gets the name of the <code>Product</code>.
	 * 
	 * @return name of the <code>Product</code>
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets if the <code>Product</code> is blocked from further ordering.
	 * 
	 * @param blocked - blocked if the <code>Product</code> is blocked from further
	 *                ordering.
	 */
	public void setBlocked(final Boolean blocked) {
		this.blocked = blocked;
	}

	/**
	 * Sets the current amount of the <code>Product</code>.
	 * 
	 * @param currentAmount - current amount of the <code>Product</code>
	 */
	public void setCurrentAmount(final Long currentAmount) {
		this.currentAmount = currentAmount;
	}

	/**
	 * Sets the ID of the <code>Product</code>.
	 * 
	 * @param ID - ID of the <code>Product</code>
	 */
	public void setID(final Long ID) {
		this.ID = ID;
	}

	/**
	 * Sets the minimum amount of stock of this <code>Product</code> that should be
	 * present
	 * 
	 * @param minAmount - minimum amount of stock of this <code>Product</code> that
	 *                  should be present
	 */
	public void setMinAmount(final Long minAmount) {
		this.minAmount = minAmount;
	}

	/**
	 * Sets the name of the <code>Product</code>
	 * 
	 * @param name - name of the <code>Product</code>
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Gets a Product purchase requirement for this Product, if applicable. In order
	 * to be applicable, the Product must not be blocked, and must have a minimum
	 * stock level that is under that of the current stock level, indicating that an
	 * action to purchase more stock of this Product is required.
	 * <P>
	 * There is some debate in software engineering where this logic should reside,
	 * in this Product class or in the ProductService class. Some books I have read
	 * indicate that the best location would be inside the Product class, with the
	 * logic part of the entity as opposed to in the service. Therefore, for this
	 * purposes of this exercise I have placed it here.
	 * 
	 * 
	 * @return <code>ProductPurchaseRequirement</code> for this
	 *         <code>Product</code>, if applicable
	 */
	public Optional<ProductPurchaseRequirement> getProductPurchaseRequirement() {

		ProductPurchaseRequirement productPurchaseRequirement = null;

		if (this.name != null && this.blocked != null && this.currentAmount != null && this.minAmount != null
				&& !this.blocked && this.currentAmount < this.minAmount) {

			productPurchaseRequirement = new ProductPurchaseRequirement();
			productPurchaseRequirement.setName(this.name);
			productPurchaseRequirement.setAmount(this.minAmount - this.currentAmount);

		}

		return Optional.ofNullable(productPurchaseRequirement);
	}
}
