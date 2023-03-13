package fr.kaibee.karl.bankaccount.domain;

import java.time.format.DateTimeFormatter;
import java.util.Formatter;

public class AccountStatement implements Report {
  public static final String AMOUNT = "Amount";
  public static final String BALANCE = "Balance";
  public static final String DATE = "Date";
  private static final String DATE_FORMAT = "dd.MM.yyyy";
  private static final String ROW_FORMAT = "%-10s %10s %14s\n";
  private StringBuilder statement;
  private Formatter formatter;

  public AccountStatement() {
    newStatement();
  }

  private void newStatement() {
    this.formatter = new Formatter();
    this.statement = new StringBuilder();
  }

  @Override
  public void generate(Account account) {
    newStatement();

    addHeaderToStatement();
    addOperationRowsToStatement(account);

    buildStatement();
  }

  private void addHeaderToStatement() {
    addRowToStatement(DATE, AMOUNT, BALANCE);
  }

  private void addRowToStatement(String startColumn, String middleColumn, String lastColumn) {
    formatter.format(ROW_FORMAT, startColumn, middleColumn, lastColumn);
  }

  private void addOperationRowsToStatement(Account account) {
    account.getOperations().forEach(this::writeOperationRow);
  }

  private void writeOperationRow(Operation operation) {
    String accountBalance = operation.balance().toString();
    String amountWithSign = operation.sign().getSign() + operation.amount();
    String formattedDate = operation.date().format(DateTimeFormatter.ofPattern(DATE_FORMAT));

    addRowToStatement(formattedDate, amountWithSign, accountBalance);
  }

  private void buildStatement() {
    statement.append(formatter);
  }

  @Override
  public String print() {
    return statement.toString();
  }
}
