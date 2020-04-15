package com.kakaopaysec.transaction.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.kakaopaysec.transaction.entity.Account;
import com.kakaopaysec.transaction.entity.QAccount;
import com.kakaopaysec.transaction.entity.QBranch;
import com.kakaopaysec.transaction.entity.QTransaction;
import com.kakaopaysec.transaction.entity.Transaction;
import com.kakaopaysec.transaction.info.BranchInfo;
import com.kakaopaysec.transaction.info.MaxAmountDTO;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class TransactionRepositoryImpl extends QuerydslRepositorySupport{
	
	@Autowired
	private final JPAQueryFactory queryFactory;
	
	public TransactionRepositoryImpl(JPAQueryFactory queryFactory) {
		super(Transaction.class);
		this.queryFactory = queryFactory;
	}

	public Account findAccountByNumber(String i) {
		QAccount account = new QAccount("account");
		return queryFactory
                .selectFrom(account)
                .where(account.accountNo.eq(i))
                .fetchOne();
	}

	public List<MaxAmountDTO> findAllTransactionAmount() {
		QTransaction transaction = new QTransaction("transaction");
		QAccount account = new QAccount("account");
		
		return queryFactory
				.select(Projections.bean(MaxAmountDTO.class, 
						transaction.trDate, 
						transaction.trAmount.subtract(transaction.trFee).sum().as("sumAmt"), 
						transaction.accountNo,
						account.accountNm)
				)
				.from(transaction)
				.leftJoin(account)
				.on(transaction.accountNo.eq(account.accountNo))
				.where(transaction.cancleYn.eq('N'))
				.groupBy(transaction.trDate, transaction.accountNo)
				.orderBy(transaction.trDate.asc())
				.fetch();
	}

	public List<String> findAccListWithYear(String year) {
		QTransaction transaction = new QTransaction("transaction");
		return queryFactory
			   .selectDistinct(transaction.accountNo)
			   .from(transaction)
			   .where(transaction.cancleYn.eq('N'), transaction.trDate.substring(0,4).eq(year))
			   .fetch();
	}

	public List<Tuple> findNoTransactionList(List<String> accList) {
		QAccount account = new QAccount("account");
		return queryFactory
			   .select(account.accountNm.as("accNm"), account.accountNo.as("accNo"))
			   .from(account)
			   .where(account.accountNo.notIn(accList))
			   .fetch();
	}

	public List<BranchInfo> findSumAmountOfEachBranchByYear(String year) {
		QTransaction transaction = new QTransaction("transaction");
		QAccount account = new QAccount("account");
		QBranch branch = new QBranch("branch");
		
		return queryFactory
			   .select(Projections.bean(BranchInfo.class,
					   transaction.trAmount.sum().as("sumAmt"),
					   branch.brCode.as("brCode"),
					   branch.brName.as("brName")))
			   .from(transaction)
			   .leftJoin(account).on(transaction.accountNo.eq(account.accountNo))
			   .leftJoin(branch).on(account.brCode.eq(branch.brCode))
			   .where(transaction.cancleYn.eq('N'), transaction.trDate.substring(0,4).eq(year))
			   .groupBy(branch.brCode)
			   .orderBy(transaction.trAmount.sum().desc())
			   .fetch();
	}

	public BranchInfo findSumAmountOfBranchByName(String brName) {
		QTransaction transaction = new QTransaction("transaction");
		QAccount account = new QAccount("account");
		QBranch branch = new QBranch("branch");
		return queryFactory
			   .select(Projections.bean(BranchInfo.class,
					   transaction.trAmount.sum().as("sumAmt"),
					   branch.brCode.as("brCode"),
					   branch.brName.as("brName")))
			   .from(transaction)
			   .leftJoin(account).on(transaction.accountNo.eq(account.accountNo))
			   .leftJoin(branch).on(account.brCode.eq(branch.brCode))
			   .where(transaction.cancleYn.eq('N'), branch.brName.eq(brName))
			   .groupBy(branch.brCode)
			   .fetchOne();
	}
	
	
	
	
}
