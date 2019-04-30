/**
 * 
 */
package org.kruger.calltaxi.services.exception;

import java.util.List;

import org.kruger.calltaxi.services.response.models.ErrorDetails;

/**
 * @author Srikar Reddy
 *
 */
public class RequestBodyValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final List<ErrorDetails> errors;

	public RequestBodyValidationException(List<ErrorDetails> errors) {
		this.errors = errors;
	}

	public List<ErrorDetails> getErrors() {
		return errors;
	}
}
