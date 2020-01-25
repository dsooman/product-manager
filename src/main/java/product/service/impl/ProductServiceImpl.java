package product.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import product.exception.InvalidProductException;
import product.exception.ProductCreationException;
import product.model.Product;
import product.model.ProductPurchaseRequirement;
import product.repository.ProductRepository;
import product.service.api.ProductService;

/**
 * An implementation of ProductService which leverages Spring Data for
 * persistence.
 */
@Service
public class ProductServiceImpl implements ProductService {

	/**
	 * The ProductRepository.
	 */
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void block(final String name) {
		Objects.requireNonNull(name);
		final Optional<Product> productOptional = this.getByName(name);
		if (productOptional.isEmpty()) {
			throw new InvalidProductException("Could not block product " + name + " as is does not exist");
		}

		final Product product = productOptional.get();
		product.setBlocked(true);
		this.save(product).orElseThrow(() -> new ProductCreationException("Could not save product with name " + name));
	}

	@Override
	public Product create(final String name) {
		Objects.requireNonNull(name);
		final Product product = new Product();
		product.setName(name);
		product.setBlocked(false);
		product.setMinAmount((long) 0);
		product.setCurrentAmount((long) 0);
		return this.save(product)
				.orElseThrow(() -> new ProductCreationException("Could not create product with name " + name));
	}

	@Override
	public void delete(final String name) {
		Objects.requireNonNull(name);
		if (this.getByName(name).isEmpty()) {
			throw new InvalidProductException("Could not delete product " + name + " as is does not exist");
		}
		this.productRepository.deleteByName(name);
	}

	private Optional<Product> getByName(final String name) {
		Objects.requireNonNull(name);
		return Optional.of(this.productRepository.findByName(name));
	}

	@Override
	public List<ProductPurchaseRequirement> getProductPurchaseRequirements() {
		// TODO Auto-generated method stub
		return null;
	}

	private Optional<Product> save(final Product product) {
		Objects.requireNonNull(product);
		this.productRepository.save(product);
		return Optional.ofNullable(this.productRepository.findByName(product.getName()));
	}

	@Override
	public void setCurrentAmount(final String name, final Long amount) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(amount);
		final Optional<Product> productOptional = this.getByName(name);
		if (productOptional.isEmpty()) {
			throw new InvalidProductException(
					"Could not set current amount on product " + name + " as is does not exist");
		}

		final Product product = productOptional.get();
		product.setCurrentAmount(amount);
		this.save(product).orElseThrow(() -> new ProductCreationException("Could not save product with name " + name));
	}

	@Override
	public void setMinAmount(final String name, final Long amount) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(amount);
		final Optional<Product> productOptional = this.getByName(name);
		if (productOptional.isEmpty()) {
			throw new InvalidProductException(
					"Could not set minimum amount on product " + name + " as is does not exist");
		}

		final Product product = productOptional.get();
		product.setMinAmount(amount);
		this.save(product).orElseThrow(() -> new ProductCreationException("Could not save product with name " + name));
	}

	@Override
	public void unblock(final String name) {
		Objects.requireNonNull(name);
		final Optional<Product> productOptional = this.getByName(name);
		if (productOptional.isEmpty()) {
			throw new InvalidProductException("Could not unblock product " + name + " as is does not exist");
		}

		final Product product = productOptional.get();
		product.setBlocked(false);
		this.save(product).orElseThrow(() -> new ProductCreationException("Could not save product with name " + name));
	}
}
