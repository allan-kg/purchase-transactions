package kg.allan.purchasetransactions.service;

import java.util.List;
import kg.allan.purchasetransactions.dto.xml.CountryISO4217Xml;
import kg.allan.purchasetransactions.exception.GetRequestException;
import kg.allan.purchasetransactions.exception.XmlParseException;

/**
 *
 * @author allan
 */
public interface ISO4217Service {
    public String retrieveISO4217XmlContent() throws GetRequestException;
    public List<CountryISO4217Xml> retrieveCountryISO4217XmlList() throws XmlParseException, GetRequestException;
}
