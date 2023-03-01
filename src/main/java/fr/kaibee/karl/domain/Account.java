package fr.kaibee.karl.domain;

import java.math.BigDecimal;

public class Account {
  private volatile BigDecimal currentBalance;

  private Account(AccountBuilder builder) {
    this.currentBalance = builder.balance;
  }

  public synchronized BigDecimal getCurrentBalance() {
    return this.currentBalance;
  }

  private synchronized void setCurrentBalance(BigDecimal amountToAdd) {
    this.currentBalance = amountToAdd;
  }

  public synchronized void deposit(BigDecimal depositAmount) {
    BigDecimal balanceToModify = getCurrentBalance();

    setCurrentBalance(balanceToModify.add(depositAmount));
  }

  public synchronized void withdraw(BigDecimal withdrawalAmount) {
    BigDecimal balanceToModify = getCurrentBalance();

    setCurrentBalance(balanceToModify.subtract(withdrawalAmount));
  }

  public static class AccountBuilder {
    private BigDecimal balance;

    public AccountBuilder withBalance(BigDecimal balance) {
      this.balance = balance;
      return this;
    }

    public Account build() {
      return new Account(this);
    }
  }
}
