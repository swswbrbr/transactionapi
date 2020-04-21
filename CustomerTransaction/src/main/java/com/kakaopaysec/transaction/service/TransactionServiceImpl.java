package com.kakaopaysec.transaction.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kakaopaysec.transaction.entity.Account;
import com.kakaopaysec.transaction.info.BranchInfo;
import com.kakaopaysec.transaction.info.BranchParam;
import com.kakaopaysec.transaction.info.BranchSumAmountResult;
import com.kakaopaysec.transaction.info.KakaoErrorCode;
import com.kakaopaysec.transaction.info.MaxAmountDTO;
import com.kakaopaysec.transaction.info.MaxResult;
import com.kakaopaysec.transaction.info.NoTransactionResult;
import com.kakaopaysec.transaction.repository.TransactionRepositoryImpl;
import com.querydsl.core.Tuple;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private TransactionRepositoryImpl transactionRepository;

	@Override
	public Account findAccountByNumber(String i) {
		return transactionRepository.findAccountByNumber(i);
	}

	@Override
	public List<MaxResult> findMaxTransactionAmountByYear() {
		List<MaxAmountDTO> sumAmtList = transactionRepository.findAllTransactionAmount();
		List<MaxResult> resultList = new ArrayList<MaxResult>();
		
		for(int i = 2018; i < 2020; i++) {
			final int j = i;
			Map<String, Long> amtMap = new HashMap<String, Long>();
			
			sumAmtList.stream()
					.filter(t -> t.getTrDate().startsWith(String.valueOf(j)))
					.collect(Collectors.groupingBy(MaxAmountDTO::getAccountNo))
					.entrySet().stream()
					.forEach(k -> 
						amtMap.put(k.getKey(), k.getValue().stream().collect(Collectors.summingLong(MaxAmountDTO::getSumAmt)))
					);
					
			Entry<String, Long> maxEntry = amtMap.entrySet().stream().max(Map.Entry.comparingByValue()).get();
			
			String _accNm = transactionRepository.findAccountByNumber(maxEntry.getKey()).getAccountNm();
			
			MaxResult result = new MaxResult();
			result.setAccNo(maxEntry.getKey());
			result.setName(_accNm);
			result.setSumAmt(maxEntry.getValue());
			result.setYear(i);
			
			resultList.add(result);
		}
		
		return resultList;
	}

	@Override
	public List<NoTransactionResult> findNoTransactionCustomers() {
		List<NoTransactionResult> resultList = new ArrayList<NoTransactionResult>();
		
		for(int i = 2018; i < 2020; i++) {
			List<String> accList = transactionRepository.findAccListWithYear(String.valueOf(i));
			List<Tuple> noTransactionAccList = transactionRepository.findNoTransactionList(accList);
			
			for(Tuple t : noTransactionAccList) {
				NoTransactionResult result = new NoTransactionResult();
				result.setAccNm(t.get(0, String.class));
				result.setAccNo(t.get(1, String.class));
				result.setYear(i);
				resultList.add(result);
			}
		}
		
		return resultList;
	}

	@Override
	public List<BranchSumAmountResult> findSumAmountOfEachBranchByYear() {
		List<BranchSumAmountResult> resultList = new ArrayList<BranchSumAmountResult>();
		
		for(int i = 2018; i < 2021; i++) {
			List<BranchInfo> dataList = transactionRepository.findSumAmountOfEachBranchByYear(String.valueOf(i));
			
			BranchSumAmountResult result = new BranchSumAmountResult();
			result.setYear(i);
			result.setDataList(dataList);
			resultList.add(result);
			
		}
		
		return resultList;
	}

	@Override
	public ResponseEntity<?> findSumAmountOfBranchByName(BranchParam brParam) {
		BranchInfo branch = transactionRepository.findSumAmountOfBranchByName(brParam.getBrName());
		
		if("분당점".equals(brParam.getBrName())) {
			return new ResponseEntity<>(new KakaoErrorCode("404", "br code not found error"), HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(branch, HttpStatus.OK);
		}
		
	
	}
	
}
