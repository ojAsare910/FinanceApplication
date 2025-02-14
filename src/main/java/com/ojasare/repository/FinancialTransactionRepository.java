package com.ojasare.repository;

import com.ojasare.entity.FinancialTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface FinancialTransactionRepository extends JpaRepository<FinancialTransaction, Long> {
    Page<FinancialTransaction> findByTransactionDateBetweenAndUserIdAndServiceAndStatusAndReference(
            LocalDateTime dateFrom, LocalDateTime dateTo, Long userId, String service, String status, String reference, Pageable pageable);
}
