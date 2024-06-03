@cp
Feature: Search product details under shop menu.


  @smoke @web @shop
  Scenario: As a Core Product ser, i must able to validate
  Videos Feeds and count the videos feeds those are present in the page

    Given User Ramesh visits Core Product site
    And  User Ramesh navigates to News & Features from "..." Menu
    Then user Ramesh validate total count 23 videos feed
    And user Ramesh validate 21 videos feeds those are present more than 2 day in the page


  @smoke @regression @web @shop
  Scenario Outline: As a Core Product user , I must be able to search product details
  for information such as price , title and top seller.

    Given User Ramesh visits Core Product site
    When  User Ramesh searches product details by navigating to "Shop" Menu
      | Category          | Type          |
      | <ProductCategory> | <ProductType> |
    Then user Ramesh should be able to view price, title and top seller details

    Examples:
      | ProductCategory | ProductType |
      | Men             | Jackets     |

