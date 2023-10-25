package kg.allan.purchasetransactions.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import kg.allan.purchasetransactions.dto.json.CountryTRREJson;
import kg.allan.purchasetransactions.exception.GetRequestException;
import kg.allan.purchasetransactions.exception.JsonParseException;

/**
 * Valid date format : yyyy-MM-dd
 * @author allan
 */
public interface TRREService {
    public Optional<CountryTRREJson> fetchRateByDate(String country,String date) throws GetRequestException, JsonParseException;
    public String formatDate(LocalDate maxDate) throws GetRequestException, JsonParseException;
    public List<CountryTRREJson> fetchRatesByDateRange(String country, String dateMin, String dateMax) throws GetRequestException, JsonParseException;
}
