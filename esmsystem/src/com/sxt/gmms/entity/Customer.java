package com.sxt.gmms.entity;

/**
 * 会员类
 * @author ming
 *
 */
public class Customer {

	private int cusId;
	private String cusCode; 
	private String cusName; 
	private String cusPym; 
	private int cusAge; 
	private String cusPhone; 
	private int cusSex; 
	private String cusComName;
	private String cusAddre; 
	private int cusStatus;
	
	public Customer() {
	}

	public Customer(int cusId, String cusCode, String cusName, String cusPym,
			int cusAge, String cusPhone, int cusSex, String cusComName,
			String cusAddre, int cusStatus) {
		super();
		this.cusId = cusId;
		this.cusCode = cusCode;
		this.cusName = cusName;
		this.cusPym = cusPym;
		this.cusAge = cusAge;
		this.cusPhone = cusPhone;
		this.cusSex = cusSex;
		this.cusComName = cusComName;
		this.cusAddre = cusAddre;
		this.cusStatus = cusStatus;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public String getCusCode() {
		return cusCode;
	}

	public void setCusCode(String cusCode) {
		this.cusCode = cusCode;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusPym() {
		return cusPym;
	}

	public void setCusPym(String cusPym) {
		this.cusPym = cusPym;
	}

	public int getCusAge() {
		return cusAge;
	}

	public void setCusAge(int cusAge) {
		this.cusAge = cusAge;
	}

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

	public int getCusSex() {
		return cusSex;
	}

	public void setCusSex(int cusSex) {
		this.cusSex = cusSex;
	}

	public String getCusComName() {
		return cusComName;
	}

	public void setCusComName(String cusComName) {
		this.cusComName = cusComName;
	}

	public String getCusAddre() {
		return cusAddre;
	}

	public void setCusAddre(String cusAddre) {
		this.cusAddre = cusAddre;
	}

	public int getCusStatus() {
		return cusStatus;
	}

	public void setCusStatus(int cusStatus) {
		this.cusStatus = cusStatus;
	}

	
}
