package kg.allan.purchasetransactions.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import kg.allan.purchasetransactions.dto.json.CountryTRREJson;
import kg.allan.purchasetransactions.exception.FetchFailedException;
import kg.allan.purchasetransactions.exception.JsonParseException;

/**
 * Valid date format : yyyy-MM-dd
 * @author allan
 */
public interface TRREService {
    public Optional<CountryTRREJson> fetchRateByDate(String country,String date) throws FetchFailedException, JsonParseException;
    public String formatDate(LocalDate maxDate);
    public List<CountryTRREJson> fetchRatesByDateRange(String country, String dateMin, String dateMax) throws FetchFailedException, JsonParseException;
    public Optional<CountryTRREJson> fetchClosestToMaxDate(String country, String dateMin, String dateMax) throws FetchFailedException, JsonParseException;
}
