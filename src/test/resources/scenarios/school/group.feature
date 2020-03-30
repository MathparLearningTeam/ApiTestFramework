Feature: Group functions are working correctly
  Background:
    Given account from template "Director" exists
    Given account from template "Director" created school from template "GenericSchool"
    Given account from template "Director" is authenticated
    Given account from template "Teacher" exists
    Given profile for account template "Teacher" and role "Teacher" exists in current school
    And response is saved with key "Teacher"
    Given account from template "Teacher2" exists
    Given profile for account template "Teacher2" and role "Teacher" exists in current school
    And response is saved with key "Teacher2"
    Given account from template "Student" exists
    Given profile for account template "Student" and role "Student" exists in current school
    And response is saved with key "Student"
    Given account from template "Student2" exists
    Given profile for account template "Student2" and role "Student" exists in current school
    And response is saved with key "Student2"

  Scenario: Group can be created and deleted successfully
    When I try to create group with name "Generic school group" headed by teacher "Teacher"
    Then Request finished with status 200
    And I save result with key "group1"
    When I try to get group "group1"
    Then Request finished with status 200
    And I verify the group has name "Generic school group" and assign to teacher "Teacher"
    When I try to delete group "group1"
    Then Request finished with status 200

#  TODO uncomment when error handling is implemented
#    When I try to get group "group1"
#    Then Request finished with status 404

  @current
  Scenario: Group students list can be modified
    When I try to create group with name "Generic students group" headed by teacher "Teacher"
    Then Request finished with status 200
    And I save result with key "group1"
    When I try to modify group "group1" and add students
      |Student|
      |Student2|
    Then Request finished with status 200
    When I try to get group "group1"
    Then Request finished with status 200
    And I verify that group has precisely following students:
      |Student|
      |Student2|
    When I try to modify group "group1" and remove students
      |Student2|
    Then Request finished with status 200
    When I try to get group "group1"
    Then Request finished with status 200
    And I verify that group has precisely following students:
      |Student|

  Scenario: Teacher can be reassigned to group
    When I try to create group with name "Generic teacher group" headed by teacher "Teacher"
    Then Request finished with status 200
    And I save result with key "group1"
    When I try to get group "group1"
    Then Request finished with status 200
    And I verify the group has name "Generic teacher group" and assign to teacher "Teacher"
    When I try to assign teacher "Teacher2" to group "group1"
    Then Request finished with status 200
    When I try to get group "group1"
    Then Request finished with status 200
    And I verify the group has name "Generic teacher group" and assign to teacher "Teacher2"
