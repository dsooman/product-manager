package product.model;

public class ProductPurchaseRequirement {

	private Long amount;

	private String name;

	public Long getAmount() {
		return this.amount;
	}

	public String getName() {
		return this.name;
	}

	public void setAmount(final Long amount) {
		this.amount = amount;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
