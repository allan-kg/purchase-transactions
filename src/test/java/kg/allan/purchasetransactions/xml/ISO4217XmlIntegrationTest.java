package kg.allan.purchasetransactions.xml;

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
public class ISO4217XmlIntegrationTest {

    @Autowired
    ISO4217Service service;

    @Test
    public void retrieveXmlTest() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(ISO4217Xml.class);

        Unmarshaller unmarshaller;
        try {
            unmarshaller = context.createUnmarshaller();

            String xmlContent = service.getISO4217Xml();

            StringReader reader = new StringReader(xmlContent);

            ISO4217Xml iso = (ISO4217Xml) unmarshaller.unmarshal(reader);

//            for (var xml : iso.getCountryTable().getCountries()) {
//                System.err.println("----------------------------");
//                System.err.println("COUNTRY = " + xml.getCountry());
//                System.err.println("CURRENCY = " + xml.getCurrency());
//                System.err.println("CODE = " + xml.getCode());
//            }
            Assertions.assertEquals(281, iso.getCountryTable().getCountries().size());
            
            String[] codesArray = {"CAD", "BRL"};
            List<String> codes = Arrays.asList(codesArray);
            Assertions.assertEquals(2, iso.getCountryTable().getCountries().stream().filter(c -> codes.contains(c.getCode())).count());
        } catch (RuntimeException e) {
            Assertions.fail(e.getMessage());
        }
    }
}
