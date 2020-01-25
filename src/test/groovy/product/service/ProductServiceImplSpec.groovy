package product.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.test.context.ContextConfiguration

import product.ProductManager
import product.model.Product
import product.model.ProductPurchaseRequirement
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

		given: "a new Product is created and saved"
		Product product = new Product()
		product.name = "A"
		product.blocked = true
		product.minAmount = 5
		product.currentAmount = 10
		productService.save(product)

		when: "the Product is retrieved by name"
		Product retrieved_product = productService.getByName("A").get()

		then: "the retrieved Product will have the same values as the saved Product"
		retrieved_product.name == product.name
		retrieved_product.blocked == true
		retrieved_product.minAmount == product.minAmount
		retrieved_product.currentAmount == product.currentAmount
	}

	def "ProductServiceImpl can block a Product and it will not appear on Product purchase purchase requirements results"() {

		given: "two new Products are created and saved, where they require additional purchasing to maintain desired stock levels"
		Product product1 = productService.create("A")
		product1.blocked = true
		product1.minAmount = 10
		product1.currentAmount = 5
		productService.save(product1)
		Product product2 = productService.create("B")
		product2.minAmount = 15
		product2.currentAmount = 10
		productService.save(product2)

		when: "one of the Products is blocked"
		productService.block(product1.name)

		then: "the blocked Product will not appear on the Product purchase purchase requirements results"
		Set<ProductPurchaseRequirement> productPurchaseRequirements = productService.getProductPurchaseRequirements()
		String foundProduct1 = "";
		String foundProduct2 = "";

		for (ProductPurchaseRequirement productPurchaseRequirement: productPurchaseRequirements) {
			if (productPurchaseRequirement.name == "A") {
				foundProduct1 = "A"
			}
			if (productPurchaseRequirement.name == "B") {
				foundProduct2 = "B"
			}
		}

		foundProduct1 == ""
		foundProduct2 == "B"
	}
}
