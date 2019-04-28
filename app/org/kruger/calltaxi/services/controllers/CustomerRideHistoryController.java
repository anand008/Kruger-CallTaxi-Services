/**
 * 
 */
package org.kruger.calltaxi.services.controllers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

/**
 * helps fetch customer's ride history.
 * 
 * @author Srikar Reddy
 *
 */
public class CustomerRideHistoryController extends Controller {

	/**
	 * fetches all the rides made by the customer so far.
	 * 
	 * @param customerId
	 * @return CompletionStage<Result>
	 */
	public CompletionStage<Result> fetchRides(String customerId) {
		return CompletableFuture.supplyAsync(Results::ok);
	}

	/**
	 * fetches all the rides made by the customer so far.
	 * 
	 * @param customerId
	 * @return CompletionStage<Result>
	 */
	public CompletionStage<Result> fetchRideById(String customerId, String rideId) {
		return CompletableFuture.supplyAsync(Results::ok);
	}

}
