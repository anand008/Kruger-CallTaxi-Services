/**
 * 
 */
package org.kruger.calltaxi.services.response.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Srikar Reddy
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "errors" })
public class ErrorResponse {
	@JsonProperty("errors")
	private List<ErrorDetails> errors;

	public List<ErrorDetails> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorDetails> errors) {
		this.errors = errors;
	}

}
