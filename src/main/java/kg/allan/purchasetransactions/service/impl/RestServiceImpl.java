/*
 * RestServiceImpl
 *
 * v1.0
 *
 * 2023
 *
 * Author: Allan Krama Guimarães
 */

package kg.allan.purchasetransactions.service.impl;

import kg.allan.purchasetransactions.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Allan Krama Guimarães
 */
@Service
public class RestServiceImpl implements RestService {

    private RestTemplate restTemplate;

    @Autowired
    public void restTemplate(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
