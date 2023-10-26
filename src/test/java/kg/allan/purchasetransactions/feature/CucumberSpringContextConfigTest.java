/*
 * CucumberSpringContextConfigTest
 *
 * v1.0
 *
 * 2023
 *
 * Author: Allan Krama Guimarães
 */

package kg.allan.purchasetransactions.feature;

import org.springframework.boot.test.context.SpringBootTest;
import io.cucumber.spring.CucumberContextConfiguration;
import kg.allan.purchasetransactions.PurchaseTransactionsApplication;
import kg.allan.purchasetransactions.repository.PurchaseTransactionRepository;
import org.springframework.boot.test.mock.mockito.SpyBean;

/**
 *
 * @author Allan Krama Guimarães
 */
@CucumberContextConfiguration
@SpringBootTest(classes = PurchaseTransactionsApplication.class)
public class CucumberSpringContextConfigTest {
}
