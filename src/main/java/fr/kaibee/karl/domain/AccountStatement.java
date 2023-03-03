package fr.kaibee.karl.domain;

import java.time.format.DateTimeFormatter;
import java.util.Formatter;

public class AccountStatement implements Report {
  public static final String AMOUNT = "Amount";
  public static final String BALANCE = "Balance";
  public static final String DATE = "Date";
  private static final String DATE_FORMAT = "dd.MM.yyyy";
  private static final String ROW_FORMAT = "%-10s %10s %14s\n";
  private final StringBuilder statement;
  private final Formatter formatter;

  public AccountStatement() {
    this.formatter = new Formatter();
    this.statement = new StringBuilder();
  }

  @Override
  public void generate(Account account) {
    writeHeader();

    account.getOperations().forEach(this::writeOperationRow);
    statement.append(formatter);
  }

  private void writeHeader() {
    formatter.format(ROW_FORMAT, DATE, AMOUNT, BALANCE);
  }

  private void writeOperationRow(Operation operation) {
    String amountWithSign = operation.sign().getSign() + operation.amount();

    formatter.format(
      ROW_FORMAT,
      operation.date().format(DateTimeFormatter.ofPattern(DATE_FORMAT)),
      amountWithSign,
      operation.balance());

  }

  @Override
  public String print() {
    return statement.toString();
  }
}
