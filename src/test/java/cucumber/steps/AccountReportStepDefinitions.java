package cucumber.steps;

import fr.kaibee.karl.bankaccount.domain.Account;
import fr.kaibee.karl.bankaccount.domain.AccountStatement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.math.BigDecimal;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountReportStepDefinitions {
  private Account account;
  private String generatedStatement;

  @Given("an account with an initial balance such as {int}")
  public void an_account_with_an_initial_balance_such_as(Integer balance) {
    BigDecimal initialBalance = new BigDecimal(balance.toString());

    account = new Account.AccountBuilder().withBalance(initialBalance).build();
  }

  @When("a deposit of {int} is made to this account")
  public void a_deposit_of_is_made_to_this_account(Integer deposit) {
    BigDecimal depositAmount = new BigDecimal(deposit.toString());

    deposit(depositAmount);
  }

  @When("a withdrawal of {int} is made from this account")
  public void a_withdrawal_of_is_made_from_this_account(Integer withdrawal) {
    BigDecimal withdrawalAmount = new BigDecimal(withdrawal.toString());

    withdraw(withdrawalAmount);
  }

  @Then("the account statement shows a final balance such as {int}")
  public void the_account_statement_shows_a_final_balance_such_as(Integer balance) {
    generateStatement();
    String expectedBalance = new BigDecimal(balance).toString();

    assertThat(generatedStatement).contains(expectedBalance);
  }

  @Then("the account statement also shows the deposit amount {int}")
  public void the_account_statement_also_shows_both_deposits_amounts(Integer amount) {
    generateStatement();
    String expectedMovementWithSign = "+" + new BigDecimal(amount);

    assertThat(generatedStatement).contains(expectedMovementWithSign);
  }

  @Then("the account statements also shows the withdrawal amount {int}")
  public void the_account_statements_also_shows_the_withdrawal_amount(Integer amount) {
    generateStatement();
    String expectedMovementWithSign = "-" + new BigDecimal(amount);

    assertThat(generatedStatement).contains(expectedMovementWithSign);
  }

  private void deposit(BigDecimal amount) {
    account.deposit(amount);
  }

  private void withdraw(BigDecimal amount) {
    account.withdraw(amount);
  }

  private void generateStatement() {
    if (Objects.isNull(generatedStatement) || generatedStatement.isBlank()) {
      AccountStatement statement = new AccountStatement();
      statement.generate(account);
      generatedStatement = statement.print();
    }
  }
}
