package product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidProductException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a <code>InvalidProductException</code> with the specified detail
	 * message.
	 *
	 * @param s the detail message.
	 */
	public InvalidProductException(final String s) {
		super(s);
	}
}
