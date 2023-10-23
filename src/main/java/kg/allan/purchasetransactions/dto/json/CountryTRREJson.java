package kg.allan.purchasetransactions.dto.json;

import jakarta.json.bind.annotation.JsonbProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TRRE = Treasury Reporting Rates of Exchange
 * @author allan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryTRREJson {
    @JsonbProperty("record_date")
    private LocalDate recordDate;
    @JsonbProperty("country")
    private String country;
    @JsonbProperty("exchange_rate")
    private BigDecimal exchangeRate;
}
