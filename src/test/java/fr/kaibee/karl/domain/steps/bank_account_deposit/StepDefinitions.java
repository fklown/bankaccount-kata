package fr.kaibee.karl.domain.steps.bank_account_deposit;

import fr.kaibee.karl.domain.Account;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {
  private Account account;

  @Given("an account with an initial balance of {double}")
  public void an_account_with_an_initial_balance_of(Double balance) {
    BigDecimal initialBalance = new BigDecimal(balance.toString());

    account = new Account.AccountBuilder().withBalance(initialBalance).build();

  }
  @When("a deposit of {double} is made to the account")
  public void a_deposit_of_is_made_to_the_account(Double deposit) {
    BigDecimal depositAmount = new BigDecimal(deposit.toString());

    account.deposit(depositAmount);
  }
  @Then("the account shows a final balance of {double}")
  public void the_account_shows_a_final_balance_of(Double balance) {
    BigDecimal expectedBalance = new BigDecimal(balance.toString());

    assertEquals(expectedBalance, account.getCurrentBalance());
  }
}
