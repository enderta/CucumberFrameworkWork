Feature: Petstore API
  As a user of the Petstore API
  I want to perform various operations related to pets
  So that I can manage pet data effectively

  Background:
    Given the Petstore API is up and running


  Scenario Outline: Create a new pet
    Given I have the following pet data:
      | name   | category   | status   |
      | <name> | <category> | <status> |
    When I send a POST request to "/pet" with the provided pet data
    Then the response status code should be <statusCode>
    And the response should contain the created pet details

    Examples:
      | name     | category | status      | statusCode |
      | Max | Dog    | available | 200        |
      | Lucy   | Cat    | sold      | 200        |
      | Buddy  | Dog    | pending   | 200        |
      | Luna   | Cat    | available | 200        |

  @wip
  Scenario: Update an existing pet
    Given an existing pet with ID 123
    And I have the following updated pet data:
      | name  | status |
      | "Max" | "sold" |
    When I send a PUT request to "/pet/123" with the updated pet data
    Then the response status code should be 200
    And the response should contain the updated pet details

  Scenario: Delete an existing pet
    Given an existing pet with ID 123
    When I send a DELETE request to "/pet/123"
    Then the response status code should be 200
    And the response body should be empty
