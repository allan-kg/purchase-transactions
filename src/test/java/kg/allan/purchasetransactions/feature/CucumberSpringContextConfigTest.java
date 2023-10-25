package kg.allan.purchasetransactions.feature;

import org.springframework.boot.test.context.SpringBootTest;
import io.cucumber.spring.CucumberContextConfiguration;
import kg.allan.purchasetransactions.PurchaseTransactionsApplication;

/**
 *
 * @author allan
 */
@CucumberContextConfiguration
@SpringBootTest(classes = PurchaseTransactionsApplication.class)
public class CucumberSpringContextConfigTest {
    
}
