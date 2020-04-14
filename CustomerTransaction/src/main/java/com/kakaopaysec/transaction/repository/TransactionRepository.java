package com.kakaopaysec.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kakaopaysec.transaction.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
