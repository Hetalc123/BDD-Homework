Feature: emailAFriend
  @emailAFriend
    Scenario: As a Registered User I Should Be Able To EmailAFriend
  Given I am On Login Page
    When I enter login details and I successfully logged in
    And I click on product and click on email
    Then I should be able to email a friend successfully