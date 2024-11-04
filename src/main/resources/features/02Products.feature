Feature: Products page

  Scenario Outline: Successful add products to cart
    Given I am logged in with valid "<username>" and "<password>"
    And I am on the products page
    When I click add to cart button for two products
    And I click cart button
    Then I should see two products in my cart
    # Need logout to change user for some error scenario
    And I am logout

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |
