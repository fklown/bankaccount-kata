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
  }

  private void writeHeader() {
    Formatter header = formatter.format(ROW_FORMAT, DATE, AMOUNT, BALANCE);

    statement.append(header);
  }

  private void writeOperationRow(Operation operation) {
    String amountWithSign = operation.sign().getSign() + operation.amount();

    Formatter operationRow = formatter.format(
      ROW_FORMAT,
      operation.date().format(DateTimeFormatter.ofPattern(DATE_FORMAT)),
      amountWithSign,
      operation.balance());

    statement.append(operationRow);
  }

  @Override
  public String print() {
    return statement.toString();
  }
}
