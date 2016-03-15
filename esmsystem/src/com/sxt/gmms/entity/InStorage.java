package com.sxt.gmms.entity;

import java.util.Date;

/**
 * 入库表
 * 
 * @author ming
 * 
 */
public class InStorage {

	private int inId;
	private String inCode;
	private Date inDate;
	private int inStatus;
	private Employee emp;

	public InStorage() {

	}

	public InStorage(int inId, String inCode, Date inDate, int inStatus,
			Employee emp) {
		super();
		this.inId = inId;
		this.inCode = inCode;
		this.inDate = inDate;
		this.inStatus = inStatus;
		this.emp = emp;
	}

	public int getInId() {
		return inId;
	}

	public void setInId(int inId) {
		this.inId = inId;
	}

	public String getInCode() {
		return inCode;
	}

	public void setInCode(String inCode) {
		this.inCode = inCode;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public int getInStatus() {
		return inStatus;
	}

	public void setInStatus(int inStatus) {
		this.inStatus = inStatus;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

}
