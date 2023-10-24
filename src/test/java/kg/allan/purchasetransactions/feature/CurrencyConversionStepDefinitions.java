package kg.allan.purchasetransactions.feature;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import java.util.Currency;
import kg.allan.purchasetransactions.entity.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author allan
 */
@Log4j2
@RequiredArgsConstructor
public class CurrencyConversionStepDefinitions extends CucumberSpringContextConfigTest {
    private Currency targetCurrency;
    private Transaction transaction;

    @Given("there is a target currency defined")
    public void givenTargetCurrencyDefined() {
       log.info("ASDSADASDASD");
       Assertions.assertEquals(1, 1);
    }

    @Given("there is a transaction registered with a day that the target conversion rate does not have an exact date match")
    public void givenTransactionRegisteredWithoutExactDateMatch() {
    }

    @Given("there is a registered conversion rate less than the transaction date for that currency")
    public void givenConversionRateLessThanTransactionDate() {
    }

    @Given("the date isn't older than 6 months of the transaction date")
    public void givenDateNotOlderThanSixMonths() {
    }

    @When("the conversion is requested")
    public void whenConversionRequested() {
    }

    @Then("The value of that purchase transaction is converted to the target currency")
    public void thenValueConvertedToTargetCurrency() {
    }

    @Then("the conversion rate is not more than 6 months old from the transaction date")
    public void thenConversionRateNotMoreThanSixMonthsOld() {
    }

    @Then("the conversion rate being used is the first before the transaction date")
    public void thenConversionRateIsTheFirstBeforeTransactionDate() {
    }
}
