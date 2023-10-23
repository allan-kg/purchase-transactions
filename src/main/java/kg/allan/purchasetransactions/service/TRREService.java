package kg.allan.purchasetransactions.service;

import java.util.List;
import java.util.Optional;
import kg.allan.purchasetransactions.dto.json.CountryTRREJson;

/**
 *
 * @author allan
 */
public interface TRREService {
    public Optional<CountryTRREJson> retrieveCountryJson(String country,String date) throws Exception;
    public List<CountryTRREJson> retrieveCountryRatesJson(String country, String dateMin, String dateMax) throws Exception;
}
