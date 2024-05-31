@cp
Feature: Search product items under shop menu.

  @smoke @regression @web @shop @reset
  Scenario Outline: As a Core Product user , I must be able to search product details
  such as price , title and top seller.

    Given User Ramesh visits Core Product site
    And  User Ramesh navigates to Shop Menu for search products
      | category          | type          |
      | <ProductCategory> | <ProductType> |
    Then user Ramesh should be able to view price, title and top seller details

    Examples:
      | ProductCategory | ProductType |
      | Men’s           | Jackets     |