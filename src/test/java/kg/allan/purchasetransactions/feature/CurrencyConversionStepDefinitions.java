package kg.allan.purchasetransactions.feature;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import java.math.BigDecimal;
import java.time.LocalDate;
import kg.allan.purchasetransactions.dto.PurchaseWithExchangeDTO;
import kg.allan.purchasetransactions.entity.PurchaseTransactionEntity;
import kg.allan.purchasetransactions.exception.ConversionFailedException;
import kg.allan.purchasetransactions.service.PurchaseTransactionService;
import kg.allan.purchasetransactions.util.CurrencyUtil;
import kg.allan.purchasetransactions.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import static org.assertj.core.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author allan
 */
@Log4j2
public class CurrencyConversionStepDefinitions extends CucumberSpringContextConfigTest {

    @Autowired
    private PurchaseTransactionService service;
    
    private String targetCountry;
    private PurchaseTransactionEntity entity;
    PurchaseWithExchangeDTO convertedDto;

    @Given("A new conversion is being made")
    public void setupNewTransaction() {
        targetCountry = null;
        convertedDto = null;
        entity = new PurchaseTransactionEntity();
        entity.setDescription("");
    }

    @And("the target country is {word}")
    public void setTargetCurrency(String country) {
        assertThat(country).isNotNull();
        assertThat(country).isNotBlank();
        targetCountry = country;
    }

    @And("the date is {word}")
    public void setStoredTransactionDate(String date) {
        try {
            var ld = LocalDate.parse(date, DateUtil.FORMATTER_ISO8601);
            entity.setDate(ld);
        } catch (Exception e) {
            fail("Can't extract LocalDate from \"" + date + "\". Reason : " + e.getMessage());
        }
    }

    @And("the value is {bigdecimal}")
    public void setStoredTransactionValue(BigDecimal value) {
        entity.setAmount(CurrencyUtil.usdMonetaryAmountOf(value));
    }

    @When("the conversion is requested")
    public void performPurchaseTransaction() {
        try {
            convertedDto = service.convert(entity, targetCountry);
        } catch (Exception ex) {
            fail("Can't convert money. Reason : " + ex.getMessage());
        }
    }

    @Then("a conversion must be performed")
    public void verifyConversionIsPerformed() {
        assertThat(convertedDto.getConvertedAmount()).isNotBlank();
        assertThat(convertedDto.getRate()).isNotBlank();
    }

    @And("the rounded converted value is {word}")
    public void verifyRoundedConvertedValue(String converted) {
        assertThat(convertedDto.getConvertedAmount()).isNotNull();
        assertThat(convertedDto.getConvertedAmount()).isNotBlank();
        assertThat(convertedDto.getConvertedAmount()).hasSizeGreaterThan(4);
        assertThat(convertedDto.getConvertedAmount().substring(4)).isEqualTo(converted);
    }

    @And("the exchange date found is {word}")
    public void verifyExchangeDateFound(String rate_date) {
        try {
            var ld = LocalDate.parse(convertedDto.getExchangeDate());//HERE< GET EXCHANGE DATE // XXX TODO FIXME
            assertThat(DateUtil.iso8601Of(ld)).isEqualTo(rate_date);
        } catch (Exception e) {
            fail("Can't extract LocalDate from \"" + convertedDto.getDate() + "\". Reason : " + e.getMessage());
        }
    }

    @When("the impossible conversion is requested")
    public void verifyNoConversionRate() {
        assertThatThrownBy(() -> service.convert(entity, targetCountry))
                .isInstanceOf(ConversionFailedException.class);
    }

    @And("there is no exchange rate found")
    public void verifyErrorMessageReturned() {
        assertThat(convertedDto).isNull();
    }
}
