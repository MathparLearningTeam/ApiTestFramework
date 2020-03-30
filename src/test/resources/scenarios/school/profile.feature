Feature: School profiles can be fetched, created and deleted

  Background:
    Given account from template "Director" exists
    Given account from template "Teacher" exists
    Given account from template "HeadTeacher" exists
    Given account from template "Student" exists
    Given account from template "Student2" exists
    Given account from template "Director" is authenticated
    Given account from template "Director" created school from template "GenericSchool"

  Scenario: Profile can be fetched
    When i try to fetch own profile
    Then Request finished with status 200
    And i verify my profile of role "Director" and school template "GenericSchool"

  @current
  Scenario: Profiles can be created by director
    When i try to create a profile for account template "Teacher" and role "Teacher"
    Then Request finished with status 200
    And response is saved with key "TeacherProfile"
    When i try to create a profile for account template "HeadTeacher" and role "HeadTeacher"
    Then Request finished with status 200
    And response is saved with key "HeadTeacher"
    When i try to create a profile for account template "Student" and role "Student"
    Then Request finished with status 200
    And response is saved with key "Student"
    When i try to create a profile for account template "Student2" and role "Student"
    Then Request finished with status 200
    And response is saved with key "Student2"

  Scenario: Get school profiles works correctly
    When i try to create a profile for account template "Teacher" and role "Teacher"
    Then Request finished with status 200
    And response is saved with key "TeacherProfile"
    When i try to get my school profiles
    Then i verify school contains profiles of account templates
    | Teacher  |
    | Director |
    When i try to create a profile for account template "HeadTeacher" and role "HeadTeacher"
    Then Request finished with status 200
    And response is saved with key "HeadTeacherProfile"
    When i try to create a profile for account template "Student" and role "Student"
    Then Request finished with status 200
    And response is saved with key "Student"
    When i try to get my school profiles
    Then i verify school contains profiles of account templates
    | Teacher     |
    | Director    |
    | HeadTeacher |
    | Student     |
