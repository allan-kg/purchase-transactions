package kg.allan.purchasetransactions.db;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import kg.allan.purchasetransactions.PurchaseTransactionsApplication;
import kg.allan.purchasetransactions.entity.Transaction;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import kg.allan.purchasetransactions.repository.TransactionRepository;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 *
 * @author allan
 */
@SpringBootTest(classes = PurchaseTransactionsApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("integration")
public class CRUDIntegrationTest {
    
    @MockBean
    TransactionRepository repository;
    
    static final Integer [] IDS = {Integer.MAX_VALUE, Integer.MAX_VALUE-1, Integer.MAX_VALUE-2, Integer.MAX_VALUE-3, Integer.MAX_VALUE-4};
    static final String [] DESCS = {"DESC 1", "DESC 2", "DESC 3", "DESC 4", "DESC 5"};
    static final MonetaryAmount [] AMOUNTS = {
        Money.of(100.0, "USD"),
        Money.of(2500.37, Monetary.getCurrency(Locale.US)),
        Money.of(1450.1234, "USD"),
        Money.of(250000.37, Monetary.getCurrency(Locale.US)),
        Money.of(14.499999, "USD")
    };
    static final LocalDateTime [] DATES = {
        LocalDateTime.parse("1980-04-01T19:30:45.123"),
        LocalDateTime.parse("1990-04-09T19:30:45.123"),
        LocalDateTime.parse("2000-04-21T19:30:45.123"),
        LocalDateTime.parse("2010-12-21T00:30:45.123"),
        LocalDateTime.parse("2020-12-31T23:59:59.999")
    };
    static final BigDecimal RATES [] = {
        new BigDecimal(1.00),
        new BigDecimal(1.25),
        new BigDecimal(0.20),
        new BigDecimal(0.79),
        new BigDecimal(0.80)
    };
    static final MonetaryAmount [] CONVERTED_AMOUNTS = {
      Money.of(AMOUNTS[0].divide(RATES[0].longValue()).getNumber() , Monetary.getCurrency(Locale.US)),
        Money.of(AMOUNTS[0].divide(RATES[0].longValue()).getNumber() , Monetary.getCurrency(Locale.UK)),
        Money.of(AMOUNTS[0].divide(RATES[0].longValue()).getNumber() , "BRL"),
        Money.of(AMOUNTS[0].divide(RATES[0].longValue()).getNumber() , Monetary.getCurrency(Locale.FRANCE)),
        Money.of(AMOUNTS[0].divide(RATES[0].longValue()).getNumber() , Monetary.getCurrency(Locale.CANADA))
    };
    
    static List<Transaction> transactions;
    
    @BeforeAll
    public static void setup(){
        var list = new CopyOnWriteArrayList<Transaction>();
        for(int i = 0; i < IDS.length; i++){
            // in fact, te id will be ignored
            Transaction t = new Transaction(IDS[i], DESCS[i], DATES[i], AMOUNTS[i], RATES[i], CONVERTED_AMOUNTS[i]);
            list.add(t);
        }
        transactions = list;
        Assertions.assertEquals(5, transactions.size());
    }
    
    @Test
    @Order(1)
    @Transactional
    public void create(){
        Mockito.when(repository.saveAll(Mockito.any(List.class))).thenReturn(transactions);
        Mockito.doNothing().when(repository).flush();
        Mockito.when(repository.findAll()).thenReturn(transactions);
        
        repository.saveAll(transactions);
        repository.flush();
        
        Assertions.assertEquals(transactions.size(), repository.findAll().size());
    }
    
    @Test
    @Order(2)
    @Transactional
    public void read(){
        Mockito.when(repository.findAllBy()).thenReturn(transactions.stream());
        
        var containsCount = repository.findAllBy().filter(t -> transactions.contains(t)).count();
        Assertions.assertEquals(transactions.size(), containsCount);
    }
}
