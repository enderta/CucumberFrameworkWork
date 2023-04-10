Feature: User Registration and Login

  Background:
    Given I am on the home page

  Scenario: Register a new user
    Given I am on the registration page
    When I fill in the registration form with valid information
    And I submit the registration form
    Then I should be redirected to the login page
    And I should see a success message

  Scenario: Login as a registered user

    When I fill in the login form with valid credentials
    And I submit the login form
    Then I should be redirected to the user dashboard
    And I should see a welcome message


  Scenario: Search for a hotel by location and dates
    Given I am on the hotel search page
    When I enter a valid location and dates
    And I click the search button
    Then I should see a list of available hotels in the specified location and dates

  @travel
  Scenario Outline: Login with valid credentials
    Given I am on the Login page
    When I enter "<email>" and "<password>" in the "<row>" row
    And I click the Login button
    Then I should be redirected to the Dashboard page
    Examples:
      | email | password | row |
      | 0     | 1        | 1   |
      | 0     | 1        | 2   |
      | 0     | 1        | 3   |


  Scenario: Search for hotels by city and dates
    Given I am on the hotel search page
    When I enter "Islamabad" as the destination
    And I enter "May 1" as the check-in date
    And I enter "May 5" as the check-out date
    And I click on the search button
    Then I should see a list of available hotels in New York