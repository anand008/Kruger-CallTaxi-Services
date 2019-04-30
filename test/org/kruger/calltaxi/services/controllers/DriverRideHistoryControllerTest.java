/**
 * 
 */
package org.kruger.calltaxi.services.controllers;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

import play.inject.guice.GuiceApplicationBuilder;
import play.libs.Json;
import play.mvc.Http.RequestBuilder;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

/**
 * test case class which is a consolidated repository of test cases to
 * thoroughly unit test the functionality served by
 * org.kruger.calltaxi.services.controllers.DriverRideHistoryControllerTest
 * 
 * @author Srikar Reddy
 *
 */
public class DriverRideHistoryControllerTest extends WithApplication {
	public DriverRideHistoryControllerTest() {
		new GuiceApplicationBuilder().build();
	}

	/**
	 * helps test the retrieval of all the rides made by this driver.
	 */
	@Test
	public void testFetchRides() {
		RequestBuilder requestBuilder = Helpers.fakeRequest(Helpers.GET, "/drivers/9663805588/rides");
		Result result = Helpers.route(app, requestBuilder);
		JsonNode resultAsJson = Json.toJson(Helpers.contentAsString(result));
		assertNotNull(resultAsJson.findValue("driver-id").asText());
		resultAsJson.findValue("rides").iterator().forEachRemaining((JsonNode rideDetails) -> {
			assertNotNull(rideDetails.findValue("id").asText());
			assertNotNull(rideDetails.findValue("source-coordinates").findValue("latitude").asText());
			assertNotNull(rideDetails.findValue("source-coordinates").findValue("longitude").asText());
			assertNotNull(rideDetails.findValue("destination-coordinates").findValue("latitude").asText());
			assertNotNull(rideDetails.findValue("destination-coordinates").findValue("longitude").asText());
			assertNotNull(rideDetails.findValue("distance-travelled").asText());
			assertNotNull(rideDetails.findValue("fare-amount").asText());
			assertNotNull(rideDetails.findValue("duration").asText());
		});
	}

	/**
	 * helps test the retrieval of a ride made by this driver.
	 */
	@Test
	public void testFetchRidesById() {
		RequestBuilder requestBuilder = Helpers.fakeRequest(Helpers.GET,
				"/drivers/9663805588/rides/54ca60a5-d34a-4cf9-8cf5-7161e17d789b");
		Result result = Helpers.route(app, requestBuilder);
		JsonNode resultAsJson = Json.toJson(Helpers.contentAsString(result));
		assertNotNull(resultAsJson.findValue("driver-id").asText());
		resultAsJson.findValue("rides").iterator().forEachRemaining((JsonNode rideDetails) -> {
			assertNotNull(rideDetails.findValue("id").asText());
			assertNotNull(rideDetails.findValue("source-coordinates").findValue("latitude").asText());
			assertNotNull(rideDetails.findValue("source-coordinates").findValue("longitude").asText());
			assertNotNull(rideDetails.findValue("destination-coordinates").findValue("latitude").asText());
			assertNotNull(rideDetails.findValue("destination-coordinates").findValue("longitude").asText());
			assertNotNull(rideDetails.findValue("distance-travelled").asText());
			assertNotNull(rideDetails.findValue("fare-amount").asText());
			assertNotNull(rideDetails.findValue("duration").asText());
		});
	}
}
