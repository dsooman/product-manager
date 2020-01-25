package product.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import product.exception.InvalidProductException;
import product.exception.ProductUpdateException;
import product.model.Product;
import product.model.ProductPurchaseRequirement;
import product.repository.ProductPurchaseRequirementRepository;
import product.repository.ProductRepository;
import product.service.api.ProductService;
import product.utility.TimeUtilities;

/**
 * An implementation of ProductService which leverages Spring Data for
 * persistence.
 */
@Service
public class ProductServiceImpl implements ProductService {

	/**
	 * The ProductPurchaseRequirementRepository.
	 */
	@Autowired
	private ProductPurchaseRequirementRepository productPurchaseRequirementRepository;

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

		// TODO: there is probably a better way of doing this!

		Optional<Product> optionalProduct = this.getByName(name);
		if (optionalProduct.isPresent()) {
			this.delete(name);
		}

		final Product product = new Product();
		product.setName(name);
		product.setBlocked(false);
		product.setMinAmount((long) 0);
		product.setCurrentAmount((long) 0);
		this.save(product);

		optionalProduct = this.getByName(product.getName());
		if (optionalProduct.isPresent()) {
			return optionalProduct.get();
		}

		throw new ProductUpdateException("Could not save product with name " + name);
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
	public Set<ProductPurchaseRequirement> getProductPurchaseRequirements() {

		final Long currentTime = TimeUtilities.getCurrentTime();

		final List<Product> products = new ArrayList<>();
		this.productRepository.findAll().forEach(product -> products.add(product));
		return Collections.unmodifiableSet(products.stream().map(Product::getProductPurchaseRequirement)
				.filter(Optional::isPresent).map(Optional::get).map(productPurchaseRequirement -> {
					productPurchaseRequirement.setTime(currentTime);
					this.productPurchaseRequirementRepository.save(productPurchaseRequirement);
					return productPurchaseRequirement;
				}).collect(Collectors.toSet()));
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
