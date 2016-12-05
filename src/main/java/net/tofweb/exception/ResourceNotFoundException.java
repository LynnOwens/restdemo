package net.tofweb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Lynn Owens
 * 
 *         This Exception is mapped to the Http 404 status code. It should be
 *         thrown to indicate that a resource was not found.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8621974651515467202L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(Throwable arg0) {
		super(arg0);
	}

}