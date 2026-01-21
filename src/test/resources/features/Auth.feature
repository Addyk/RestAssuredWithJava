@Auth

Feature: This is the Feature File for Auth API

Background:
    Given the API request is initialized

Scenario Outline: Happy Path for Auth API
     And I set headers:
    | Accept       | application/json |
    | Content-Type | application/json |
    And I authenticate using basic auth with username "<username>" and password "<password>"
    When user sends a "<request>" request to "<endpoint>" endpoint
    Then the response status code should be <statusCode>
    And response should match json schema "<filePath>"
Examples:
| endpoint                  | statusCode |request | username | password |filePath|
| /basic-auth/user/passwd   | 200        |GET     |user |passwd|schemas/authResponse.json|