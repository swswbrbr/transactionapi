package com.kakaopaysec.transaction.info;

import java.util.List;

public class BranchSumAmountResult {
	
	private int year;
	private List<BranchInfo> dataList;

	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public List<BranchInfo> getDataList() {
		return dataList;
	}
	public void setDataList(List<BranchInfo> dataList) {
		this.dataList = dataList;
	}
	
}
