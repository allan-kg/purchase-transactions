/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kg.allan.purchasetransactions.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import kg.allan.purchasetransactions.PurchaseTransactionsApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author allan
 */
@SpringBootTest(classes = PurchaseTransactionsApplication.class)
public class ISO4217XmlTest {
    
    public static final String XMLFILE = "codes.xml";
    
    @Test
    public void readXmlFromFile() throws JAXBException{
         JAXBContext context = JAXBContext.newInstance(ISO4217Xml.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            ISO4217Xml iso = (ISO4217Xml) unmarshaller.unmarshal(new File(XMLFILE));

            for(var xml : iso.getCountryTable().getCountries()){
                System.err.println("----------------------------");
                System.err.println("COUNTRY = " + xml.getCountry());
                System.err.println("CURRENCY = " + xml.getCurrency());
                System.err.println("CODE = " + xml.getCode());
            }
    }
}
