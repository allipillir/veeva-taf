@cp
Feature: Search product details under shop menu.

  @smoke @regression @web @shop @reset
  Scenario Outline: As a Core Product user , I must be able to search product details
  for information such as price , title and top seller.

    Given User Ramesh visits Core Product site
    And  User Ramesh searches product details by navigating to "Shop" Menu
      | Category          | Type          |
      | <ProductCategory> | <ProductType> |
    Then user Ramesh should be able to view price, title and top seller details

    Examples:
      | ProductCategory | ProductType |
      | Men             | Jackets     |