Feature: Account creation and registration works as expected

  Scenario: Director can create any type of users
    When i check if email from account template "Director" available
    Then Request finished with status 200
    Then response body has key "available" with value "true"
    Given account from template "Director" exists
    When i check if email from account template "Director" available
    Then Request finished with status 200
    Then response body has key "available" with value "false"
