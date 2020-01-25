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

	public Boolean getBlocked() {
		return this.blocked;
	}

	public Long getCurrentAmount() {
		return this.currentAmount;
	}

	public Long getID() {
		return this.ID;
	}

	public Long getMinAmount() {
		return this.minAmount;
	}

	public String getName() {
		return this.name;
	}

	public void setBlocked(final Boolean blocked) {
		this.blocked = blocked;
	}

	public void setCurrentAmount(final Long currentAmount) {
		this.currentAmount = currentAmount;
	}

	public void setID(final Long iD) {
		this.ID = iD;
	}

	public void setMinAmount(final Long minAmount) {
		this.minAmount = minAmount;
	}

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
	 * @return <code>Optional</code> of <code>ProductPurchaseRequirement</code> for
	 *         this <code>Product</code>, if applicable
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
