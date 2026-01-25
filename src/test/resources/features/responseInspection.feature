@responseInspection
Feature: Response Inspection - caching and response headers

  # -------------------------------------------------
  # /cache
  # -------------------------------------------------

  @cache @happyPath
  Scenario: GET /cache without cache headers should return 200
    Given the API request is initialized
    When user sends a "GET" request to "/cache" endpoint
    Then the response status code should be 200

  @cache @happyPath
  Scenario: GET /cache with If-Modified-Since should return 304
    Given the API request is initialized
    And I set headers:
      | If-Modified-Since | Wed, 21 Oct 2015 07:28:00 GMT |
    When user sends a "GET" request to "/cache" endpoint
    Then the response status code should be 304

  # -------------------------------------------------
  # /cache/{value}
  # -------------------------------------------------

  @cacheValue @happyPath
  Scenario Outline: GET /cache/{value} should set Cache-Control max-age
    Given the API request is initialized
    When user sends a "GET" request to "/cache/<value>" endpoint
    Then the response status code should be 200
    And response headers should be:
      | cache-control | public, max-age=<value> |
    Examples:
      | value |
      | 5     |
      | 60    |

  # -------------------------------------------------
  # /etag/{etag}
  # -------------------------------------------------

  @etag @happyPath
  Scenario: GET /etag/{etag} should return 200 and ETag header
    Given the API request is initialized
    When user sends a "GET" request to "/etag/test123" endpoint
    Then the response status code should be 200
    And response headers should be:
      | etag | test123 |

  @etag @happyPath
  Scenario: GET /etag/{etag} with matching If-None-Match should return 304
    Given the API request is initialized
    And I set headers:
      | If-None-Match | "test123" |
    When user sends a "GET" request to "/etag/test123" endpoint
    Then the response status code should be 304

  # -------------------------------------------------
  # /response-headers (GET)
  # -------------------------------------------------

  @responseHeaders @happyPath
  Scenario: GET /response-headers should return headers from query params
    Given the API request is initialized
    When user sends a "GET" request to "/response-headers?X-Env=QA&X-App=Automation" endpoint
    Then the response status code should be 200
    And response headers should be:
      | x-env | QA         |
      | x-app | Automation |

  @responseHeaders @happyPath
  Scenario Outline: GET /response-headers should support multiple key-value pairs
    Given the API request is initialized
    When user sends a "GET" request to "/response-headers?<params>" endpoint
    Then the response status code should be 200
    And response headers should be:
      | <header1> | <value1> |
      | <header2> | <value2> |
    Examples:
      | params                          | header1 | value1 | header2 | value2 |
      | TraceId=abc123&Build=2026.01.25 | traceid | abc123 | build   | 2026.01.25 |

  # -------------------------------------------------
  # /response-headers (POST)
  # -------------------------------------------------

  @responseHeaders @happyPath
  Scenario: POST /response-headers should return headers from query params
    Given the API request is initialized
    When user sends a "POST" request to "/response-headers?mode=test&team=qa" endpoint
    Then the response status code should be 200
    And response headers should be:
      | mode | test |
      | team | qa   |
