package kg.allan.purchasetransactions.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.money.MonetaryAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author allan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeDTO {
    private MonetaryAmount amount;
    private BigDecimal rate;
    private LocalDate date;
}
