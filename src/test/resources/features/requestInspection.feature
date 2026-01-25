@requestInspection
Feature: Request Inspection Feature

    Background:
    Given the API request is initialized

@headers @happyPath
  Scenario: Custom headers should be returned in /headers response
    And I set headers:
      | X-Env    | QA         |
      | X-Client | Automation |
    When user sends a "GET" request to "/headers" endpoint
    Then the response status code should be 200
    And response json fields should be:
    | headers.X-Env    | QA         |
    | headers.X-Client | Automation |


@headers @happyPath
  Scenario: Standard Accept header should be returned in /headers response
    And I set headers:
      | X-Accept-Test | application/json |
    When user sends a "GET" request to "/headers" endpoint
    Then the response status code should be 200
    And response json fields should be:
      | headers.X-Accept-Test | application/json |

@userAgent @happyPath
Scenario: Custom User-Agent should be returned by /user-agent endpoint
  And I set headers:
    | User-Agent | MyAutomation/1.0 |
  When user sends a "GET" request to "/user-agent" endpoint
  Then the response status code should be 200
  And response json fields should be:
    | user-agent | MyAutomation/1.0 |


 @userAgent @happyPath
  Scenario: Default User-Agent should be present when not explicitly set
    When user sends a "GET" request to "/user-agent" endpoint
    Then the response status code should be 200
    And response json field "user-agent" should not be empty


    @ip @happyPath
  Scenario: Client IP should be returned by /ip endpoint
    When user sends a "GET" request to "/ip" endpoint
    Then the response status code should be 200
    And response json field "origin" should not be empty

  @ip @happyPath
  Scenario: Client IP should have a valid IP format
    When user sends a "GET" request to "/ip" endpoint
    Then the response status code should be 200
    And response json field "origin" should match regex ".*\d+\.\d+\.\d+\.\d+.*"

 