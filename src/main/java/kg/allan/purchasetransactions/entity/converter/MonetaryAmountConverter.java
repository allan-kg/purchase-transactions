package kg.allan.purchasetransactions.entity.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import javax.money.MonetaryAmount;
import org.javamoney.moneta.ToStringMonetaryAmountFormat;

/**
  * @author allan
 */
@Converter
public class MonetaryAmountConverter implements AttributeConverter<MonetaryAmount, String> {
    
    private final ToStringMonetaryAmountFormat format = ToStringMonetaryAmountFormat.of(ToStringMonetaryAmountFormat.ToStringMonetaryAmountFormatStyle.ROUNDED_MONEY);
    
    @Override
    public String convertToDatabaseColumn(MonetaryAmount ma) {
        return format.format(ma);
    }

    @Override
    public MonetaryAmount convertToEntityAttribute(String str) {
        return format.parse(str);
    }
}