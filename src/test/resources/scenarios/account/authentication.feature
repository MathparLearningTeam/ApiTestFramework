Feature: User can authenticate and check if authenticated

  Background:
    Given account from template "Director" exists

  Scenario:
    When I try to check if authentication token "NOT_VALID_TOKEN" is valid
    Then Request finished with status 200
    And response body has key "valid" with value "false"
    When I try to log in with credentials from template "Director"
    Then I am authenticated successfully
    When I try to check if current authentication token is valid
    Then Request finished with status 200
    And response body has key "valid" with value "true"
