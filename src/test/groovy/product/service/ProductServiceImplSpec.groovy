package product.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.test.context.ContextConfiguration

import product.ProductManager
import product.model.Product
import product.model.ProductPurchaseRequirement
import product.repository.ProductPurchaseRequirementRepository
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

	@Autowired
	ProductPurchaseRequirementRepository productPurchaseRequirementRepository

	def setup() {
		productRepository.deleteAll()
		productPurchaseRequirementRepository.deleteAll()
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

		given: "some new Products are created and saved, where they require additional purchasing to maintain desired stock levels"
		productService.create("A")
		productService.setMinAmount("A", 10)
		productService.setCurrentAmount("A", 5)
		productService.create("B")
		productService.setMinAmount("B", 10)
		productService.setCurrentAmount("B", 5)
		Product product3 = productService.create("C")
		productService.setMinAmount("C", 10)
		productService.setCurrentAmount("C", 5)

		when: "one of the Products is blocked"
		productService.block("A")

		then: "the blocked Product will not appear on the Product purchase purchase requirements results"
		Set<ProductPurchaseRequirement> productPurchaseRequirements = productService.getProductPurchaseRequirements()
		String foundProduct1 = "";
		String foundProduct2 = "";
		String foundProduct3 = "";

		for (ProductPurchaseRequirement productPurchaseRequirement: productPurchaseRequirements) {
			if (productPurchaseRequirement.name == "A") {
				foundProduct1 = productPurchaseRequirement.name
			}
			if (productPurchaseRequirement.name == "B") {
				foundProduct2 = productPurchaseRequirement.name
			}
			if (productPurchaseRequirement.name == "C") {
				foundProduct3 = productPurchaseRequirement.name
			}
		}

		foundProduct1 == ""
		foundProduct2 == "B"
		foundProduct3 == "C"
	}

	def "ProductServiceImpl can generate Product purchase requirements based on Product rules and then additionally add or amend them"() {

		given: "some new Products are created and saved, where they require additional purchasing to maintain desired stock levels"
		productService.create("A")
		productService.setMinAmount("A", 10)
		productService.setCurrentAmount("A", 5)
		productService.block("A")
		productService.create("B")
		productService.setMinAmount("B", 10)
		productService.setCurrentAmount("B", 5)
		Product product3 = productService.create("C")
		productService.setMinAmount("C", 10)
		productService.setCurrentAmount("C", 5)

		when: "a Product purchase requirements are generated and then amended / added to"
		//This will work whether there is a call to getProductPurchaseRequirements() or not...
		productService.getProductPurchaseRequirements()
		productService.overrideMinAmount("A", 20)

		then: "getting the latest Product purchase requirements will contain the amendment / addition"

		Set<ProductPurchaseRequirement> latestProductPurchaseRequirements = productService.getLatestProductPurchaseRequirements()

		//Note that Product A was blocked above, but now that has been overridden...
		Long foundProductAmount = null;
		for (ProductPurchaseRequirement productPurchaseRequirement: latestProductPurchaseRequirements) {
			if (productPurchaseRequirement.name == "A") {
				foundProductAmount = productPurchaseRequirement.amount
			}
		}

		foundProductAmount == 20
	}
}
