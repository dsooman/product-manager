package product.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import product.model.Product;
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
	public Product save(Product product) {
		Objects.requireNonNull(product);
		productRepository.save(product);
		return productRepository.findByName(product.getName());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Product getByName(String name) {
		Objects.requireNonNull(name);
		return productRepository.findByName(name);
	}
}
