package kg.allan.purchasetransactions.service.impl;

import jakarta.json.Json;
import jakarta.json.JsonReader;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import kg.allan.purchasetransactions.dto.json.CountryTRREJson;
import kg.allan.purchasetransactions.dto.json.DataTRREJson;
import kg.allan.purchasetransactions.exception.GetRequestException;
import kg.allan.purchasetransactions.exception.JsonParseException;
import kg.allan.purchasetransactions.service.RestService;
import kg.allan.purchasetransactions.service.TRREService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;

/**
 *
 * @author allan
 */
@Service
public class TRREServiceImpl implements TRREService {

    @Value("${trre.endpoint}")
    private String jsonEndpoint;

    @Autowired
    private RestService restService;

    @Value("${trre.parameters.fields_parameter}")
    private String fieldsParameter;

    @Value("${trre.parameters.filter.eq}")
    private String equals;
    @Value("${trre.parameters.filter.le}")
    private String lessThanOrEquals;
    @Value("${trre.parameters.filter.ge}")
    private String greaterThanOrEquals;

    @Value("${trre.parameters.filters}")
    private String filtersParamPrefix;
    @Value("${trre.parameters.filter.country}")
    private String filterCountry;
    @Value("${trre.parameters.filter.record_date}")
    private String filterRecordDate;

    String buildParametersSingleRate(String country, String date) {
        String params = "";
        boolean useComma = false;
        if (date != null && StringUtils.hasText(date)) {
            useComma = true;
            params += filterRecordDate;
            params += equals;
            params += date;
        }
        if (country != null && StringUtils.hasText(country)) {
            if (useComma) {
                params += ",";
            }
            params += filterCountry;
            params += equals;
            params += country;
        }
        if (StringUtils.hasText(params)) {
            params = filtersParamPrefix + params;
        }
        return params;
    }

    String buildParametersForPeriod(String country, String dateMin, String dateMax) {
        String params = "";
        boolean useComma = false;

        if (country != null && StringUtils.hasText(country)) {
            useComma = true;
            params += filterCountry;
            params += equals;
            params += country;
        }

        if (dateMin != null && StringUtils.hasText(dateMin)) {
            if (useComma) {
                params += ",";
            }
            useComma = true;
            params += filterRecordDate;
            params += greaterThanOrEquals;
            params += dateMin;
        }

        if (dateMax != null && StringUtils.hasText(dateMax)) {
            if (useComma) {
                params += ",";
            }
            useComma = true;
            params += filterRecordDate;
            params += lessThanOrEquals;
            params += dateMax;
        }
        if (StringUtils.hasText(params)) {
            params = filtersParamPrefix + params;
        }
        return params;
    }

    public Optional<CountryTRREJson> fetchRateByDate(String country, String date) throws GetRequestException, JsonParseException {
        String uri = jsonEndpoint + "?" + fieldsParameter;
        String params = buildParametersSingleRate(country, date);
        if (StringUtils.hasText(params)) {
            uri = uri + '&' + params;
        }

        ResponseEntity<String> response = restService.getRestTemplate().getForEntity(uri, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            try {
                Jsonb jsonb = JsonbBuilder.create();
                var countryObj = jsonb.fromJson(response.getBody(), DataTRREJson.class);
                jsonb.close();
                return countryObj.getData().stream().findFirst();
            } catch (Exception ex) {
                throw new JsonParseException("Error: Unable to parse json.", ex);
            }
        } else {
            throw new GetRequestException("Error: Unable to fetch json.");
        }
    }

    public List<CountryTRREJson> fetchRatesByDateRange(String country, String dateMin, String dateMax) throws GetRequestException, JsonParseException {
        String uri = jsonEndpoint + "?" + fieldsParameter;
        String params = buildParametersForPeriod(country, dateMin, dateMax);
        if (StringUtils.hasText(params)) {
            uri = uri + '&' + params;
        }

        ResponseEntity<String> response = restService.getRestTemplate().getForEntity(uri, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            try {
                Jsonb jsonb = JsonbBuilder.create();
                var data = (DataTRREJson) jsonb.fromJson(response.getBody(), DataTRREJson.class);
                jsonb.close();
                return data.getData();
            } catch (Exception ex) {
                throw new JsonParseException("Error: Unable to parse json.", ex);
            }
        } else {
            throw new GetRequestException("Error: Unable to fetch json.");
        }
    }

    @Override
    public String formatDate(LocalDate maxDate) throws GetRequestException, JsonParseException {
        return maxDate.getYear() + "-" + String.format("%02d", maxDate.getMonthValue()) + "-" + String.format("%02d", maxDate.getDayOfMonth());
    }

}
