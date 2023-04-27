package com.piggery.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.piggery.models.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

}
