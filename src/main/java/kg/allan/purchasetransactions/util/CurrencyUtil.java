package kg.allan.purchasetransactions.util;

import java.math.BigDecimal;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.MonetaryRounding;
import javax.money.RoundingQueryBuilder;
import lombok.extern.log4j.Log4j2;
import org.javamoney.moneta.Money;

/**
 *
 * @author allan
 */
public class CurrencyUtil {

    public static final BigDecimal DEFAULT_EXCHANGE_RATE = BigDecimal.valueOf(1.0F);
    public static final Integer DEFAULT_SCALE = 2;
    public static final CurrencyUnit CURRENCY_UNIT_USD = Monetary.getCurrency("USD");
    public static final CurrencyUnit DEFAULT_CURRENCY_UNIT = CURRENCY_UNIT_USD;
    public static final MonetaryRounding DEFAULT_ROUNDING;
    
    static{
        var roundingQuery = RoundingQueryBuilder.of().setScale(DEFAULT_SCALE).setCurrency(DEFAULT_CURRENCY_UNIT).build();
        DEFAULT_ROUNDING = Monetary.getRounding(roundingQuery);
    }
    

    public static MonetaryAmount convertRounded(MonetaryAmount from, CurrencyUnit currency, BigDecimal exchangeRate, int scale) {
        var roundingQuery = RoundingQueryBuilder.of().setScale(scale).setCurrency(currency).build();
        var rounding = Monetary.getRounding(roundingQuery);
        return Money.of(from.getNumber(), currency).multiply(exchangeRate).with(rounding);
    }

    public static MonetaryAmount convertRounded(MonetaryAmount from, String currencyCode, BigDecimal exchangeRate, int scale) {
        CurrencyUnit currency = Monetary.getCurrency(currencyCode);
        return convertRounded(from, currency, exchangeRate, scale);
    }

    public static MonetaryAmount convertRounded(MonetaryAmount from, CurrencyUnit currency, BigDecimal exchangeRate) {
        return Money.of(from.getNumber(), currency).multiply(exchangeRate).with(DEFAULT_ROUNDING);
    }

    public static MonetaryAmount convertRounded(MonetaryAmount from, String currencyCode, BigDecimal exchangeRate) {
        CurrencyUnit currency = Monetary.getCurrency(currencyCode);
        return CurrencyUtil.convertRounded(from, currency, exchangeRate);
    }

    public static MonetaryAmount convertRounded(MonetaryAmount from, CurrencyUnit currency) {
        return CurrencyUtil.convertRounded(from, currency, DEFAULT_EXCHANGE_RATE);
    }

    public static MonetaryAmount convertRounded(MonetaryAmount from, String currencyCode) {
        CurrencyUnit currency = Monetary.getCurrency(currencyCode);
        return CurrencyUtil.convertRounded(from, currency);
    }
    
    public static MonetaryAmount roundCents(MonetaryAmount money, int scale) {
        var roundingQuery = RoundingQueryBuilder.of().setScale(scale).setCurrency(DEFAULT_CURRENCY_UNIT).build();
        var rounding = Monetary.getRounding(roundingQuery);
        return money.with(rounding);
    }

    public static MonetaryAmount roundCents(MonetaryAmount money) {
        return roundCents(money, DEFAULT_SCALE);
    }

    public static MonetaryAmount usdMonetaryAmountOf(BigDecimal value) {
        return Money.of(value, CURRENCY_UNIT_USD);
    }
    
    public static MonetaryAmount usdRoundedMonetaryAmountOf(BigDecimal value) {
        return roundCents(Money.of(value, CURRENCY_UNIT_USD));
    }
}
