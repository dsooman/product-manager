package product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Column(name = "blocked", nullable = true)
	private Boolean blocked;

	@Column(name = "current_amount", nullable = true)
	private Long currentAmount;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long ID;

	@Column(name = "min_amount", nullable = true)
	private Long minAmount;

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
}
