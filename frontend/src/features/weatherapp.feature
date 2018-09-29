#Author: iftikhar.ahmad@gmail.com

Feature: Weather check for given postcode area
  Search the weather forecast for a given postcode
  and display weather forecast or appropriate error message
  for invalid or non-existing postcodes.

  Background: 
    Given I navigate to the url "https://serene-mountain-14043.herokuapp.com/"

  Scenario: Page layout
    Then page header is "Weather Checker"
    And have input box
    And have search button

  Scenario: Weather check with valid existing postcode
    Given I enter the postcode "W6 0WN"
    When I press the search button
    Then weather properties table is displayed
    And following weather properties need to be there:
      | Time        |
      | Temperature |
      | Humidity    |

  Scenario: Weather properties table should not have empty values
    Given I enter the postcode "W6 0WN"
    When I press the search button
    Then weather properties table is displayed
    And properties with no value should not appear on the table

  Scenario: Weather check with valid non existing postcode
    Given I enter the postcode "B99 9AA"
    When I press the search button
    Then error message is displayed "Postcode not found!"

  Scenario: Weather check with non-valid postcode
    Given I enter the postcode "EC1A 1BB"
    When I press the search button
    Then error message is displayed "Postcode not valid."

