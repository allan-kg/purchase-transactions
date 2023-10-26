package kg.allan.purchasetransactions.service;

import java.util.List;
import java.util.Optional;
import kg.allan.purchasetransactions.dto.PurchaseWithExchangeDTO;
import kg.allan.purchasetransactions.dto.PurchaseTransactionDTO;
import kg.allan.purchasetransactions.entity.PurchaseTransactionEntity;
import kg.allan.purchasetransactions.exception.ConversionFailedException;
import kg.allan.purchasetransactions.exception.ElementNotFoundException;
import kg.allan.purchasetransactions.exception.FetchFailedException;
import kg.allan.purchasetransactions.exception.InvalidPurchaseTransactionException;
import kg.allan.purchasetransactions.exception.JsonParseException;

/**
 *
 * @author allan
 */
public interface PurchaseTransactionService {
    public PurchaseTransactionDTO newPurchaseTransaction(PurchaseTransactionDTO newPurchaseTransaction) throws InvalidPurchaseTransactionException;
    public Optional<PurchaseTransactionDTO> find(Integer id);
    public PurchaseWithExchangeDTO convert(PurchaseTransactionEntity entity, String target) throws FetchFailedException, JsonParseException, ElementNotFoundException, ConversionFailedException;
    public Optional<PurchaseWithExchangeDTO> convert(Integer id, String target) throws FetchFailedException, JsonParseException, ElementNotFoundException, ConversionFailedException;
    public List<PurchaseTransactionDTO> all();
    public List<PurchaseWithExchangeDTO> convertAll(String target) throws FetchFailedException, JsonParseException, ElementNotFoundException, ConversionFailedException;
    public List<PurchaseTransactionDTO> addAll(List<PurchaseTransactionDTO> listPurchaseTransactions);
}
