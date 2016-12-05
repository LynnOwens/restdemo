package net.tofweb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Lynn Owens
 *
 *         Exception for when the user submits a Customer that fails validation
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidCustomerException extends RuntimeException {

	private static final long serialVersionUID = -3434176837042718148L;

	public InvalidCustomerException() {
		super();
	}

	public InvalidCustomerException(String errorMessage) {
		super(errorMessage);
	}

	public InvalidCustomerException(Throwable arg0) {
		super(arg0);
	}
}
