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
 * helps manage taxi ride booking related operations.
 * 
 * @author Srikar Reddy
 *
 */
public class BookingController extends Controller {
	/**
	 * helps find a nearby taxi to the customer.
	 * 
	 * @param customerId
	 * @param taxiColor
	 * @param taxiType
	 * @return CompletionStage<Result>
	 */
	public CompletionStage<Result> bookTaxi(String customerId, String taxiColor, String taxiType) {
		return CompletableFuture.supplyAsync(Results::ok);
	}

	/**
	 * helps confirm a customer's ride, services can also mark a taxi unavailable.
	 * 
	 * @param customerId
	 * @param rideId
	 * @return CompletionStage<Result>
	 */
	public CompletionStage<Result> confirmBooking(String customerId, String rideId) {
		return CompletableFuture.supplyAsync(Results::ok);
	}

	/**
	 * helps mark a ride as completed once the customer has been dropped at the
	 * destination.
	 * 
	 * @param driverId
	 * @param rideId
	 * @return CompletionStage<Result>
	 */
	public CompletionStage<Result> completeRide(String driverId, String rideId) {
		return CompletableFuture.supplyAsync(Results::ok);
	}

	/**
	 * helps update bill payment status, marks the taxi available and updates the
	 * taxi's coordinates.
	 * 
	 * @param driverId
	 * @param rideId
	 * @return CompletionStage<Result>
	 */
	public CompletionStage<Result> updateRidePaymentDetails(String driverId, String rideId) {
		return CompletableFuture.supplyAsync(Results::noContent);
	}
}
