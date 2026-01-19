Feature: This is the Feature File for Status Code API

@HappyPath
Scenario Outline: Happy Path for all the endpoints of Status Code API

When I send "<request>" request to "<endpoint>"
Then status code should be <statusCode>
Examples:
| endpoint  | statusCode |request|
| /get      | 200      |GET|
| /delete   | 200      |DELETE|
| /patch    | 200      |PATCH|
| /post     | 200      |POST|
| /put      | 200      |PUT|