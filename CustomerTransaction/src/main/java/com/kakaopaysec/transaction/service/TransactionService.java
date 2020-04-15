package com.kakaopaysec.transaction.service;

import java.util.List;

import com.kakaopaysec.transaction.entity.Account;
import com.kakaopaysec.transaction.info.MaxResult;
import com.kakaopaysec.transaction.info.NoTransactionResult;

public interface TransactionService {

	Account findAccountByNumber(String l);

	List<MaxResult> findMaxTransactionAmountByYear();

	List<NoTransactionResult> findNoTransactionCustomers();
	
}
