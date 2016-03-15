package com.sxt.gmms.entity;

import java.util.Date;

/**
 * 销售退货表
 * 
 * @author noko
 * 
 */
public class SeReturn {

	private int seRId;
	private String seRCode;
	private Date seRDate;
	private int seRStatus;
	private String seRComm;
	private Employee emploee;

	public SeReturn() {
	}

	public SeReturn(int seRId, String seRCode, Date seRDate, int seRStatus,
			String seRComm, Employee emploee) {
		super();
		this.seRId = seRId;
		this.seRCode = seRCode;
		this.seRDate = seRDate;
		this.seRStatus = seRStatus;
		this.seRComm = seRComm;
		this.emploee = emploee;
	}

	public int getSeRId() {
		return seRId;
	}

	public void setSeRId(int seRId) {
		this.seRId = seRId;
	}

	public String getSeRCode() {
		return seRCode;
	}

	public void setSeRCode(String seRCode) {
		this.seRCode = seRCode;
	}

	public Date getSeRDate() {
		return seRDate;
	}

	public void setSeRDate(Date seRDate) {
		this.seRDate = seRDate;
	}

	public int getSeRStatus() {
		return seRStatus;
	}

	public void setSeRStatus(int seRStatus) {
		this.seRStatus = seRStatus;
	}

	public String getSeRComm() {
		return seRComm;
	}

	public void setSeRComm(String seRComm) {
		this.seRComm = seRComm;
	}

	public Employee getEmploee() {
		return emploee;
	}

	public void setEmploee(Employee emploee) {
		this.emploee = emploee;
	}
}
