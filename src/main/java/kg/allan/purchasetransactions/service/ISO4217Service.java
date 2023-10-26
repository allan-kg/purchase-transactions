/*
 * ISO4217Service
 *
 * v1.0
 *
 * 2023
 *
 * Author: Allan Krama Guimarães
 */

package kg.allan.purchasetransactions.service;

import java.util.List;
import kg.allan.purchasetransactions.dto.xml.CountryISO4217Xml;
import kg.allan.purchasetransactions.exception.ElementNotFoundException;
import kg.allan.purchasetransactions.exception.FetchFailedException;
import kg.allan.purchasetransactions.exception.XmlParseException;

/**
 *
 * @author Allan Krama Guimarães
 */
public interface ISO4217Service {
    public List<CountryISO4217Xml> getCountries();
    public String currencyCodeOf(String country) throws ElementNotFoundException;
}
