package com.kakaopaysec.transaction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Branch {
	
	public Branch() {}
	
//	@Id
//	@GeneratedValue
//	@Column(name = "BRANCH_ID")
//	private long id;
	
	@Id
	@Column(name = "BRANCH_CODE")
	private char brCode;
	
	@Column(name = "BRANCH_NAME")
	private String brName;

//	public long getId() {
//		return id;
//	}

	public char getBrCode() {
		return brCode;
	}

	public void setBrCode(char brCode) {
		this.brCode = brCode;
	}

	public String getBrName() {
		return brName;
	}

	public void setBrName(String brName) {
		this.brName = brName;
	}
	
	
	
	
	
	
}
