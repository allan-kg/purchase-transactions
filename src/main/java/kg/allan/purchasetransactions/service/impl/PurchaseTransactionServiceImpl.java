package kg.allan.purchasetransactions.service.impl;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import kg.allan.purchasetransactions.dto.PurchaseTransactionDTO;
import kg.allan.purchasetransactions.dto.json.CountryTRREJson;
import kg.allan.purchasetransactions.entity.PurchaseTransactionEntity;
import kg.allan.purchasetransactions.exception.InvalidPurchaseTransactionException;
import org.springframework.stereotype.Service;
import kg.allan.purchasetransactions.service.PurchaseTransactionService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import kg.allan.purchasetransactions.repository.PurchaseTransactionRepository;
import kg.allan.purchasetransactions.service.TRREService;
import kg.allan.purchasetransactions.util.CurrencyUtil;
import org.springframework.util.StringUtils;

/**
 *
 * @author allan
 */
@Service
public class PurchaseTransactionServiceImpl implements PurchaseTransactionService {

    @Autowired
    private PurchaseTransactionRepository repository;

    @Autowired
    private TRREService trreService;

    public Optional<CountryTRREJson> findCountryTRREJsonByFirstValid(List<CountryTRREJson> countries, LocalDate maxDate) {
        LocalDate minDate = maxDate.minusMonths(6);

        return countries.stream().sorted(Collections.reverseOrder())
                .filter(c
                        -> (c.getRecordDate().isBefore(maxDate) || c.getRecordDate().isEqual(maxDate))
                && (c.getRecordDate().isAfter(minDate) || c.getRecordDate().isEqual(minDate))
                ).findFirst();
    }

    public Optional<BigDecimal> findExchangeRateByFirstValid(List<CountryTRREJson> countries, LocalDate maxDate) {
        var octj = findCountryTRREJsonByFirstValid(countries, maxDate);
        if (octj.isPresent()) {
            return Optional.of(octj.get().getExchangeRate());
        }
        return Optional.empty();
    }

    public PurchaseTransactionEntity entity(PurchaseTransactionDTO dto) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull()).setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(dto, PurchaseTransactionEntity.class);
    }

    public PurchaseTransactionDTO dto(PurchaseTransactionEntity entity) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull()).setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(entity, PurchaseTransactionDTO.class);
    }
    
    private void validateDescription(String desc) throws InvalidPurchaseTransactionException {
        if (desc == null) {
            throw new InvalidPurchaseTransactionException("Missing description.");
        }
        if (desc.length() > 50) {
            throw new InvalidPurchaseTransactionException("Description contains more than 50 characters.");
        }
    }

    private LocalDate validateAndRetrieveDate(String date) throws InvalidPurchaseTransactionException {
        if (date == null || !StringUtils.hasText(date)) {
            throw new InvalidPurchaseTransactionException("Missing date.");
        }
        try {
            LocalDate ld = LocalDate.parse(date);
            return ld;
        } catch (DateTimeException e) {
            throw new InvalidPurchaseTransactionException("Invalid date.", e);
        }

    }

    private BigDecimal validateAndRetrieveAmount(String amount) throws InvalidPurchaseTransactionException {
        if (amount == null || !StringUtils.hasText(amount)) {
            throw new InvalidPurchaseTransactionException("Missing amount.");
        }
        try {
            BigDecimal bd = new BigDecimal(amount);
            
            if(bd.compareTo(BigDecimal.ZERO) <= 0)
                throw new InvalidPurchaseTransactionException("Amount isn't positive.");
            
            return bd;
        } catch (NumberFormatException e) {
            throw new InvalidPurchaseTransactionException("Invalid amount.", e);
        }
    }
    
    

    private PurchaseTransactionEntity instantiateEntity(PurchaseTransactionDTO dto) throws InvalidPurchaseTransactionException {
        PurchaseTransactionEntity entity = new PurchaseTransactionEntity();

        validateDescription(dto.getDescription());
        entity.setDescription(dto.getDescription());

        entity.setDate(validateAndRetrieveDate(dto.getDate()));

        var amount = validateAndRetrieveAmount(dto.getAmount());
        var usdMoney = CurrencyUtil.usdRoundedMonetaryAmountOf(amount);
        entity.setAmount(usdMoney);
        
        return entity;
    }

    @Override
    public PurchaseTransactionDTO newPurchaseTransaction(PurchaseTransactionDTO newPurchaseTransaction) throws InvalidPurchaseTransactionException{
        PurchaseTransactionEntity entity = instantiateEntity(newPurchaseTransaction);
        return dto(repository.save(entity));
    }

    @Override
    public Optional<PurchaseTransactionDTO> find(Integer id) {
        var oe = repository.findById(id);
        Optional<PurchaseTransactionDTO> optdto = Optional.empty();
        if (oe.isPresent()) {
            optdto = Optional.of(dto(oe.get()));
        }
        return optdto;
    }

    @Override
    public List<PurchaseTransactionDTO> all() {
        return repository.findAllBy()
                .parallel()
                .map(e -> dto(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<PurchaseTransactionDTO> addAll(List<PurchaseTransactionDTO> listPurchaseTransactions) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
