package kg.allan.purchasetransactions.service;

import java.util.List;
import java.util.Optional;
import kg.allan.purchasetransactions.dto.PurchaseWithExchangeDTO;
import kg.allan.purchasetransactions.dto.PurchaseTransactionDTO;
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
    PurchaseTransactionDTO newPurchaseTransaction(PurchaseTransactionDTO newPurchaseTransaction) throws InvalidPurchaseTransactionException;
    Optional<PurchaseTransactionDTO> find(Integer id);
    Optional<PurchaseWithExchangeDTO> convert(Integer id, String target) throws FetchFailedException, JsonParseException, ElementNotFoundException, ConversionFailedException;
    List<PurchaseTransactionDTO> all();
    List<PurchaseWithExchangeDTO> convertAll(String target) throws FetchFailedException, JsonParseException, ElementNotFoundException, ConversionFailedException;
    List<PurchaseTransactionDTO> addAll(List<PurchaseTransactionDTO> listPurchaseTransactions);
}
