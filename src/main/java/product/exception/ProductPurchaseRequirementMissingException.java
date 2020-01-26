package product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This Exception is thrown when there is an attempt to get the latest product
 * purchase requirements and there are none because they have never been
 * generated.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductPurchaseRequirementMissingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a <code>ProductPurchaseRequirementMissingException</code> with the
	 * specified detail message.
	 *
	 * @param s the detail message.
	 */
	public ProductPurchaseRequirementMissingException(final String s) {
		super(s);
	}
}