package kg.allan.purchasetransactions.service;

import java.util.List;
import java.util.Optional;
import kg.allan.purchasetransactions.dto.json.CountryTRREJson;
import kg.allan.purchasetransactions.exception.GetRequestException;
import kg.allan.purchasetransactions.exception.JsonParseException;

/**
 *
 * @author allan
 */
public interface TRREService {
    public Optional<CountryTRREJson> retrieveCountryJson(String country,String date) throws GetRequestException, JsonParseException;
    public List<CountryTRREJson> retrieveCountryRatesJson(String country, String dateMin, String dateMax) throws GetRequestException, JsonParseException;
}
