package com.sxt.gmms.entity;

/**
 * 供应商信息类
 * 
 * @author ming
 * 
 */
public class Supplie {

	private int supId;
	private String supCode;
	private String supName;
	private String supPym;
	private String supPhone;
	private String supLinkman;
	private String supEmail;
	private String supAddr;
	private String supComment;
	private int supStatus;

	public Supplie() {
	}

	public Supplie(int supId, String supCode, String supName, String supPym,
			String supPhone, String supLinkman, String supEmail,
			String supAddr, String supComment, int supStatus) {
		super();
		this.supId = supId;
		this.supCode = supCode;
		this.supName = supName;
		this.supPym = supPym;
		this.supPhone = supPhone;
		this.supLinkman = supLinkman;
		this.supEmail = supEmail;
		this.supAddr = supAddr;
		this.supComment = supComment;
		this.supStatus = supStatus;
	}

	public int getSupId() {
		return supId;
	}

	public void setSupId(int supId) {
		this.supId = supId;
	}

	public String getSupCode() {
		return supCode;
	}

	public void setSupCode(String supCode) {
		this.supCode = supCode;
	}

	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

	public String getSupPym() {
		return supPym;
	}

	public void setSupPym(String supPym) {
		this.supPym = supPym;
	}

	public String getSupPhone() {
		return supPhone;
	}

	public void setSupPhone(String supPhone) {
		this.supPhone = supPhone;
	}

	public String getSupLinkman() {
		return supLinkman;
	}

	public void setSupLinkman(String supLinkman) {
		this.supLinkman = supLinkman;
	}

	public String getSupEmail() {
		return supEmail;
	}

	public void setSupEmail(String supEmail) {
		this.supEmail = supEmail;
	}

	public String getSupAddr() {
		return supAddr;
	}

	public void setSupAddr(String supAddr) {
		this.supAddr = supAddr;
	}

	public String getSupComment() {
		return supComment;
	}

	public void setSupComment(String supComment) {
		this.supComment = supComment;
	}

	public int getSupStatus() {
		return supStatus;
	}

	public void setSupStatus(int supStatus) {
		this.supStatus = supStatus;
	}

}
