@dp2
Feature: Validate NBA Bulls site home page footers.

  @smoke @regression @web @reset
    Scenario: As a Derived Product users,
  I should not see any duplicate link in home page footer

    Given User Ramesh visits DP2 site
    When  User Ramesh navigates to home page footer
    Then user Ramesh validates any reports duplicate links
