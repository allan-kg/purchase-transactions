package kg.allan.purchasetransactions.feature;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.NumberValue;
import kg.allan.purchasetransactions.entity.Transaction;
import kg.allan.purchasetransactions.util.CurrencyUtil;
import lombok.extern.log4j.Log4j2;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author allan
 */
@Log4j2
public class CurrencyConversionStepDefinitions extends CucumberSpringContextConfigTest {

    private CurrencyUnit targetCurrency;
    private Transaction expectedTransaction;
    private Boolean transactionPossible;
    private Transaction effectiveTransaction;
    
    @Given("A new transaction is being made")
    public void setupNewTransaction(){
        expectedTransaction = new Transaction();
        transactionPossible = true;
    }
    
    @And("the target currency is {word}")
    public void setTargetCurrency(String currency) {
        try{
            this.targetCurrency = Monetary.getCurrency(currency);
        }catch(Exception e){
            fail("Can't extract currency from \"" + currency + "\". Reason : " + e.getMessage());
        }
    }

    @And("the stored transaction datetime is {word}")
    public void setStoredTransactionDate(String datetime) {
        try{
            expectedTransaction.setDate(LocalDateTime.parse(datetime));
        }catch(Exception e){
            fail("Can't extract LocalDateTime from \"" + datetime + "\". Reason : " + e.getMessage());
        }
    }

    @And("the stored transaction value is {bigdecimal}")
    public void setStoredTransactionValue(BigDecimal value) {
        expectedTransaction.setAmount(CurrencyUtil.usdMonetaryAmount(value));
    }

    @When("the purchase transaction is requested")
    public void performPurchaseTransaction() {
        //FIXME
        effectiveTransaction = expectedTransaction;
        effectiveTransaction.setConvertedAmount(CurrencyUtil.convert(effectiveTransaction.getAmount(), targetCurrency, BigDecimal.valueOf(4.858)));
    }

    @Then("a conversion must be performed")
    public void verifyConversionIsPerformed() {
    }

    @And("the rounded converted value is {bigdecimal}")
    public void verifyRoundedConvertedValue(BigDecimal converted) {
        NumberValue nv = CurrencyUtil.usdMonetaryAmount(converted).getNumber();
        var comparisonResult = expectedTransaction.getConvertedAmount().getNumber().compareTo(nv);
        assertThat(comparisonResult).isEqualTo(0);
    }

    @And("the exchange datetime found is {word}")
    public void verifyExchangeDateFound(String rate_date) {
        LocalDateTime ldt = null;
        try{
            ldt = LocalDateTime.parse(rate_date);
        }catch(Exception e){
            fail("Can't extract LocalDateTime from \"" + rate_date + "\". Reason : " + e.getMessage());
        }
        var comparisonResult = expectedTransaction.getDate().compareTo(ldt);
        assertThat(comparisonResult).isEqualTo(0);
    }

    @And("there is no conversion rate up to {word}")
    public void verifyNoConversionRate(String mindate) {
    }

    @And("the value returned is the error message")
    public void verifyErrorMessageReturned() {
    }
}
