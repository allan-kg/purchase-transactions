package kg.allan.purchasetransactions.service.impl;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import kg.allan.purchasetransactions.dto.xml.CountryISO4217Xml;
import kg.allan.purchasetransactions.dto.xml.ISO4217Xml;
import kg.allan.purchasetransactions.exception.ElementNotFoundException;
import kg.allan.purchasetransactions.exception.GetRequestException;
import kg.allan.purchasetransactions.exception.XmlParseException;
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
    
    private List<CountryISO4217Xml> countries;
    
    @Value("${iso4217.xml.url}")
    private String xmlURL;

    @Autowired
    private RestService restService;

    private String fetchISO4217XmlContent() throws GetRequestException {
        ResponseEntity<String> response = restService.getRestTemplate().getForEntity(xmlURL, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new GetRequestException("Error: Unable to fetch ISO 4217 Xml.");
        }
    }

    private List<CountryISO4217Xml> fetchCountryISO4217XmlList() throws XmlParseException, GetRequestException {
        try {
        JAXBContext context = JAXBContext.newInstance(ISO4217Xml.class);

        Unmarshaller unmarshaller;
            unmarshaller = context.createUnmarshaller();

            String xmlContent = fetchISO4217XmlContent();

            StringReader reader = new StringReader(xmlContent);

            ISO4217Xml iso = (ISO4217Xml) unmarshaller.unmarshal(reader);

            return iso.getCountryTable().getCountries();
        } catch (JAXBException ex) {
            throw new XmlParseException("Error: Unable to parse ISO4217XML.", ex);
        }
    }
    
    @PostConstruct
    private void fetchXmlData() throws XmlParseException, GetRequestException{
        this.countries = fetchCountryISO4217XmlList();
    }

    public List<CountryISO4217Xml> getCountries() {
        return this.countries;
    }

    public String countryCode(String name) throws ElementNotFoundException{
        var oc = countries.stream()
                .parallel()
                .filter(c -> c.getCountry().equalsIgnoreCase(name))
                .findFirst();
        if(!oc.isPresent()){
            throw new ElementNotFoundException("ISO 4217 code not found for country \"" + name + "\"");
        }
        var country = oc.get();
        return country.getCode();
    }

    public String countryName(String code) throws ElementNotFoundException{
        var oc = countries.stream()
                .parallel()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst();
        if(!oc.isPresent()){
            throw new ElementNotFoundException("Country name not found for ISO 4217 code \"" + code + "\"");
        }
        var country = oc.get();
        return country.getCountry();
    }
}
