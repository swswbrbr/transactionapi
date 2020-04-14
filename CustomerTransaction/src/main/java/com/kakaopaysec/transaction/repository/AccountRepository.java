package com.kakaopaysec.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kakaopaysec.transaction.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
