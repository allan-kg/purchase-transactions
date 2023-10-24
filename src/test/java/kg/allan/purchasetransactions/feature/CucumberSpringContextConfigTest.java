package kg.allan.purchasetransactions.feature;

import io.cucumber.junit.platform.engine.Cucumber;
import org.springframework.boot.test.context.SpringBootTest;
import io.cucumber.spring.CucumberContextConfiguration;
import kg.allan.purchasetransactions.PurchaseTransactionsApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.suite.api.Suite;

/**
 *
 * @author allan
 */
@CucumberContextConfiguration
@SpringBootTest(classes = PurchaseTransactionsApplication.class)
public class CucumberSpringContextConfigTest {
    
}
