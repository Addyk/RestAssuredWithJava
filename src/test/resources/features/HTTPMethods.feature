Feature: This is the Feature File for HTTP Methods API

@HappyPath
Scenario Outline: Happy Path for all the endpoints of Status Code API
    Given the API request is initialized
    When user sends a "<request>" request to "<endpoint>" endpoint
    Then the response status code should be <statusCode>
    And response should match json schema "<filePath>"
    And response headers should be:
  | access-control-allow-credentials | true               |
  | access-control-allow-origin      | *                  |
  | content-type                     | application/json   |
  | server                           | gunicorn           |
Examples:
| endpoint  | statusCode |request|filePath|
| /get      | 200      |GET|schemas/StatusCode/given.json|
| /delete   | 200      |DELETE|schemas/StatusCode/given.json|
| /patch    | 200      |PATCH|schemas/StatusCode/given.json|
| /post     | 200      |POST|schemas/StatusCode/given.json|
| /put      | 200      |PUT|schemas/StatusCode/given.json|

@NegativeScenario
Scenario Outline: User uses wrong request for the endpoint
    Given the API request is initialized
    When user sends a "<request>" request to "<endpoint>" endpoint
    Then the response status code should be <statusCode>
Examples:
| endpoint  | statusCode |request|
| /get      | 405      |POST|
| /delete   | 405      |PUT|
| /patch    | 405      |GET|
| /post     | 405      |DELETE|
| /put      | 405      |PATCH|
