package kg.allan.purchasetransactions.service.impl;

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

    
    private String xmlURL;

    public ISO4217ServiceImpl(@Value("${iso4217.xml.url}") String xmlURL){
        this.xmlURL = xmlURL;
    }

    @Autowired
    private RestService restService;

    public String getISO4217Xml(){
        ResponseEntity<String> response = restService.getRestTemplate().getForEntity(xmlURL, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RestClientException("Error: Unable to fetch ISO 4217 Xml.");
        }
    }
}
