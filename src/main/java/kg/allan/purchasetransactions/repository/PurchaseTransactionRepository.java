/*
 * PurchaseTransactionRepository
 *
 * v1.0
 *
 * 2023
 *
 * Author: Allan Krama Guimarães
 */

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
 * @author Allan Krama Guimarães
 */
@Repository
public interface PurchaseTransactionRepository extends JpaRepository<PurchaseTransactionEntity, Integer>{
    public List<PurchaseTransactionEntity> findAll();
    public Stream<PurchaseTransactionEntity> findAllBy();
}
