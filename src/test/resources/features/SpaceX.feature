Feature: SpaceX API Testing
  As a user of the SpaceX API
  I want to verify its functionality and data accuracy

  Scenario: Retrieve rocket details
    Given I have a valid access to the SpaceX API
    When I send a GET request to "/rockets/falcon1"
    Then the response status code should be 200
    And the response body should contain the following information:
      | Field        | Expected Value       |
      | rocket_id    | Falcon1              |
      | rocket_name  | Falcon 1             |
      | description  | The Falcon 1 was ... |
      | first_flight | 2006-03-24           |
      | country      | Republic of the ...  |


  Scenario: Search for launch information
    Given I have a valid access to the SpaceX API
    When I send a GET request to "/launches" with query parameter "mission_name" as "FalconSat"
    Then the response status code should be 200
    And the response body should contain at least one launch entry
    And each launch entry should have the following information:
      | Field          | Expected Value        |
      | flight_number  | 1                     |
      | mission_name   | Starlink-1            |
      | launch_year    | 2019                  |
      | launch_success | true                  |
      | rocket         | Object                |
      | details        | SpaceX's Starlink ... |
   @wip
  Scenario Outline: Get upcoming launches
    Given I have a valid access to the SpaceX API
    When I send a GET request to "<End Points>"
    Then the response status code should be <Status Code>

    Examples:
      | End Points         | Status Code |
      | /launches/upcoming | 200         |
      | /launches/past     | 200         |
      | /launches/latest   | 200         |
      | /launches/next     | 200         |