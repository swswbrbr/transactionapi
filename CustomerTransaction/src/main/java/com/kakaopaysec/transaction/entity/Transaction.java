package com.kakaopaysec.transaction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;

@Entity
@IdClass(TransactionKeys.class)
public class Transaction {
	
	public Transaction() {}
	
	@Id
	@Column(name = "TRANSACTION_DATE")
	private String trDate;
	
	@Id
	@Column(name = "ACCOUNT_NO")
	@JoinColumn(name = "ACCOUNT_NO")
	private long accountNo;
	
	@Id
	@Column(name = "TRANSACTION_NO")
	private int trNo;
	
	@Column(name = "TRANSACTION_AMOUNT")
	private long trAmount;
	
	@Column(name = "TRANSACTION_FEE")
	private long trFee;
	
	@Column(name = "CANCLE_YN")
	private char cancleYn;

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public char getCancleYn() {
		return cancleYn;
	}

	public void setCancleYn(char cancleYn) {
		this.cancleYn = cancleYn;
	}

	public int getTrNo() {
		return trNo;
	}

	public void setTrNo(int trNo) {
		this.trNo = trNo;
	}

	public long getTrAmount() {
		return trAmount;
	}

	public void setTrAmount(long trAmount) {
		this.trAmount = trAmount;
	}

	public long getTrFee() {
		return trFee;
	}

	public void setTrFee(long trFee) {
		this.trFee = trFee;
	}

	public String getTrDate() {
		return trDate;
	}

	public void setTrDate(String trDate) {
		this.trDate = trDate;
	}
	
	

//	public long getId() {
//		return id;
//	}


	
}
