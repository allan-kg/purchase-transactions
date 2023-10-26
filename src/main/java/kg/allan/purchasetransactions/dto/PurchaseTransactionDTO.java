/*
 * PurchaseTransactionDTO
 *
 * v1.0
 *
 * 2023
 *
 * Author: Allan Krama Guimarães
 */

package kg.allan.purchasetransactions.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;

/**
 * Data Transfer Object for {@link PurchaseTransactionEntity}.
 * <br>
 * I have intentionally chosen not to implement Serializable as there has been
 * no requirement to serialize this object in any format other than JSON for
 * the endpoints.
 * @author Allan Krama Guimarães
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Relation(collectionRelation = "purchase_transactions", itemRelation = "purchase_transaction")
public class PurchaseTransactionDTO {
    private Integer id;
    
    private String description;
    
    private String date;
    
    private String amount;
   
}
