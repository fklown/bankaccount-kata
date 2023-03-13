package fr.kaibee.karl.domain;

import fr.kaibee.karl.bankaccount.domain.EntryType;
import fr.kaibee.karl.bankaccount.domain.Operation;
import fr.kaibee.karl.bankaccount.domain.OperationType;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OperationTest {
  @Test
  void shouldHoldStatementAttributes() {
    BigDecimal balance = new BigDecimal("3489.15");
    BigDecimal amount = new BigDecimal("1541.15");
    LocalDate date = LocalDate.of(2020,9,12);
    Operation operation = new Operation(
      OperationType.DEPOSIT,
      date,
      EntryType.CREDIT,
      amount,
      balance);

    assertThat(operation.amount()).isNotNull().isEqualTo(amount);
    assertThat(operation.balance()).isNotNull().isEqualTo(balance);
    assertThat(operation.date()).isNotNull().isEqualTo(date);
    assertThat(operation.sign()).isNotNull().isEqualTo(EntryType.CREDIT);
    assertThat(operation.type()).isNotNull().isEqualTo(OperationType.DEPOSIT);
  }
}