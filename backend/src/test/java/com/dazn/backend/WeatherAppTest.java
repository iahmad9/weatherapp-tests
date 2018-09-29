package com.dazn.backend;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;




public class WeatherAppTest {

	public static final String BASE_URL = "https://fierce-gorge-81903.herokuapp.com";
	public static final String RESTSERVIC_WEATHER_API_ENDPOINT = "/api";
	public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
	public static final String POST_REQUEST_BODY = "{\"address\":\"%s\"}";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String JSON_ELEMENT_ERROR_MESSAGE = "errorMessage";
	
	public String[] MINIMUM_FORECAST_PROPS = {"time","temperature", "humidity"};
	
	//Test Data with sample post codes and expected responses.
	@DataProvider
	public Object[][] dpResponseCodes() {
		return new Object[][] { 
			new Object[] { "W6 0WN", 200 }, 
			new Object[] { "B99 9AA", 433 },
			new Object[] { "EC1A 1BB", 435 }, 
			new Object[] { "@#$%", 435 },
			new Object[] { " ", 435 },};
	}
	
	//Test Data with sample post codes and expected error messages.
	@DataProvider
	public Object[][] dpErrorMessages() {
		return new Object[][] { 
			new Object[] { "B99 9AA", "Postcode not found!" },
			new Object[] { "EC1A 1BB", "Postcode not valid." },
			new Object[] { "@#$%", "Postcode not valid." },
			new Object[] { " ", "Postcode not valid." },};
	}
	
	@DataProvider
	public Object[] dpValidExistingPostcodes() {
		return new Object[] { 
			new String("W6 0WN") };
		}
	
	@BeforeClass
	public void beforeMethod() {
		RestAssured.baseURI = BASE_URL;
	}

	public Response prepareAndPostRequest(String postcode) {
	
		RequestSpecification request = RestAssured.given();

		// Set http header Content-Type
		request.header(CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);

		// Request payload in json format
		request.body(String.format(POST_REQUEST_BODY, postcode));
		
		Response response = request.post(RESTSERVIC_WEATHER_API_ENDPOINT);
		
		return response;
	}
	
	@Test(dataProvider = "dpResponseCodes")
	public void verifyWeatherApiResponseCodes(String postcode, Integer expectedStatusCode) {

		Response response = prepareAndPostRequest(postcode);
		assertEquals(response.getStatusCode(), expectedStatusCode.intValue());
	}
	
	@Test(dataProvider = "dpErrorMessages")
	public void verifyErrorMessages(String postcode, String expectedResponseData) {
		
		Response response = prepareAndPostRequest(postcode);
		JsonPath jsonPath = response.jsonPath();
		String errorMessage = jsonPath.get(JSON_ELEMENT_ERROR_MESSAGE);
		assertEquals(errorMessage, expectedResponseData);
	}
	
	@Test(dataProvider = "dpValidExistingPostcodes")
	public void verifyWeatherForcastResult(String postcode) {
		Response response = prepareAndPostRequest(postcode);
		String weatherDetails = response.getBody().asString();
		
		for (int i=0; i<MINIMUM_FORECAST_PROPS.length; i++) {
			assertTrue(weatherDetails.contains(MINIMUM_FORECAST_PROPS[i]));
		}
	}
}
