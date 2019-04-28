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
 * helps manage taxis.
 * 
 * @author Srikar Reddy
 *
 */
public class TaxiManagementController extends Controller {
	/**
	 * fetches taxis based on the filters provided.
	 * 
	 * @param available
	 * @param taxiColor
	 * @param taxiType
	 * @return CompletionStage<Result>
	 */
	public CompletionStage<Result> fetchTaxis(Boolean available, String taxiColor, String taxiType) {
		return CompletableFuture.supplyAsync(Results::ok);
	}

	/**
	 * fetches taxi using its id.
	 * 
	 * @param taxiId
	 * @return CompletionStage<Result>
	 */
	public CompletionStage<Result> fetchTaxiById(String taxiId) {
		return CompletableFuture.supplyAsync(Results::ok);
	}
}
