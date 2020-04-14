package com.kakaopaysec.transaction.service;

import java.util.List;

import com.kakaopaysec.transaction.entity.Account;

public interface TransactionService {

	List<Account> findAccountByNumber(long l);
	
}
