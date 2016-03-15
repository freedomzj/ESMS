package com.sxt.gmms.entity;

import java.util.Date;

/**
 * 销售类
 * 
 * @author ming
 * 
 */
public class Sell {

	private int sellId;
	private String sellCode;
	private Date sellDate;
	private int sellStatus;
	private Employee emp;

	public Sell() {
	}

	public Sell(int sellId, String sellCode, Date sellDate, int sellStatus,
			Employee emp) {
		super();
		this.sellId = sellId;
		this.sellCode = sellCode;
		this.sellDate = sellDate;
		this.sellStatus = sellStatus;
		this.emp = emp;
	}

	public int getSellId() {
		return sellId;
	}

	public void setSellId(int sellId) {
		this.sellId = sellId;
	}

	public String getSellCode() {
		return sellCode;
	}

	public void setSellCode(String sellCode) {
		this.sellCode = sellCode;
	}

	public Date getSellDate() {
		return sellDate;
	}

	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}

	public int getSellStatus() {
		return sellStatus;
	}

	public void setSellStatus(int sellStatus) {
		this.sellStatus = sellStatus;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

}
