package fr.kaibee.karl.domain;

import fr.kaibee.karl.bankaccount.domain.Account;
import fr.kaibee.karl.bankaccount.domain.AccountStatement;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountStatementTest {
  private static final String DATE_FORMAT_PATTERN = "dd.MM.yyyy";

  @Test
  void shouldProduceStatementBasedOnAccountOperations() {
    Account account = new Account.AccountBuilder().withBalance(new BigDecimal(0)).build();
    account.deposit(new BigDecimal(500));
    AccountStatement accountStatement = new AccountStatement();
    accountStatement.generate(account);
    String date = LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));

    StringBuilder expected = new StringBuilder();
    Formatter formatter = new Formatter();
    formatter.format("%-10s %10s %14s\n", "Date", "Amount", "Balance");
    formatter.format("%-10s %10s %14s\n", date, "+500", "500");
    expected.append(formatter);

    assertThat(accountStatement.print()).isEqualTo(expected.toString());
  }

  @Test
  void shouldProduceIdenticalStatementRegardlessOfCallsWhenAccountRemainsTheSame() {
    Account account = new Account.AccountBuilder().withBalance(new BigDecimal(0)).build();
    account.deposit(new BigDecimal(500));
    AccountStatement accountStatement = new AccountStatement();
    accountStatement.generate(account);
    String date = LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));

    StringBuilder expected = new StringBuilder();
    Formatter formatter = new Formatter();
    formatter.format("%-10s %10s %14s\n", "Date", "Amount", "Balance");
    formatter.format("%-10s %10s %14s\n", date, "+500", "500");
    expected.append(formatter);

    accountStatement.generate(account);
    accountStatement.generate(account);
    accountStatement.generate(account);
    accountStatement.generate(account);

    assertThat(accountStatement.print()).isEqualTo(expected.toString());
  }
}
