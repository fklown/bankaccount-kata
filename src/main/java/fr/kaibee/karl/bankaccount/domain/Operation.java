package fr.kaibee.karl.bankaccount.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Operation(OperationType type, LocalDate date, OperationSign sign, BigDecimal amount, BigDecimal balance) {
}
