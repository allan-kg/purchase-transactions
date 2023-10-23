package kg.allan.purchasetransactions.util;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.RoundingQueryBuilder;
import javax.money.convert.MonetaryConversions;

/**
 *
 * @author allan
 */
public class CurrencyUtil {
    
    synchronized public static MonetaryAmount convert(MonetaryAmount from, CurrencyUnit currency){
        var conversion = MonetaryConversions.getConversion(currency);
        return from.with(conversion);
    }
    
    synchronized public static MonetaryAmount convert(MonetaryAmount from, String currencyCode){
        var currency = Monetary.getCurrency(currencyCode);
        return convert(from, currency);
    }
    
    synchronized public static MonetaryAmount roundCents(MonetaryAmount money, int scale){
        var roundingQuery = RoundingQueryBuilder.of().setScale(scale).build();
        var rounding = Monetary.getRounding(roundingQuery);
        return money.with(rounding);
    }
    
    synchronized public static MonetaryAmount roundCents(MonetaryAmount money){
        return roundCents(money, 2);
    }
    
}
