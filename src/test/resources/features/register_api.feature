@api
Feature: User Registration API
 
  Scenario: Successful user registration
    Given the API endpoint is "/createAccount"
    When I send a POST request with valid registration data 
    Then the response status code should be 200
    And the response should contain success
  
