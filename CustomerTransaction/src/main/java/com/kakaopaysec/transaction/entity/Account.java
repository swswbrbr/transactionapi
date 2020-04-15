package com.kakaopaysec.transaction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Account {
	
	public Account() {}
	
	@Id
	@Column(name = "ACCOUNT_NO")
	private String accountNo;
	
	@Column(name = "ACCOUNT_NAME")
	private String accountNm;
	
	@Column(name = "BRANCH_CODE")
	@JoinColumn(name = "BRANCH_CODE")
	private char brCode;
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountNm() {
		return accountNm;
	}
	public void setAccountNm(String accountNm) {
		this.accountNm = accountNm;
	}
	public char getBrCode() {
		return brCode;
	}
	public void setBrCode(char brCode) {
		this.brCode = brCode;
	}
	
	
	
}
