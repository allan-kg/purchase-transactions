package kg.allan.purchasetransactions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object that includes original data from the purchase as well as
 * the currency exchange data.
 * <br>
 * I have intentionally chosen not to implement Serializable as there has been
 * no requirement to serialize this object in any format other than JSON for the
 * endpoints.
 *
 * @author allan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseWithExchangeDTO extends PurchaseTransactionDTO {
    private String rate;

    private String convertedAmount;
    
    public PurchaseWithExchangeDTO(PurchaseTransactionDTO purchase, ExchangeDTO exchange){
        this.setDate(purchase.getDate());
        this.setAmount(purchase.getAmount());
        this.setDescription(purchase.getDescription());
        this.setId(purchase.getId());
        
        this.setRate(exchange.getRate().toString());
        this.setConvertedAmount(exchange.getAmount().toString());
    }
}
