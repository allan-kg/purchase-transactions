package kg.allan.purchasetransactions.service.impl;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import kg.allan.purchasetransactions.dto.xml.CountryISO4217Xml;
import kg.allan.purchasetransactions.dto.xml.ISO4217Xml;
import kg.allan.purchasetransactions.service.ISO4217Service;
import kg.allan.purchasetransactions.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

/**
 *
 * @author allan
 */
@Service
public class ISO4217ServiceImpl implements ISO4217Service {

    @Value("${iso4217.xml.url}")
    private String xmlURL;

    @Autowired
    private RestService restService;

    public String retrieveISO4217XmlContent() throws Exception {
        ResponseEntity<String> response = restService.getRestTemplate().getForEntity(xmlURL, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RestClientException("Error: Unable to fetch ISO 4217 Xml.");
        }
    }

    public List<CountryISO4217Xml> retrieveCountryISO4217XmlList() throws Exception {
        JAXBContext context = JAXBContext.newInstance(ISO4217Xml.class);

        Unmarshaller unmarshaller;
        try {
            unmarshaller = context.createUnmarshaller();

            String xmlContent = retrieveISO4217XmlContent();

            StringReader reader = new StringReader(xmlContent);

            ISO4217Xml iso = (ISO4217Xml) unmarshaller.unmarshal(reader);

            return iso.getCountryTable().getCountries();
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }
}
