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

	private Optional<Product> getByName(final String name) {
		Objects.requireNonNull(name);
		return Optional.of(this.productRepository.findByName(name));
	}

	private Optional<Product> save(final Product product) {
		Objects.requireNonNull(product);
		this.productRepository.save(product);
		return Optional.ofNullable(this.productRepository.findByName(product.getName()));
	}

	@Override
	public Product create(String name) {
		Objects.requireNonNull(name);
		Product product = new Product();
		product.setName(name);
		product.setBlocked(false);
		product.setMinAmount(Long.valueOf(0));
		product.setCurrentAmount(Long.valueOf(0));
		return this.save(product)
				.orElseThrow(() -> new ProductCreationException("Could not create Product with name " + name));
	}

	@Override
	public void delete(String name) {
		Objects.requireNonNull(name);
		if (this.getByName(name).isEmpty()) {
			throw new InvalidProductException("Cannot delete Product " + name + " as is does not exist");
		}
		productRepository.deleteByName(name);
	}

	@Override
	public void block(String name) {
		Objects.requireNonNull(name);
		Optional<Product> productOptional = this.getByName(name);
		if (productOptional.isEmpty()) {
			throw new InvalidProductException("Cannot block Product " + name + " as is does not exist");
		}

		Product product = productOptional.get();
		product.setBlocked(true);
		this.save(product).orElseThrow(() -> new ProductCreationException("Could not save Product with name " + name));
	}

	@Override
	public void unblock(String name) {
		Objects.requireNonNull(name);
		Optional<Product> productOptional = this.getByName(name);
		if (productOptional.isEmpty()) {
			throw new InvalidProductException("Cannot unblock Product " + name + " as is does not exist");
		}

		Product product = productOptional.get();
		product.setBlocked(false);
		this.save(product).orElseThrow(() -> new ProductCreationException("Could not save Product with name " + name));
	}

	@Override
	public void setCurrentAmount(String name, Long amount) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(amount);
		Optional<Product> productOptional = this.getByName(name);
		if (productOptional.isEmpty()) {
			throw new InvalidProductException("Cannot set current amount on Product " + name + " as is does not exist");
		}

		Product product = productOptional.get();
		product.setCurrentAmount(amount);
		this.save(product).orElseThrow(() -> new ProductCreationException("Could not save Product with name " + name));
	}

	@Override
	public void setMinAmount(String name, Long amount) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(amount);
		Optional<Product> productOptional = this.getByName(name);
		if (productOptional.isEmpty()) {
			throw new InvalidProductException("Cannot set minimum amount on Product " + name + " as is does not exist");
		}

		Product product = productOptional.get();
		product.setMinAmount(amount);
		this.save(product).orElseThrow(() -> new ProductCreationException("Could not save Product with name " + name));
	}

	@Override
	public List<ProductPurchaseRequirement> getProductPurchaseRequirements() {
		// TODO Auto-generated method stub
		return null;
	}
}
