package kg.allan.purchasetransactions.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.money.MonetaryAmount;
import kg.allan.purchasetransactions.entity.converter.MonetaryAmountConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author allan
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TRANSACTION")
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(length = 50, nullable = false)
    private String description;
    
    
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime date;
    
    @Convert(converter = MonetaryAmountConverter.class)
    private MonetaryAmount amount;
    
    @Column
    private BigDecimal rate;
    
    @Convert(converter = MonetaryAmountConverter.class)
    private MonetaryAmount convertedAmount;

}