package fr.kaibee.karl.bankaccount.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Account {
  private volatile BigDecimal currentBalance;
  private final List<Operation> operations;

  private Account(AccountBuilder builder) {
    this.currentBalance = builder.balance;
    this.operations = Collections.synchronizedList(new ArrayList<>());
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
    addOperation(new Operation(
      OperationType.DEPOSIT,
      LocalDate.now(),
      OperationSign.CREDIT,
      depositAmount,
      this.getCurrentBalance()));
  }

  public synchronized void withdraw(BigDecimal withdrawalAmount) {
    BigDecimal balanceToModify = getCurrentBalance();

    setCurrentBalance(balanceToModify.subtract(withdrawalAmount));
    addOperation(new Operation(
      OperationType.WITHDRAWAL,
      LocalDate.now(),
      OperationSign.DEBIT,
      withdrawalAmount,
      this.getCurrentBalance()));
  }

  public List<Operation> getOperations() {
    return new ArrayList<>(operations);
  }

  private void addOperation(Operation operation) {
    this.operations.add(operation);
  }

  public static class AccountBuilder {
    private BigDecimal balance;

    public AccountBuilder withBalance(BigDecimal balance) {
      this.balance = balance;
      return this;
    }

    public Account build() {
      setBalanceToZeroWhenMissing();

      return new Account(this);
    }

    private void setBalanceToZeroWhenMissing() {
      if (Objects.isNull(this.balance)) {
        this.balance = new BigDecimal(0);
      }
    }
  }
}
