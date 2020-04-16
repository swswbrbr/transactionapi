package com.kakaopaysec.transaction;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kakaopaysec.transaction.info.BranchInfo;
import com.kakaopaysec.transaction.info.MaxAmountDTO;
import com.kakaopaysec.transaction.repository.TransactionRepositoryImpl;
import com.querydsl.core.Tuple;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class RepositoryTests {
	
	@Autowired
	private TransactionRepositoryImpl transactionRepository;
	
	@Test
	public void findTransactionAmountList() {
		List<MaxAmountDTO> sumAmtList = transactionRepository.findAllTransactionAmount();
		
		assertThat(sumAmtList.get(0))
					.isNotNull()
					.isInstanceOf(MaxAmountDTO.class);
		
		assertThat(sumAmtList.get(0).getSumAmt())
					.isEqualTo(1000000L)
					.isNotZero();
					
	}
	
	@Test
	public void findAccListWithYearTest() {
		List<String> accList = transactionRepository.findAccListWithYear("2018");
		
		assertThat(accList.get(0))
					.isNotNull()
					.isNotEmpty()
					.isExactlyInstanceOf(String.class);
		
	}
	
	@Test
	public void findNoTransactionCustomersTest() {
		List<String> accList = transactionRepository.findAccListWithYear("2019");
		List<Tuple> noTransactionAccList = transactionRepository.findNoTransactionList(accList);
		
		assertThat(noTransactionAccList.get(0))
					.isNotNull()
					.isInstanceOf(Tuple.class)
					.isNotIn(accList);
	}
	
	@Test
	public void findSumAmountOfEachBranchByYearTest() {
		List<BranchInfo> dataList = transactionRepository.findSumAmountOfEachBranchByYear("2018");
		
		assertThat(dataList.get(0))
					.isNotNull()
					.isInstanceOf(BranchInfo.class);
		
		assertThat(dataList.get(0).getSumAmt())
					.isNotNull()
					.isInstanceOf(Long.class)
					.isEqualByComparingTo(38500000L);
					
	}
	
	@Test
	public void findSumAmountOfBranchByNameTest() {
		BranchInfo branch = transactionRepository.findSumAmountOfBranchByName("분당점");
		
		assertThat(branch.getBrCode())
					.isNotNull()
					.isInstanceOf(Character.class)
					.isEqualTo('B');
		
		assertThat(branch)
					.isNotNull()
					.isInstanceOf(BranchInfo.class);
					
	}
	
		
	
}
