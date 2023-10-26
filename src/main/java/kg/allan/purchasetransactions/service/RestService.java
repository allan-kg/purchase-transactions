/*
 * RestService
 *
 * v1.0
 *
 * 2023
 *
 * Author: Allan Krama Guimarães
 */

package kg.allan.purchasetransactions.service;

import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Allan Krama Guimarães
 */
public interface RestService {
    public RestTemplate getRestTemplate();
}
