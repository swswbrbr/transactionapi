package com.kakaopaysec.transaction.info;


public class BranchInfo implements Comparable<BranchInfo>{
	private String brName;
	private char brCode;
	private long sumAmt;
	
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public char getBrCode() {
		return brCode;
	}
	public void setBrCode(char brCode) {
		this.brCode = brCode;
	}
	public long getSumAmt() {
		return sumAmt;
	}
	public void setSumAmt(long sumAmt) {
		this.sumAmt = sumAmt;
	}
	@Override
	public int compareTo(BranchInfo o) {
		return Long.compare(o.sumAmt, this.sumAmt);
	}
}
