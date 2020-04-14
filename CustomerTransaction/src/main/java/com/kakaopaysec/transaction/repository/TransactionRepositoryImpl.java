package com.kakaopaysec.transaction.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.kakaopaysec.transaction.entity.Account;
import com.kakaopaysec.transaction.entity.QAccount;
import com.kakaopaysec.transaction.entity.Transaction;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class TransactionRepositoryImpl extends QuerydslRepositorySupport{
	
	@Autowired
	private final JPAQueryFactory queryFactory;
	
	public TransactionRepositoryImpl(JPAQueryFactory queryFactory) {
		super(Transaction.class);
		this.queryFactory = queryFactory;
	}

	public List<Account> findAccountByNumber(long i) {
		QAccount account = new QAccount("account");
		return queryFactory
                .selectFrom(account)
                .where(account.accountNo.eq(i))
                .fetch();
	}
	
	
	
	
}
