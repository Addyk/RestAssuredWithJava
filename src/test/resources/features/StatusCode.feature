Feature: This is the Feature File for Status Code API

@HappyPath
Scenario Outline: Happy Path for all the endpoints of Status Code API
    Given the API request is initialized
    When user sends a "<request>" request to "<endpoint>" endpoint
    Then the response status code should be <statusCode>
Examples:
| endpoint  | statusCode |request|
| /get      | 200      |GET|
| /delete   | 200      |DELETE|
| /patch    | 200      |PATCH|
| /post     | 200      |POST|
| /put      | 200      |PUT|


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
