package product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ProductCreationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a <code>ProductCreationException</code> with the specified detail
	 * message.
	 *
	 * @param s the detail message.
	 */
	public ProductCreationException(final String s) {
		super(s);
	}
}
