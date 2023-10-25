package kg.allan.purchasetransactions.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import kg.allan.purchasetransactions.entity.PurchaseTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author allan
 */
@Repository
public interface PurchaseTransactionRepository extends JpaRepository<PurchaseTransactionEntity, Integer>{
    public List<PurchaseTransactionEntity> findAll();
    public Stream<PurchaseTransactionEntity> findAllBy();
}
