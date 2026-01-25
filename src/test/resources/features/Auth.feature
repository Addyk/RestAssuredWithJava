@Auth

Feature: This is the Feature File for Auth API

Background:
    Given the API request is initialized

@AuthHappy
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
| /basic-auth/user/passwd   | 200        |GET     |auth.username |auth.password|schemas/Auth/authResponse.json|
| /hidden-basic-auth/user/passwd   | 200        |GET     |auth.username |auth.password|schemas/Auth/authResponse.json|

@AuthHappy
Scenario Outline: Happy Path for Auth API
     And I set headers:
    | Accept       | application/json |
    | Content-Type | application/json |
    And I authenticate using digest auth with username "<username>" and password "<password>"
    When user sends a "<request>" request to "<endpoint>" endpoint
    Then the response status code should be <statusCode>
    And response should match json schema "<filePath>"
Examples:
| endpoint                  | statusCode |request | username | password |filePath|
| /digest-auth/auth/user/passwd   | 200        |GET     |auth.username |auth.password|schemas/Auth/authResponse.json|
| /digest-auth/auth/user/passwd/MD5   | 200        |GET     |auth.username |auth.password|schemas/Auth/authResponse.json|
| /digest-auth/auth/user/passwd/MD5/30   | 200        |GET     |auth.username |auth.password|schemas/Auth/authResponse.json|


 @Bearer
  Scenario: Bearer token should be accepted
    And I set bearer token "my-token"
    When user sends a "GET" request to "/bearer" endpoint
    Then the response status code should be 200
    And response should match json schema "schemas/Auth/authResponse.json"

@AuthNegative
Scenario Outline: Negative Path for Auth API
     And I set headers:
    | Accept       | application/json |
    | Content-Type | application/json |
    And I authenticate using basic auth with username "<username>" and password "<password>"
    When user sends a "<request>" request to "<endpoint>" endpoint
    Then the response status code should be <statusCode>
Examples:
| endpoint                  | statusCode |request | username | password |
| /basic-auth/user/passwd   | 401        |GET     |user1     |passwd    |