package fr.kaibee.karl.domain;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OperationUnitTest {
  @Test
  void shouldHoldStatementAttributes() {
    BigDecimal balance = new BigDecimal("3489.15");
    BigDecimal amount = new BigDecimal("1541.15");
    Operation operation = new Operation(OperationType.DEPOSIT, "2020-09-12", EntryType.CREDIT, amount, balance);

    assertThat(operation.date()).isNotNull();
    assertThat(operation.amount()).isNotNull().isGreaterThan(new BigDecimal(0));
    assertThat(operation.sign()).isNotNull().isEqualTo(EntryType.CREDIT);
    assertThat(operation.balance()).isNotNull();
    assertThat(operation.type()).isNotNull();
  }
}