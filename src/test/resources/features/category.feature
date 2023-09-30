Feature: Cognos Categories API

  Scenario: Get all categories
    Given the application is running
    When I make a GET request to "/cognos-categories-api/categories"
    Then the response status code should be 200
    And the response should contain JSON
