package fr.kaibee.karl.bankaccount.domain;

public interface Report {
  void generate(Account account);

  String print();
}
