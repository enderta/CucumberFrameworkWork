Feature: SpaceX API Testing
  As a user of the SpaceX API
  I want to verify its functionality and data accuracy
  @wip
  Scenario: Retrieve rocket details
    Given I have a valid access to the SpaceX API
    When I send a GET request to "/rockets/falcon1"
    Then the response status code should be 200
    And the response body should contain the following information:
      | Field         | Expected Value        |
      | rocket_id     | Falcon1               |
      | rocket_name   | Falcon 1              |
      | description   | The Falcon 1 was ...  |
      | first_flight  | 2006-03-24            |
      | country       | Republic of the ...   |

  Scenario: Search for launch information
    Given I have a valid access to the SpaceX API
    When I send a GET request to "/launches" with query parameter "mission_name" as "Starlink"
    Then the response status code should be 200
    And the response body should contain at least one launch entry
    And each launch entry should have the following information:
      | Field              | Expected Type |
      | flight_number      | Integer       |
      | mission_name       | Starlink-1    |
      | launch_year        | 2019          |
      | launch_success     | true          |
      | rocket             | Object        |
      | details            | SpaceX's Starlink ... |

  Scenario: Get upcoming launches
    Given I have a valid access to the SpaceX API
    When I send a GET request to "/launches/upcoming"
    Then the response status code should be 200
    And the response body should contain a list of upcoming launches
    And each launch should have the following information:
      | Field              | Expected Type |
      | flight_number      | Integer       |
      | mission_name       | Starship to Mars |
      | launch_date_utc    | 2023-08-15T08:00:00.000Z |
      | rocket             | Object        |
      | launch_site        | Object        |