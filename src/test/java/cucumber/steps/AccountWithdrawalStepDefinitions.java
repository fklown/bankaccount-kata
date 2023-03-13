package cucumber.steps;

import fr.kaibee.karl.bankaccount.domain.Account;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountWithdrawalStepDefinitions {
  private Account account;

  @Given("an initial account with a balance of {int}")
  public void an_account_with_an_initial_balance_of(Integer balance) {
    BigDecimal initialBalance = new BigDecimal(balance.toString());

    account = new Account.AccountBuilder().withBalance(initialBalance).build();
  }

  @When("a withdrawal of {int} is made from the account")
  public void aWithdrawalOfWithdrawal_amountIsMadeFromTheAccount(Integer withdrawal) {
    BigDecimal withdrawalAmount = new BigDecimal(withdrawal.toString());

    account.withdraw(withdrawalAmount);
  }

  @Then("this same account shows a final balance of {int}")
  public void the_account_shows_a_final_balance_of(Integer balance) {
    BigDecimal expectedBalance = new BigDecimal(balance.toString());

    assertEquals(expectedBalance, account.getCurrentBalance());
  }
}
