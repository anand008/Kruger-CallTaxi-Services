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
 * helps fetch driver's ride history.
 * 
 * @author Srikar Reddy
 *
 */
public class DriverRideHistoryController extends Controller {
	/**
	 * fetches all the rides made by the driver so far.
	 * 
	 * @param customerId
	 * @return CompletionStage<Result>
	 */
	public CompletionStage<Result> fetchRides(String driverId) {
		return CompletableFuture.supplyAsync(Results::ok);
	}

	/**
	 * fetches all the rides made by the driver so far.
	 * 
	 * @param customerId
	 * @return CompletionStage<Result>
	 */
	public CompletionStage<Result> fetchRideById(String driverId, String rideId) {
		return CompletableFuture.supplyAsync(Results::ok);
	}
}
