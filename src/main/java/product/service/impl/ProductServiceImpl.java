package product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import product.exception.InvalidProductException;
import product.exception.ProductUpdateException;
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void block(final String name) {
		Objects.requireNonNull(name);

		final Optional<Product> productOptional = this.getByName(name);

		if (productOptional.isEmpty()) {
			throw new InvalidProductException("Could not block product " + name + " as is does not exist");
		}

		final Product product = productOptional.get();
		product.setBlocked(true);
		this.save(product).orElseThrow(() -> new ProductUpdateException("Could not save product with name " + name));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Product create(final String name) {
		Objects.requireNonNull(name);

		final Product product = new Product();
		product.setName(name);
		product.setBlocked(false);
		product.setMinAmount((long) 0);
		product.setCurrentAmount((long) 0);

		return this.save(product)
				.orElseThrow(() -> new ProductUpdateException("Could not create product with name " + name));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final String name) {
		Objects.requireNonNull(name);

		if (this.getByName(name).isEmpty()) {
			throw new InvalidProductException("Could not delete product " + name + " as is does not exist");
		}

		this.productRepository.deleteByName(name);
	}

	/**
	 * Gets an <code>Optional</code> of a <code>Product</code> by name.
	 * 
	 * @param name - name of the <code>Product</code>
	 * @return the <code>Optional</code> of a <code>Product</code>, which will be
	 *         empty if the <code>Product</code> was not found
	 */
	private Optional<Product> getByName(final String name) {
		Objects.requireNonNull(name);
		return Optional.ofNullable(this.productRepository.findByName(name));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ProductPurchaseRequirement> getProductPurchaseRequirements() {
		List<Product> products = new ArrayList<Product>();
		productRepository.findAll().forEach(product -> products.add(product));
		return products.stream().map(Product::getProductPurchaseRequirement).filter(Optional::isPresent)
				.map(Optional::get).collect(Collectors.toList());
	}

	/**
	 * Saves a <code>Product</code>.
	 * 
	 * @param product - the <code>Product</code> to save.
	 * @return <code>Optional</code> of the <code>Product</code>
	 */
	private Optional<Product> save(final Product product) {
		Objects.requireNonNull(product);
		this.productRepository.save(product);
		return Optional.ofNullable(this.productRepository.findByName(product.getName()));
	}

	/**
	 * {@inheritDoc}
	 */
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
		this.save(product).orElseThrow(() -> new ProductUpdateException("Could not save product with name " + name));
	}

	/**
	 * {@inheritDoc}
	 */
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
		this.save(product).orElseThrow(() -> new ProductUpdateException("Could not save product with name " + name));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void unblock(final String name) {
		Objects.requireNonNull(name);

		final Optional<Product> productOptional = this.getByName(name);

		if (productOptional.isEmpty()) {
			throw new InvalidProductException("Could not unblock product " + name + " as is does not exist");
		}

		final Product product = productOptional.get();
		product.setBlocked(false);
		this.save(product).orElseThrow(() -> new ProductUpdateException("Could not save product with name " + name));
	}
}
