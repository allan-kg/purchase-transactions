package kg.allan.purchasetransactions.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import kg.allan.purchasetransactions.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author allan
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
    public List<Transaction> findAll();
    public Stream<Transaction> findAllBy();
}
