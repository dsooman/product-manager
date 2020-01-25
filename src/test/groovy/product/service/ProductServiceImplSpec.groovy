package product.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.test.context.ContextConfiguration

import product.ProductManager
import product.model.Product
import product.repository.ProductRepository
import product.service.impl.ProductServiceImpl
import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@Narrative(""" ProductServiceImpl is implementation of ProductService,
which is a service for managing Products and their rules.
""")
@Title("Tests for ProductServiceImpl")
@Subject(ProductServiceImpl)
@ContextConfiguration(classes=ProductManager.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ProductServiceImplSpec extends Specification {

	@Autowired
	ProductServiceImpl productService

	@Autowired
	ProductRepository productRepository

	def setup() {
		productRepository.deleteAll()
	}

	def cleanup() {
	}

	def "ProductServiceImpl can create a Product and then retrieve it"() {

		given: "a new product is created and saved"
		Product product = new Product()
		product.name = "A"
		product.blocked = true
		product.minAmount = 5
		product.currentAmount = 10
		productService.save(product)

		when: "the product is retrieved by name"
		Product retrieved_product = productService.getByName("A")

		then: "the retrieved product will have the same values as the saved product"
		retrieved_product.name == product.name
		retrieved_product.blocked == true
		retrieved_product.minAmount == product.minAmount
		retrieved_product.currentAmount == product.currentAmount
	}
}
