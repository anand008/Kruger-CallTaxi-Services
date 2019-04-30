/**
 * 
 */
package org.kruger.calltaxi.services.controllers;

import static org.junit.Assert.assertEquals;
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
 * org.kruger.calltaxi.services.controllers.TaxiManagementController
 * 
 * @author Srikar Reddy
 *
 */
public class TaxiManagementControllerTest extends WithApplication {

	public TaxiManagementControllerTest() {
		new GuiceApplicationBuilder().build();
	}

	/**
	 * helps test retrieval of pink mini taxis which are available.
	 */
	@Test
	public void testFetchTaxisAvailablePinkMini() {
		RequestBuilder requestBuilder = Helpers.fakeRequest(Helpers.GET, "/taxis?available=true&color=pink&type=mini");
		Result result = Helpers.route(app, requestBuilder);
		JsonNode resultAsJson = Json.toJson(Helpers.contentAsString(result));
		resultAsJson.iterator().forEachRemaining((JsonNode taxiDetails) -> {
			String taxiId = resultAsJson.findValue("taxi-details").findValue("taxi-id").asText();
			assertTrue(taxiId.startsWith("KA") && taxiId.split(Pattern.quote(".")).length == 4);
			assertEquals("pink", resultAsJson.findValue("taxi-details").findValue("taxi-color").asText());
			assertEquals("mini", resultAsJson.findValue("taxi-details").findValue("taxi-type").asText());
			assertNotNull(resultAsJson.findValue("taxi-details").findValue("car-model").asText());
			assertNotNull(resultAsJson.findValue("driver-details").findValue("driver-name"));
			assertNotNull(resultAsJson.findValue("driver-details").findValue("driver-contact-number"));
			assertNotNull(resultAsJson.findValue("driver-details").findValue("driver-rating"));
		});
	}

	/**
	 * helps test retrieval of mini taxis which are available.
	 */
	@Test
	public void testFetchTaxisAvailableMini() {
		RequestBuilder requestBuilder = Helpers.fakeRequest(Helpers.GET, "/taxis?available=true&type=mini");
		Result result = Helpers.route(app, requestBuilder);
		JsonNode resultAsJson = Json.toJson(Helpers.contentAsString(result));
		resultAsJson.iterator().forEachRemaining((JsonNode taxiDetails) -> {
			String taxiId = resultAsJson.findValue("taxi-details").findValue("taxi-id").asText();
			assertTrue(taxiId.startsWith("KA") && taxiId.split(Pattern.quote(".")).length == 4);
			assertNotNull(resultAsJson.findValue("taxi-details").findValue("taxi-color").asText());
			assertEquals("mini", resultAsJson.findValue("taxi-details").findValue("taxi-type").asText());
			assertNotNull(resultAsJson.findValue("taxi-details").findValue("car-model").asText());
			assertNotNull(resultAsJson.findValue("driver-details").findValue("driver-name"));
			assertNotNull(resultAsJson.findValue("driver-details").findValue("driver-contact-number"));
			assertNotNull(resultAsJson.findValue("driver-details").findValue("driver-rating"));
		});
	}

	/**
	 * helps test fetch all taxis which are available.
	 */
	@Test
	public void testFetchTaxisAvailable() {
		RequestBuilder requestBuilder = Helpers.fakeRequest(Helpers.GET, "/taxis?available=true");
		Result result = Helpers.route(app, requestBuilder);
		JsonNode resultAsJson = Json.toJson(Helpers.contentAsString(result));
		resultAsJson.iterator().forEachRemaining((JsonNode taxiDetails) -> {
			String taxiId = resultAsJson.findValue("taxi-details").findValue("taxi-id").asText();
			assertTrue(taxiId.startsWith("KA") && taxiId.split(Pattern.quote(".")).length == 4);
			assertNotNull(resultAsJson.findValue("taxi-details").findValue("taxi-color").asText());
			String taxiType = resultAsJson.findValue("taxi-details").findValue("taxi-type").asText();
			assertTrue(taxiType.equals("mini") || taxiType.equals("prime"));
			assertNotNull(resultAsJson.findValue("taxi-details").findValue("car-model").asText());
			assertNotNull(resultAsJson.findValue("driver-details").findValue("driver-name"));
			assertNotNull(resultAsJson.findValue("driver-details").findValue("driver-contact-number"));
			assertNotNull(resultAsJson.findValue("driver-details").findValue("driver-rating"));
		});
	}

	/**
	 * helps test fetch taxi by id.
	 */
	@Test
	public void testFetchTaxiById() {
		RequestBuilder requestBuilder = Helpers.fakeRequest(Helpers.GET, "/taxis/KA.51.MD.4173");
		Result result = Helpers.route(app, requestBuilder);
		JsonNode resultAsJson = Json.toJson(Helpers.contentAsString(result));
		resultAsJson.iterator().forEachRemaining((JsonNode taxiDetails) -> {
			String taxiId = resultAsJson.findValue("taxi-details").findValue("taxi-id").asText();
			assertTrue(taxiId.startsWith("KA") && taxiId.split(Pattern.quote(".")).length == 4);
			assertNotNull(resultAsJson.findValue("taxi-details").findValue("taxi-color").asText());
			String taxiType = resultAsJson.findValue("taxi-details").findValue("taxi-type").asText();
			assertTrue(taxiType.equals("mini") || taxiType.equals("prime"));
			assertNotNull(resultAsJson.findValue("taxi-details").findValue("car-model").asText());
			assertNotNull(resultAsJson.findValue("driver-details").findValue("driver-name"));
			assertNotNull(resultAsJson.findValue("driver-details").findValue("driver-contact-number"));
			assertNotNull(resultAsJson.findValue("driver-details").findValue("driver-rating"));
		});
	}
}
