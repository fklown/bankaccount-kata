package fr.kaibee.karl.bankaccount.domain;

public enum EntryType {
  CREDIT("+"),
  DEBIT("-");

  private final String sign;
  EntryType(String sign) {
    this.sign = sign;
  }

  public String getSign() {
    return sign;
  }
}
