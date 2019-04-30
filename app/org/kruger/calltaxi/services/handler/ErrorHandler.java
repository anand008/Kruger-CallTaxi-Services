/**
 * 
 */
package org.kruger.calltaxi.services.handler;

import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;

import org.kruger.calltaxi.services.exception.HeaderValidationException;
import org.kruger.calltaxi.services.exception.QueryParameterValidationException;
import org.kruger.calltaxi.services.exception.RequestBodyValidationException;
import org.kruger.calltaxi.services.response.models.ErrorDetails;
import org.kruger.calltaxi.services.response.models.ErrorResponse;

import play.api.test.Helpers;
import play.http.HttpErrorHandler;
import play.libs.Json;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import play.mvc.Results;

/**
 * error handler responsible for handling exceptions occurred during service
 * usage.
 * 
 * @author Srikar Reddy
 *
 */
public class ErrorHandler implements HttpErrorHandler {

	/**
	 * gets invoked during a client error.
	 */
	public CompletionStage<Result> onClientError(RequestHeader request, int statusCode, String message) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrors(new LinkedList<ErrorDetails>());
		ErrorDetails errorDetails = new ErrorDetails();
		switch (statusCode) {
		case 400:
			errorDetails.setErrorCode("KCTS-BAD-REQUEST");
			errorDetails.setErrorDescription(
					"request provided seems to be syntactically or semantically incorrect, please try validating data before service hit");
			break;
		case 404:
			errorDetails.setErrorCode("KCTS-RESOURCE-NOT-FOUND");
			errorDetails.setErrorDescription(
					"request provided seems to be syntactically or semantically incorrect, please try validating data before service hit");
			break;
		default:
			errorDetails.setErrorCode("KCTS-UNKNOWN-CLIENT-ERROR");
			errorDetails.setErrorDescription("unknown error occurred on the client");
			break;
		}
		errorResponse.getErrors().add(errorDetails);
		return CompletableFuture.completedFuture(Results.status(statusCode, Json.toJson(errorResponse))
				.withHeader("content-type", "content-type=application/json"));

	}

	/**
	 * gets invoked when client successfully sent a request but it could not be
	 * processed due to some issue.
	 */
	public CompletionStage<Result> onServerError(RequestHeader request, Throwable exception) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrors(new LinkedList<ErrorDetails>());
		Throwable causalExceptionInstance = exception instanceof CompletionException ? exception.getCause() : exception;
		int statusCode = 0;
		if (causalExceptionInstance instanceof HeaderValidationException) {
			errorResponse.getErrors().addAll(((HeaderValidationException) causalExceptionInstance).getErrors());
			statusCode = Helpers.PRECONDITION_FAILED();
		} else if (causalExceptionInstance instanceof QueryParameterValidationException) {
			errorResponse.getErrors().addAll(((QueryParameterValidationException) causalExceptionInstance).getErrors());
			statusCode = Helpers.PRECONDITION_FAILED();
		} else if (causalExceptionInstance instanceof RequestBodyValidationException) {
			errorResponse.getErrors().addAll(((RequestBodyValidationException) causalExceptionInstance).getErrors());
			statusCode = Helpers.UNPROCESSABLE_ENTITY();
		} else {
			ErrorDetails errorDetails = new ErrorDetails();
			errorDetails.setErrorCode(String.valueOf(Helpers.SERVICE_UNAVAILABLE()));
			errorDetails.setErrorDescription("SERVICE UNAVAILABLE");
			errorResponse.getErrors().add(errorDetails);
			statusCode = Helpers.SERVICE_UNAVAILABLE();
		}
		return CompletableFuture.completedFuture(Results.status(statusCode, Json.toJson(errorResponse))
				.withHeader("content-type", "content-type=application/json"));
	}

}
