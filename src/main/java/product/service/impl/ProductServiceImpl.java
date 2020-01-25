package product.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	private Product getByName(final String name) {
		Objects.requireNonNull(name);
		return this.productRepository.findByName(name);
	}

	private Product save(final Product product) {
		Objects.requireNonNull(product);
		this.productRepository.save(product);
		return this.productRepository.findByName(product.getName());
	}

	@Override
	public void create(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void block(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCurrentAmount(String name, Long amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMinAmount(String name, Long amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ProductPurchaseRequirement> getProductPurchaseRequirements() {
		// TODO Auto-generated method stub
		return null;
	}
}
