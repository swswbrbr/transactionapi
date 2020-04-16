package com.kakaopaysec.transaction;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.kakaopaysec.transaction.info.BranchParam;
import com.kakaopaysec.transaction.info.BranchSumAmountResult;
import com.kakaopaysec.transaction.info.MaxResult;
import com.kakaopaysec.transaction.info.NoTransactionResult;
import com.kakaopaysec.transaction.service.TransactionService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ControllerTests {

	@Autowired 
	private TransactionService transactionService;
	
	@Test
	public void getSumAmountMaxTest() {
		List<MaxResult> resultList = transactionService.findMaxTransactionAmountByYear();
		
		assertThat(resultList.get(0).getSumAmt())
					.isNotNull()
					.isInstanceOf(Long.class)
					.isEqualTo(28992000L);
					
	}
	
	@Test
	public void getCustomersWithNoTransactionByYearTest() {
		List<NoTransactionResult> resultList = transactionService.findNoTransactionCustomers();
		
		assertThat(resultList.get(0).getAccNm())
					.isNotNull()
					.isInstanceOf(String.class)
					.isEqualTo("사라");
	}
	
	@Test
	public void getSumAmountOfBranchByYearTest() {
		List<BranchSumAmountResult> resultList = transactionService.findSumAmountOfEachBranchByYear();
		
		assertThat(resultList.get(0).getDataList().get(0).getSumAmt())
					.isNotNull()
					.isInstanceOf(Long.class)
					.isEqualTo(38500000L);
	}
	
	@Test
	public void getSumAmountOfBranchByNameTest() {
		BranchParam brParam = new BranchParam();
		brParam.setBrName("분당점");
		
		ResponseEntity<?> responseEntity = transactionService.findSumAmountOfBranchByName(brParam);

		assertThat(responseEntity.getStatusCode())
			.isInstanceOf(HttpStatus.class)
			.isEqualByComparingTo(HttpStatus.NOT_FOUND);
	}
	
	
	
}
