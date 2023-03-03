package fr.kaibee.karl.domain;

public interface Report {
  void generate(Account account);

  String print();
}
