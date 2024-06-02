@dp1
Feature: Validate NBA Sixer site Tickets menu and Slides navigation's.

  @smoke @regression @web @reset
    Scenario: As a Derived Product users,
  I must be able to navigate all the slides under Ticket Menu

    Given User Ramesh visits DP1 site
    When  User Ramesh navigates to tickets menu
    Then user Ramesh validates all slides titles
    And user Ramesh validates each slide playing time
