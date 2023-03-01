Feature: Is deposited amount correctly added to an account balance?
  Every bank user should be able to deposit any amount into her or his bank account.

  Scenario Outline: Initial account, amount to add, the final balance should represents the sum of them both.
    Given an account with an initial balance of <initial_balance>
    When a deposit of <deposit_amount> is made to the account
    Then the account shows a final balance of <final_balance>

  Examples:
    | initial_balance | deposit_amount | final_balance |
    | 100             | 120            | 220           |
    | 10000           | -120           | 9880          |
    | 458.10          | 120.87         | 578.97        |
    | -589            | 789            | 200           |