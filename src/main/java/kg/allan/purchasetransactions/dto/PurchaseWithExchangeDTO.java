package kg.allan.purchasetransactions.dto;

import kg.allan.purchasetransactions.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;

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
@Relation(collectionRelation = "purchase_transactions_with_exchange", itemRelation = "purchase_with_exchange")
public class PurchaseWithExchangeDTO extends PurchaseTransactionDTO {
    private String rate;
    private String convertedAmount;
    private String exchangeDate;
    private String country;
    
    public PurchaseWithExchangeDTO(PurchaseTransactionDTO purchase, ExchangeDTO exchange){
        this.setDate(purchase.getDate());
        this.setAmount(purchase.getAmount());
        this.setDescription(purchase.getDescription());
        this.setId(purchase.getId());
        
        this.setRate(exchange.getRate().toString());
        this.setConvertedAmount(exchange.getAmount().toString());
        this.setExchangeDate(exchange.getDate().toString());
        this.setCountry(exchange.getCountry());
    }
}
