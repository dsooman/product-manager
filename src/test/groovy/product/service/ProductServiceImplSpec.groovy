package product.service

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.test.context.ContextConfiguration

import product.ProductManager
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

	def setup() {
	}

	def cleanup() {
	}

	def "ProductServiceImpl can create a Product and then retrieve it"() {

		given: "this is a dummy test"

		when: "strings a and b are created"
		String a = "example"
		String b = "example"

		then: "a and b will equal each other"
		a == b
	}
}
