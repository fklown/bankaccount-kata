package fr.kaibee.karl.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Operation(OperationType type, LocalDate date, EntryType sign, BigDecimal amount, BigDecimal balance) {
}
