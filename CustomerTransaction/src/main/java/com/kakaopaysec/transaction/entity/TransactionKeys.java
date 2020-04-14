package com.kakaopaysec.transaction.entity;

import java.io.Serializable;

public class TransactionKeys implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String trDate;
	
	private long accountNo;
	
	private int trNo;

	public TransactionKeys(String trDate, long accountNo, int trNo) {
		super();
		this.trDate = trDate;
		this.accountNo = accountNo;
		this.trNo = trNo;
	}
	
	
	
}
