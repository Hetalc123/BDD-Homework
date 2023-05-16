Feature: aregister
  @register
  Scenario: As a User, I should able to register successfully
  so that, I can Use all User features
   Given I am on registration page
    When I enter required registration details
    Then I should able to register successfully