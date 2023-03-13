Feature: Are account operations correctly printed to the account statement?
  For monitoring purposes, every bank user should be able to a statement of her or his bank account.

  Scenario Outline: Initial account, amount to add, the final balance should represents the sum of them both.
    Given an account with an initial balance such as <initial_balance>
    When a deposit of <deposit_amount> is made to this account
    And a withdrawal of <withdrawal_amount> is made from this account
    Then the account statement shows a final balance such as <final_balance>
    And the account statement also shows the deposit amount <deposit_amount>
    And the account statements also shows the withdrawal amount <withdrawal_amount>

    Examples:
      | initial_balance | deposit_amount | withdrawal_amount | final_balance |
      | 80              | 920            | 900               | 100           |
      | 0               | 200            | 200               | 0             |
      | -56             | 156            | 100               | 0             |
      | -890            | 200            | 0                 | -690          |
