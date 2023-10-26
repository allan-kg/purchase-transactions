/*
 * ISO4217XmlITest
 *
 * v1.0
 *
 * 2023
 *
 * Author: Allan Krama Guimarães
 */

package kg.allan.purchasetransactions.dto.xml;

import java.util.Arrays;
import java.util.List;
import kg.allan.purchasetransactions.PurchaseTransactionsApplication;
import kg.allan.purchasetransactions.exception.ElementNotFoundException;
import kg.allan.purchasetransactions.service.ISO4217Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Allan Krama Guimarães
 */
@SpringBootTest(classes = PurchaseTransactionsApplication.class)
@ActiveProfiles("integration")
public class ISO4217XmlITest {

    @Autowired
    ISO4217Service service;

    @Test
    public void fetchXml() throws Exception {
        try {
            var countries = service.getCountries();

            Assertions.assertEquals(281, countries.size());

            String[] codesArray = {"CAD", "BRL"};
            List<String> codes = Arrays.asList(codesArray);
            Assertions.assertEquals(2, countries.stream().filter(c -> codes.contains(c.getCode())).count());
        } catch (RuntimeException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    public void countryCode() throws ElementNotFoundException {
        assertThatThrownBy(() -> service.currencyCodeOf("ASDASD"))
                .isInstanceOf(ElementNotFoundException.class);
        assertThat(service.currencyCodeOf("Brazil")).isEqualToIgnoringCase("BRL");
        assertThat(service.currencyCodeOf("Canada")).isEqualToIgnoringCase("CAD");
    }
}
