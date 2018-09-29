package com.dazn.frontend;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * WeatherCheckerPage.java 
 * Purpose: Page Object Model for Weather Checker.
 *
 * @author Iftikhar Ahmad
 * @version 1.0 29/09/2018
 *
 */

public class WeatherCheckerPage{
		
	//TODO - Web page should have better locators and naming conventions
	// A bug will be reported for improved testable production code.
	
	final WebDriver driver;
	
	//Page element locators
	public static final String LOCATOR_CLASSNAME_HEADER = "header";
	public static final String LOCATOR_NAME_ADDRESS = "address";
	public static final String LOCATOR_CLASSNAME_SEARCH = "submit_3";
	public static final String LOCATOR_ID_SEARCHLOCATION = "searchLocation";
	public static final String LOCATOR_CLASSNAME_TABLEHEADER = "tableHeader";
	public static final String LOCATOR_XPATH_TABLEBODY = "//*[@id='root']/div/div[3]/div/table/tbody";
	public static final String LOCATOR_XPATH_ERRORMESSAGE = "//h1[contains(text(), \"%s\")]";
	
	@FindBy(className=LOCATOR_CLASSNAME_HEADER)
	public WebElement elmHeader;
	
	@FindBy(className=LOCATOR_CLASSNAME_SEARCH)
	public WebElement elmSearch;
	
	@FindBy(className=LOCATOR_CLASSNAME_TABLEHEADER)
	public WebElement elmTableHeader;
	
	@FindBy(name=LOCATOR_NAME_ADDRESS)
	public WebElement elmAddress;
	
	@FindBy(id=LOCATOR_ID_SEARCHLOCATION)
	public WebElement elmSearchLocation;
	
	@FindBy(xpath=LOCATOR_XPATH_TABLEBODY)
	public WebElement elmTableBody;
	
	/** Description: Find given error message on the page
	 * @param driver A WebDriver object
	 * @param error  Error message to find
	 * @return WebElement containing the error message
	 */
	
	public static WebElement getElementErrorMessage(WebDriver driver, String error) {
		String xpath = String.format(LOCATOR_XPATH_ERRORMESSAGE, error);
		return driver.findElement(By.xpath(xpath));
	}
		
	public WeatherCheckerPage(WebDriver driver) {
		this.driver = driver;
	}
}
