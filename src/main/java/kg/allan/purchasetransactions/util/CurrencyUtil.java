package kg.allan.purchasetransactions.util;

import java.math.BigDecimal;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.RoundingQueryBuilder;
import org.javamoney.moneta.Money;

/**
 *
 * @author allan
 */
public class CurrencyUtil {
    
    public static final Integer DEFAULT_SCALE = 2;
    public static final CurrencyUnit CURRENCY_UNIT_USD = Monetary.getCurrency("USD");
    public static final CurrencyUnit DEFAULT_CURRENCY_UNIT = CURRENCY_UNIT_USD;
    
    
    synchronized public static MonetaryAmount convert(MonetaryAmount from, CurrencyUnit currency, BigDecimal exchangeRate, int scale){
        var roundingQuery = RoundingQueryBuilder.of().setScale(scale).build();
        var rounding = Monetary.getRounding(roundingQuery);
        return Money.of(from.getNumber(), currency).multiply(exchangeRate).with(rounding);
    }

    synchronized public static MonetaryAmount convert(MonetaryAmount from, String currencyCode, BigDecimal exchangeRate, int scale){
        CurrencyUnit currency = Monetary.getCurrency(currencyCode);
        return convert(from, currency, exchangeRate, scale);
    }
    
    synchronized public static MonetaryAmount convert(MonetaryAmount from, CurrencyUnit currency, BigDecimal exchangeRate){
        return convert(from, currency, exchangeRate, DEFAULT_SCALE);
    }
    
    synchronized public static MonetaryAmount convert(MonetaryAmount from, String currencyCode, BigDecimal exchangeRate){
        CurrencyUnit currency = Monetary.getCurrency(currencyCode);
        return convert(from, currency, exchangeRate);
    }
    
    synchronized public static MonetaryAmount convert(MonetaryAmount from, CurrencyUnit currency){
        return convert(from, currency, BigDecimal.valueOf(1.0));
    }
    
    synchronized public static MonetaryAmount convert(MonetaryAmount from, String currencyCode){
        CurrencyUnit currency = Monetary.getCurrency(currencyCode);
        return convert(from, currency);
    }
    
    synchronized public static MonetaryAmount roundCents(MonetaryAmount money, int scale){
        var roundingQuery = RoundingQueryBuilder.of().setScale(scale).build();
        var rounding = Monetary.getRounding(roundingQuery);
        return money.with(rounding);
    }
    
    synchronized public static MonetaryAmount roundCents(MonetaryAmount money){
        return roundCents(money, DEFAULT_SCALE);
    }
    
    synchronized public static MonetaryAmount usdMonetaryAmount(BigDecimal value){
        if(value == null)
            value = BigDecimal.valueOf(0);
        return Money.of(value, CURRENCY_UNIT_USD);
    }
}
