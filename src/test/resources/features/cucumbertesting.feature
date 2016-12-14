Feature: SoftwareTestingAssignment

  Scenario: Successful Affiliate Login
    Given I am an affiliate trying to log in
    When I login using valid credentials
    Then I should be taken to my account admin page

  Scenario: Unsuccessful Affiliate Login
    Given I am an affiliate trying to log in
    When I login using invalid credentials
    Then I should see an error message
    And I should remain on the login page

  Scenario: Account Admin Page Contents
    Given I am a logged in affiliate
    When I visit my account admin page
    Then I should see my balance
    And I should see a button allowing me to withdraw my balance

  Scenario Outline: Withdrawals
    Given I am a logged in affiliate
    And my balance is <balance>
    When I try to withdraw my balance
    Then I should see a message indicating <message-type>
    And my new balance will be <new-balance>

Examples:
|balance|message-type|new-balance|
|4.99   |error       |4.99       |
|5.00   |success     |0.00       |
|0.00   |error       |0.00       |
|142.12 |success     |0.00       |