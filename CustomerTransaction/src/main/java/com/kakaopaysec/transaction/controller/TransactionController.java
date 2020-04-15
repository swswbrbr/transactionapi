package com.kakaopaysec.transaction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kakaopaysec.transaction.entity.Account;
import com.kakaopaysec.transaction.info.BranchParam;
import com.kakaopaysec.transaction.info.BranchSumAmountResult;
import com.kakaopaysec.transaction.info.MaxResult;
import com.kakaopaysec.transaction.info.NoTransactionResult;
import com.kakaopaysec.transaction.service.TransactionService;

@RestController
@RequestMapping("transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping(value = "hello")
	public Account helloWorld() {
		return transactionService.findAccountByNumber("11111111");
	}
	
	@GetMapping(value = "sum/amount/max")
	public List<MaxResult> getMaxTransactionAmountByYear(){
		return transactionService.findMaxTransactionAmountByYear();
	}
	
	@GetMapping(value = "empty/customer")
	public List<NoTransactionResult> getCustomersWithNoTransactionByYear(){
		return transactionService.findNoTransactionCustomers();
	}
	
	@GetMapping(value = "sum/branch/year")
	public List<BranchSumAmountResult> getSumAmountOfBranchByYear(){
		return transactionService.findSumAmountOfEachBranchByYear();
	}
	
	@PostMapping(value = "sum/branch/name")
	public ResponseEntity<?> getSumAmountOfBranchByName(@RequestBody BranchParam brParam){
		return transactionService.findSumAmountOfBranchByName(brParam);
	}
	
}
