package fr.kaibee.karl.domain;

import java.math.BigDecimal;

public record Operation(OperationType type, String date, EntryType sign, BigDecimal amount, BigDecimal balance) {
}
