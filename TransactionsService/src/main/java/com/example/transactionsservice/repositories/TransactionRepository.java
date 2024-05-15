package com.example.transactionsservice.repositories;

import com.example.transactionsservice.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t JOIN Client ON t.txnClientId = (SELECT c FROM Client c WHERE c.id = :id) WHERE t.limitExcd = true")
    List<Transaction> findAllByLimitExceeded(Long id);
}
