Feature: Password restoration is working as expected

  Scenario: Restore password request isn't failing when receiving invalid email
    When i send a request to change password for an email "not_existing_email"
    Then Request finished with status 200
