Feature: This is the Feature File for Status Code API

@HappyPath @statusCode
Scenario Outline: Happy Path for all the endpoints of Status Code API
    Given the API request is initialized
    And user set path parameter "code" as "<statusCode>"
    When user sends a "<request>" request to "/status/{code}" endpoint
    Then the response status code should be <statusCode>
Examples:
|statusCode |request|
| 200        |GET|
| 201        |GET|
| 204        |GET|
| 400        |GET|
| 404        |GET|
| 200        |DELETE|
| 201        |DELETE|
| 204        |DELETE|
| 400        |DELETE|
| 404        |DELETE|
| 200        |PATCH|
| 201        |PATCH|
| 204        |PATCH|
| 400        |PATCH|
| 404        |PATCH|
| 200        |POST|
| 201        |POST|
| 204        |POST|
| 400        |POST|
| 404        |POST|
| 200        |PUT|
| 201        |PUT|
| 204        |PUT|
| 400        |PUT|
| 404        |PUT|