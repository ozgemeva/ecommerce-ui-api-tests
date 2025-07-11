@register
Feature: Basic Navigation

  Scenario: Only homepage check
    Given the user is on the homepage
    When the user clicks the "Signup / Login" button
    And the user enters "<name>" and "<email>"
    
  Examples:
  | name        | email                |
  | TestUser01  | test01@example.com   |
  
#Feature: User Registration
#Scenario Outline: New user registration
 # Given the user is on the homepage
 # When the user clicks the "Signup / Login" button
  #And the user enters "<name>" and "<email>"
  #And clicks the "Signup" button
 # Then the user should see the "Enter Account Information" section#

#Examples:
  #| name        | email                |
 # | TestUser01  | test01@example.com   |
