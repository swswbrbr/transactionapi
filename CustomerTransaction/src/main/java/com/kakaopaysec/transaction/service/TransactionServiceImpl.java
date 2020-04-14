package com.kakaopaysec.transaction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kakaopaysec.transaction.entity.Account;
import com.kakaopaysec.transaction.repository.TransactionRepositoryImpl;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private TransactionRepositoryImpl repository;

	@Override
	public List<Account> findAccountByNumber(long i) {
		return repository.findAccountByNumber(i);
	}
	
}
