package kg.allan.purchasetransactions.feature;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.NumberValue;
import kg.allan.purchasetransactions.entity.PurchaseTransactionEntity;
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
    private PurchaseTransactionEntity expectedTransaction;
    private Boolean transactionPossible;
    private PurchaseTransactionEntity effectiveTransaction;
    
    @Given("A new transaction is being made")
    public void setupNewTransaction(){
        expectedTransaction = new PurchaseTransactionEntity();
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

    @And("the stored transaction date is {word}")
    public void setStoredTransactionDate(String date) {
        try{
            expectedTransaction.setDate(LocalDate.parse(date));
        }catch(Exception e){
            fail("Can't extract LocalDate from \"" + date + "\". Reason : " + e.getMessage());
        }
    }

    @And("the stored transaction value is {bigdecimal}")
    public void setStoredTransactionValue(BigDecimal value) {
        expectedTransaction.setAmount(CurrencyUtil.usdMonetaryAmountOf(value));
    }

    @When("the purchase transaction is requested")
    public void performPurchaseTransaction() {
        //FIXME
        effectiveTransaction = expectedTransaction;
//        effectiveTransaction.setConvertedAmount(CurrencyUtil.convert(effectiveTransaction.getAmount(), targetCurrency, BigDecimal.valueOf(4.858)));
    }

    @Then("a conversion must be performed")
    public void verifyConversionIsPerformed() {
    }

    @And("the rounded converted value is {bigdecimal}")
    public void verifyRoundedConvertedValue(BigDecimal converted) {
        NumberValue nv = CurrencyUtil.usdMonetaryAmountOf(converted).getNumber();
//        var comparisonResult = expectedTransaction.getConvertedAmount().getNumber().compareTo(nv);
//        assertThat(comparisonResult).isEqualTo(0);
    }

    @And("the exchange date found is {word}")
    public void verifyExchangeDateFound(String rate_date) {
        LocalDate ldt = null;
        try{
            ldt = LocalDate.parse(rate_date);
        }catch(Exception e){
            fail("Can't extract LocalDate from \"" + rate_date + "\". Reason : " + e.getMessage());
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
