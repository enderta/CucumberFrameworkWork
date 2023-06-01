Feature: Lab Automation Practice Website

Background:
  Given I am on the registration page of lab-automation-practice website
    Then I login the page
  Scenario Outline: User Registration
    When I enter the following details to fill the registration form:
      | User Name   | Password   |
      | <firstName> | <password> |
    And click on the register button on the registration page
    Then I should be taken to the registration confirmation page

    Examples:
      | firstName     | lastName | email_with_timestamp               | password     |
      | standard_user | Doe      | john.doe+<timestamp>@example.com   | secret_sauce |
      | Jane          | Smith    | jane.smith+<timestamp>@example.com | password2    |


  Scenario Outline: Search Product
    Given I am on the product listing page
    When I search for "<searchTerm>"
    Then I should see search results containing "<searchTerm>"

    Examples:
      | searchTerm |
      | T-Shirt    |
      | Shoes      |