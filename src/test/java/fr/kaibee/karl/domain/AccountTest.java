package fr.kaibee.karl.domain;

import fr.kaibee.karl.bankaccount.domain.Account;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {
  @Test
  void shouldIncreaseBalanceByAmountDeposited() {
    BigDecimal deposit = new BigDecimal(120);
    BigDecimal initialBalance = new BigDecimal(590);
    Account account = new Account.AccountBuilder().withBalance(initialBalance).build();
    BigDecimal previousBalance = account.getCurrentBalance();
    BigDecimal expectedBalance = previousBalance.add(deposit);

    account.deposit(deposit);

    assertThat(account.getCurrentBalance()).isGreaterThan(previousBalance).isEqualTo(expectedBalance);
  }

  @Test
  void shouldDecreaseBalanceByAmountWithdrawn() {
    BigDecimal withdrawal = new BigDecimal(120);
    BigDecimal initialBalance = new BigDecimal(590);
    Account account = new Account.AccountBuilder().withBalance(initialBalance).build();
    BigDecimal previousBalance = account.getCurrentBalance();
    BigDecimal expectedBalance = previousBalance.subtract(withdrawal);

    account.withdraw(withdrawal);

    assertThat(account.getCurrentBalance()).isLessThan(previousBalance).isEqualTo(expectedBalance);
  }
}