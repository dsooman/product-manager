package product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown to indicate that an update to a <code>Product</code> was not
 * successful.
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ProductUpdateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a <code>ProductUpdateException</code> with the specified detail
	 * message.
	 *
	 * @param s the detail message.
	 */
	public ProductUpdateException(final String s) {
		super(s);
	}
}
