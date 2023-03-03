package fr.kaibee.karl.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountStatementTest {
  private static final String DATE_FORMAT_PATTERN = "dd.MM.yyyy";

  @Test
  void shouldLogStatementFromAccountOperations() {
    Account account = new Account.AccountBuilder().withBalance(new BigDecimal(0)).build();
    account.deposit(new BigDecimal(500));
    AccountStatement accountStatement = new AccountStatement();
    accountStatement.generate(account);
    String date = LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));

    Formatter formatter = new Formatter();
    StringBuilder expected = new StringBuilder();
    expected.append(formatter.format("%-10s %10s %14s\n", "Date", "Amount", "Balance"))
      .append(formatter.format("%-10s %10s %14s\n", date, "+500", "500"));

    assertThat(accountStatement.print()).startsWith(expected).endsWith(expected);
  }
}
