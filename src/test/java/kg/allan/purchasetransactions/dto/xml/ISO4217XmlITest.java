package kg.allan.purchasetransactions.dto.xml;

import kg.allan.purchasetransactions.dto.xml.ISO4217Xml;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kg.allan.purchasetransactions.PurchaseTransactionsApplication;
import kg.allan.purchasetransactions.service.ISO4217Service;
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
public class ISO4217XmlITest {

    @Autowired
    ISO4217Service service;

    @Test
    public void retrieveXmlTest() throws Exception {
        try {
            var countries = service.retrieveCountryISO4217XmlList();

            Assertions.assertEquals(281, countries.size());
            
            String[] codesArray = {"CAD", "BRL"};
            List<String> codes = Arrays.asList(codesArray);
            Assertions.assertEquals(2, countries.stream().filter(c -> codes.contains(c.getCode())).count());
        } catch (RuntimeException e) {
            Assertions.fail(e.getMessage());
        }
    }
}
