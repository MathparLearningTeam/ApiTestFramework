Feature: Class functions are working correctly
  Background:
    Given account from template "Director" exists
    Given account from template "Director" is authenticated
    Given account from template "Director" created school from template "GenericSchool"
    Given account from template "Teacher" exists
    Given account from template "Student" exists
    Given account from template "Student2" exists
    Given profile for account template "Teacher" and role "Teacher" exists in current school
    And response is saved with key "Teacher"
    Given profile for account template "Student" and role "Student" exists in current school
    And response is saved with key "Student"
    Given profile for account template "Student2" and role "Student" exists in current school
    And response is saved with key "Student2"

  Scenario: Class can be created and deleted successfully
    When I try to create class with name "Test class 1"
    Then Request finished with status 200
    And I save result with key "class1"
    When I try to get class "class1"
    Then Request finished with status 200
    And I verify that class has name "Test class 1"
    When I try to delete class "class1"
    Then Request finished with status 200
  #TODO add error handling before using this
    #When I try to get class "class1"
    #Then Request finished with status 404

  Scenario: Class can be populated with students and students could be removed
    When I try to create class with name "Test class 1"
    Then Request finished with status 200
    And I save result with key "class1"
    When I try to modify class "class1" and add students
      |Student|
      |Student2|
    Then Request finished with status 200
    When I try to get class "class1"
    Then Request finished with status 200
    And I verify that class has only following students
      |Student|
      |Student2|
    When I try to modify class "class1" and remove students
      |Student|
    Then Request finished with status 200
    When I try to get class "class1"
    Then Request finished with status 200
    And I verify that class has only following students
      |Student2|
