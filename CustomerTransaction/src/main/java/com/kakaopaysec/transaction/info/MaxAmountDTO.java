package com.kakaopaysec.transaction.info;

public class MaxAmountDTO implements Comparable<MaxAmountDTO>{
	
	private String trDate;
	private long sumAmt;
	private String accountNo;
	private String accountNm;
	
	public String getTrDate() {
		return trDate;
	}
	public void setTrDate(String trDate) {
		this.trDate = trDate;
	}
	public long getSumAmt() {
		return sumAmt;
	}
	public void setSumAmt(long sumAmt) {
		this.sumAmt = sumAmt;
	}
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
	@Override
	public int compareTo(MaxAmountDTO o) {
		return Long.compare(this.sumAmt, o.getSumAmt());
	}
	
	
	
}
