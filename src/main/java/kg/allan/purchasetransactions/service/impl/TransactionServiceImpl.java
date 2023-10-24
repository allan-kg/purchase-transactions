package kg.allan.purchasetransactions.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import kg.allan.purchasetransactions.dto.json.CountryTRREJson;
import kg.allan.purchasetransactions.service.TransactionService;
import org.springframework.stereotype.Service;

/**
 *
 * @author allan
 */
@Service
public class TransactionServiceImpl implements TransactionService{
    
    
    public Optional<CountryTRREJson> findCountryTRREJsonByFirstValid(List<CountryTRREJson> countries, LocalDate maxDate){
        LocalDate minDate = maxDate.minusMonths(6);
        
        return countries.stream().sorted(Collections.reverseOrder())
                .filter(c-> 
                        (c.getRecordDate().isBefore(maxDate) || c.getRecordDate().isEqual(maxDate))
                        &&
                        (c.getRecordDate().isAfter(minDate) || c.getRecordDate().isEqual(minDate))
                ).findFirst();
    }
    
    public Optional<BigDecimal> findExchangeRateByFirstValid(List<CountryTRREJson> countries, LocalDate maxDate){
        var octj = findCountryTRREJsonByFirstValid(countries, maxDate);
        if(octj.isPresent()){
            return Optional.of(octj.get().getExchangeRate());
        }
        return Optional.empty();
    }
    
            
}
