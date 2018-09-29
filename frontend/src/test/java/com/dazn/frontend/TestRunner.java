package com.dazn.frontend;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * TestRunner.java 
 * Purpose: Test Runner class to aid the junit test discovery and execution.
 *
 * @author Iftikhar Ahmad
 * @version 1.0 29/09/2018
 *
 */

@RunWith(Cucumber.class)
@CucumberOptions(features="src/features", plugin= {"pretty", "html:target/html", 
															 "json:target/json"})
public class TestRunner {

}
