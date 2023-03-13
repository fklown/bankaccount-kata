Feature: Is withdrawn amount correctly subtracted to an account balance?
  For purchases and business purposes, every bank user should be able to withdraw any amount they currently have on her or his bank account.

  Scenario Outline: Initial account with a balance, amount to subtract, the final balance should represents the withdrawal subtracted from the balance.
    Given an initial account with a balance of <initial_balance>
    When a withdrawal of <withdrawal_amount> is made from the account
    Then this same account shows a final balance of <final_balance>

    Examples:
      | initial_balance | withdrawal_amount | final_balance |
      | 100             | 10                | 90            |
      | 102082          | 102082            | 0             |
      | 500             | 879               | -379          |
