package net.tofweb.controller.advice;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import net.tofweb.exception.InvalidCustomerException;
import net.tofweb.exception.ResourceNotFoundException;

/**
 * @author Lynn Owens
 *
 *         Cross cutting Controller advice which will handle exceptions bubbling
 *         up out of the domain. Appropriate responses will be returned. This
 *         prevents information leak to the client such as package names or
 *         stack traces, while simultaneously consolidating such configuration.
 */
@ControllerAdvice
public class ExceptionHandlingAdvice extends ResponseEntityExceptionHandler {

	private static final Logger logger = LogManager.getLogger(ExceptionHandlingAdvice.class);

	/**
	 * In the event that a resource was not found, respond with a 404
	 * 
	 * @param ex
	 * @param request
	 * @return Http Status 404
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	protected ResponseEntity<Object> handleNotFound(ResourceNotFoundException ex, WebRequest request) {
		String message = "Customer not found";
		return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	/**
	 * In the event that a user-submitted Customer fails validations, respond
	 * with a 400
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(InvalidCustomerException.class)
	protected ResponseEntity<Object> handleInvalidCustomer(InvalidCustomerException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	/**
	 * In the event of any RuntimeException, respond with a 500
	 * 
	 * @param ex
	 * @param request
	 * @return Http Status 500
	 */
	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<Object> handleServerError(RuntimeException ex, WebRequest request) {
		String message = "Internal server error";
		logger.error(message, ex);
		return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
}
