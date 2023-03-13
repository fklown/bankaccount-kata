package fr.kaibee.karl.bankaccount.domain;

public enum OperationSign {
  CREDIT("+"),
  DEBIT("-");

  private final String symbol;
  OperationSign(String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return symbol;
  }
}
