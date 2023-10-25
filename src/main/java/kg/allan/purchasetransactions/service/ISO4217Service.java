package kg.allan.purchasetransactions.service;

import java.util.List;
import kg.allan.purchasetransactions.dto.xml.CountryISO4217Xml;
import kg.allan.purchasetransactions.exception.ElementNotFoundException;
import kg.allan.purchasetransactions.exception.GetRequestException;
import kg.allan.purchasetransactions.exception.XmlParseException;

/**
 *
 * @author allan
 */
public interface ISO4217Service {
    public List<CountryISO4217Xml> getCountries();
    public String countryCode(String name) throws ElementNotFoundException;
    public String countryName(String code) throws ElementNotFoundException;
}
