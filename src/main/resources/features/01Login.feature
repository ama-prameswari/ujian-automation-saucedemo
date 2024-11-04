Feature: Login

  Scenario Outline: Successful login with valid credentials
    Given I am on the login page
    When I enter a valid "<username>" and "<password>"
    And I click the login button
    Then I should be redirected to products page

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |

  Scenario Outline: Failed login with invalid credentials
    Given I am on the login page
    When I enter an invalid "<username>" and "<password>"
    And I click the login button
    Then I should see an error message saying "<error_message>"

    Examples:
      | username      | password      | error_message                                               |
      | invalid       | invalid       | Username and password do not match any user in this service |
      |               | secret_sauce  | Username is required                                        |
      | standard_user |               | Password is required                                        |
