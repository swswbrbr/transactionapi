package com.kakaopaysec.transaction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kakaopaysec.transaction.entity.Account;
import com.kakaopaysec.transaction.info.MaxResult;
import com.kakaopaysec.transaction.info.NoTransactionResult;
import com.kakaopaysec.transaction.service.TransactionService;

@RestController
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public Account helloWorld() {
		return transactionService.findAccountByNumber("11111111");
	}
	
	@RequestMapping(value = "/maxamount", method = RequestMethod.GET)
	public List<MaxResult> getMaxTransactionAmountByYear(){
		return transactionService.findMaxTransactionAmountByYear();
	}
	
	@RequestMapping(value = "/notransaction", method = RequestMethod.GET)
	public List<NoTransactionResult> getCustomersWithNoTransactionByYear(){
		return transactionService.findNoTransactionCustomers();
	}
	
}
