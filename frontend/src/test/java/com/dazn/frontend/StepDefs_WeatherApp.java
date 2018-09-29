package com.dazn.frontend;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.dazn.frontend.WeatherCheckerPage;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * WeatherCheckerPage.java Purpose: Step definitions class for weather checker
 * feature.
 *
 * @author Iftikhar Ahmad
 * @version 1.0 29/09/2018
 *
 */

public class StepDefs_WeatherApp {

	static WebDriver driver;
	private WeatherCheckerPage weatherCheckerPage;
	public static final int DEFAULT_TIMEOUT = 10; //seconds
	
	@Before
	public void setUp() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
		weatherCheckerPage = PageFactory.initElements(driver, WeatherCheckerPage.class);
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Given("^I navigate to the url \"([^\"]*)\"$")
	public void iNavigateTo(String url) {
		driver.get(url);
	}

	@Then("page header is \"([^\"]*)\"$")
	public void pageHeaderTextIs(String headerText) {
		WebElement header = weatherCheckerPage.elmHeader;
		assertNotNull(header);
		assertEquals(headerText, header.getText());
	}

	@Then("have input box")
	public void haveInputBox() {
		WebElement address = weatherCheckerPage.elmAddress;
		assertNotNull(address);
	}

	@Then("have search button")
	public void haveSearchButton() {
		WebElement search = weatherCheckerPage.elmSearch;
		assertNotNull(search);
		assertEquals("Search", search.getText());
	}

	@Given("I enter the postcode \"([^\"]*)\"$")
	public void iEnterThePostcode(String postcode) {
		weatherCheckerPage.elmAddress.sendKeys(postcode);
	}

	@When("I press the search button")
	public void iPressSearchButton() {

		WebElement form = weatherCheckerPage.elmSearchLocation;
		assertNotNull(form);
		form.submit();
	}

	@Then("^weather properties table is displayed$")
	public void checkWeatherPropertiesTableisDisplayed() {
		assertNotNull(weatherCheckerPage.elmTableHeader);
	}

	@Then("properties with no value should not appear on the table")
	public void checkPropsWithoutValue() {
		WebElement tBody = weatherCheckerPage.elmTableBody;
		assertNotNull(tBody);

		List<WebElement> datarows = tBody.findElements(By.tagName("td"));

		Iterator<WebElement> it = datarows.iterator();

		while (it.hasNext()) {
			String text = it.next().getText();
			assertFalse(text.isEmpty());
		}
	}

	@Then("following weather properties need to be there:")
	public void checkMiniumSetOfPropsExist(List<String> props) {

		WebElement tBody = weatherCheckerPage.elmTableBody;
		assertNotNull(tBody);

		String tblText = tBody.getText();
		Iterator<String> it = props.iterator();
		while (it.hasNext()) {
			String prop = it.next();
			assertTrue(tblText.contains(prop));
		}
	}

	@Then("error message is displayed \"([^\"]*)\"$")
	public void errorMessageIsDisplayed(String error) {
		WebElement errorMessage = WeatherCheckerPage.getElementErrorMessage(driver, error);
		assertNotNull(errorMessage);
	}
}
