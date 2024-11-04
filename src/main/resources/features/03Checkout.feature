Feature: Checkout
# Each scenario has a logout step to reset the session,
# allowing tests with specific users for negative test cases.
  Scenario Outline: Successful to complete checkout process
    Given I am logged in with valid "<username>" and "<password>"
    And I click cart button
    And I should see two products in my cart
    When I click checkout button
    And I enter firstname "<firstname>", lastname "<lastname>" and postal code "<postalcode>"
    And I click continue button
    And I am on the overview page
    And I click finish button
    Then I should see the checkout process complete
    And I am logout

    Examples:
      | username      | password      | firstname | lastname | postalcode |
      | standard_user | secret_sauce  | Sang      | Juara    | 17350      |

  Scenario Outline: Failed to finish checkout process
    Given I am logged in with valid "<username>" and "<password>"
    # Add product again because it's empty (from last scenario)
    And I click add to cart button for two products
    And I click cart button
    And I should see two products in my cart
    When I click checkout button
    And I enter firstname "<firstname>", lastname "<lastname>" and postal code "<postalcode>"
    And I click continue button
    And I am on the overview page
    And I click finish button
    Then Finish button is not clickable
    And I am logout

    Examples:
      | username    | password      | firstname | lastname | postalcode |
      | error_user  | secret_sauce  | Sang      | Juara    | 17350      |

  Scenario Outline: Forget to fill all identity requirement
    Given I am logged in with valid "<username>" and "<password>"
    And I click cart button
    And I should see two products in my cart
    When I click checkout button
    And I enter firstname "<firstname>", lastname "<lastname>" and postal code "<postalcode>"
    And I click continue button
    Then I should see an error message saying "<error_message>"
    And I am logout

    Examples:
      | username      | password      | firstname | lastname | postalcode | error_message           |
      | standard_user | secret_sauce  |           | Juara    | 17350      | First Name is required  |
      | standard_user | secret_sauce  | Sang      |          | 17350      | Last Name is required   |
      | standard_user | secret_sauce  | Sang      | Juara    |            | Postal Code is required |