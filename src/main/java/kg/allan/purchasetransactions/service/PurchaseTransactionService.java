package kg.allan.purchasetransactions.service;

import java.util.List;
import java.util.Optional;
import kg.allan.purchasetransactions.dto.PurchaseTransactionDTO;
import kg.allan.purchasetransactions.exception.InvalidPurchaseTransactionException;

/**
 *
 * @author allan
 */
public interface PurchaseTransactionService {
    PurchaseTransactionDTO newPurchaseTransaction(PurchaseTransactionDTO newPurchaseTransaction) throws InvalidPurchaseTransactionException;
    Optional<PurchaseTransactionDTO> find(Integer id);
    List<PurchaseTransactionDTO> all();
    List<PurchaseTransactionDTO> addAll(List<PurchaseTransactionDTO> listPurchaseTransactions);
}
