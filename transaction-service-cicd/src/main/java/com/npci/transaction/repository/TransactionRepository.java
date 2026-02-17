package com.npci.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.npci.transaction.entity.PaymentTransaction;

public interface TransactionRepository extends JpaRepository<PaymentTransaction, String> {
}
