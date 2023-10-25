package kg.allan.purchasetransactions.dto.json;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import kg.allan.purchasetransactions.PurchaseTransactionsApplication;
import kg.allan.purchasetransactions.service.TRREService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 *
 * @author allan
 */
@SpringBootTest(classes = PurchaseTransactionsApplication.class)
@ActiveProfiles("integration")
public class TRREITest {
    
    @Autowired
    TRREService service;
    
    @Test
    public void retrieveSingleCountryRateTest()throws Exception{
        var oCountry = service.fetchRateByDate("Brazil", "2023-06-30");
        Assertions.assertTrue(oCountry.isPresent());
        var country = oCountry.get();
        Assertions.assertEquals("Brazil", country.getCountry());
        Assertions.assertEquals(LocalDate.of(2023, Month.JUNE, 30), country.getRecordDate());
        Assertions.assertEquals(new BigDecimal("4.858"), country.getExchangeRate());
    }
    
    @Test
    public void retrieveExchangeRatesForCountryByPeriodTest()throws Exception{
        var countries = service.fetchRatesByDateRange("Brazil", "2023-03-31", "2023-06-30");
        Assertions.assertEquals(2, countries.size());
        Assertions.assertEquals(new BigDecimal("5.098"), countries.get(0).getExchangeRate());
        Assertions.assertEquals(new BigDecimal("4.858"), countries.get(1).getExchangeRate());
        Assertions.assertEquals(LocalDate.of(2023, 03, 31), countries.get(0).getRecordDate());
        Assertions.assertEquals(LocalDate.of(2023, 06, 30), countries.get(1).getRecordDate());
        Assertions.assertTrue(countries.stream().allMatch(c -> c.getCountry().equalsIgnoreCase("brazil")));
    }
}
