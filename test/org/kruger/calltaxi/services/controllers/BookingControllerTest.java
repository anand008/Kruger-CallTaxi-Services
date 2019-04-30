/**
 * 
 */
package org.kruger.calltaxi.services.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

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
 * org.kruger.calltaxi.services.controllers.BookingController
 * 
 * @author Srikar Reddy
 *
 */
public class BookingControllerTest extends WithApplication {

	public BookingControllerTest() {
		new GuiceApplicationBuilder().build();
	}

	/**
	 * tests the cab booking process when a customer requests for pink color micro
	 * taxi.
	 */
	@Test
	public void testBookTaxiColorPinkTypeMicro() {
		RequestBuilder requestBuilder = Helpers.fakeRequest(Helpers.POST,
				"/customers/8494848586/rides?color=pink&type=micro");
		Result result = Helpers.route(app, requestBuilder);
		JsonNode resultAsJson = Json.toJson(Helpers.contentAsString(result));
		String taxiId = resultAsJson.findValue("taxi-details").findValue("taxi-id").asText();
		assertTrue(taxiId.startsWith("KA") && taxiId.split(Pattern.quote(".")).length == 4);
		String taxiFare = resultAsJson.findValue("fare-details").findValue("expected-fare").asText();
		assertNotNull(taxiFare);
		resultAsJson.findValue("href").asText().contains(requestBuilder.uri());
	}

	/**
	 * tests the cab booking process when a customer requests for pink color prime
	 * taxi.
	 */
	@Test
	public void testBookTaxiColorPinkTypePrime() {
		RequestBuilder requestBuilder = Helpers.fakeRequest(Helpers.POST,
				"/customers/8494848586/rides?color=pink&type=prime");
		Result result = Helpers.route(app, requestBuilder);
		JsonNode resultAsJson = Json.toJson(Helpers.contentAsString(result));
		String taxiId = resultAsJson.findValue("taxi-details").findValue("taxi-id").asText();
		assertTrue(taxiId.startsWith("KA") && taxiId.split(Pattern.quote(".")).length == 4);
		String taxiFare = resultAsJson.findValue("fare-details").findValue("expected-fare").asText();
		assertNotNull(taxiFare);
		resultAsJson.findValue("href").asText().contains(requestBuilder.uri());
	}

	/**
	 * tests the cab booking process when a customer requests for pink color micro
	 * taxi.
	 */
	@Test
	public void testBookTaxiColorNonPinkTypeMicro() {
		RequestBuilder requestBuilder = Helpers.fakeRequest(Helpers.POST, "/customers/8494848586/rides?type=micro");
		Result result = Helpers.route(app, requestBuilder);
		JsonNode resultAsJson = Json.toJson(Helpers.contentAsString(result));
		String taxiId = resultAsJson.findValue("taxi-details").findValue("taxi-id").asText();
		assertTrue(taxiId.startsWith("KA") && taxiId.split(Pattern.quote(".")).length == 4);
		String taxiFare = resultAsJson.findValue("fare-details").findValue("expected-fare").asText();
		assertNotNull(taxiFare);
		resultAsJson.findValue("href").asText().contains(requestBuilder.uri());
	}

	/**
	 * tests the cab booking process when a customer requests for pink color prime
	 * taxi.
	 */
	@Test
	public void testBookTaxiColorNonPinkTypePrime() {
		RequestBuilder requestBuilder = Helpers.fakeRequest(Helpers.POST, "/customers/8494848586/rides?type=prime");
		Result result = Helpers.route(app, requestBuilder);
		JsonNode resultAsJson = Json.toJson(Helpers.contentAsString(result));
		String taxiId = resultAsJson.findValue("taxi-details").findValue("taxi-id").asText();
		assertTrue(taxiId.startsWith("KA") && taxiId.split(Pattern.quote(".")).length == 4);
		String taxiFare = resultAsJson.findValue("fare-details").findValue("expected-fare").asText();
		assertNotNull(taxiFare);
		resultAsJson.findValue("href").asText().contains(requestBuilder.uri());
	}

	/**
	 * tests the ride confirmation process
	 */
	@Test
	public void testConfirmBooking() {
		RequestBuilder requestBuilder = Helpers.fakeRequest(Helpers.POST,
				"/customers/8494848586/rides/54ca60a5-d34a-4cf9-8cf5-7161e17d789b");
		Result result = Helpers.route(app, requestBuilder);
		JsonNode resultAsJson = Json.toJson(Helpers.contentAsString(result));
		String taxiId = resultAsJson.findValue("taxi-details").findValue("taxi-id").asText();
		assertTrue(taxiId.startsWith("KA") && taxiId.split(Pattern.quote(".")).length == 4);
		assertNotNull(resultAsJson.findValue("driver-details").findValue("driver-name").asText());
		assertNotNull(resultAsJson.findValue("driver-details").findValue("driver-contact-number").asText());
		assertNotNull(resultAsJson.findValue("driver-details").findValue("driver-rating").asText());
		String otp = resultAsJson.findValue("ride-details").findValue("otp").asText();
		assertNotNull(Integer.parseInt(otp));
		assertTrue(otp.length() == 6);
	}

	/**
	 * tests the ride completion process
	 */
	@Test
	public void testCompleteRide() {
		RequestBuilder requestBuilder = Helpers.fakeRequest("PATCH",
				"/drivers/8494848586/rides/54ca60a5-d34a-4cf9-8cf5-7161e17d789b");
		Result result = Helpers.route(app, requestBuilder);
		JsonNode resultAsJson = Json.toJson(Helpers.contentAsString(result));
		assertNotNull(resultAsJson.findValue("fare").asText());
		assertTrue(resultAsJson.findValue("href").asText().contains(requestBuilder.uri()));
	}

	/**
	 * tests the final ride bill status.
	 */
	@Test
	public void testUpdateRidePaymentDetails() {
		RequestBuilder requestBuilder = Helpers.fakeRequest(Helpers.PUT,
				"/drivers/8494848586/rides/54ca60a5-d34a-4cf9-8cf5-7161e17d789b");
		Result result = Helpers.route(app, requestBuilder);
		assertTrue(result.status() == 204);
	}

}
