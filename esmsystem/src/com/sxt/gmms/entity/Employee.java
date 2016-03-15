package com.sxt.gmms.entity;

import java.util.Date;

/**
 * 员工类
 * 
 * @author ming
 * 
 */
public class Employee {

	private int empId;
	private String empCode;
	private String empName;
	private String empPym;
	private int empAge;
	private float empBaseSal;
	private String empPhone;
	private int empSex;
	private String empAddre;
	private Date empInDate;
	private int empStatus;

	public Employee() {
	}

	public Employee(int empId, String empCode, String empName, String empPym,
			int empAge, float empBaseSal, String empPhone, int empSex,
			String empAddre, Date empInDate, int empStatus) {
		super();
		this.empId = empId;
		this.empCode = empCode;
		this.empName = empName;
		this.empPym = empPym;
		this.empAge = empAge;
		this.empBaseSal = empBaseSal;
		this.empPhone = empPhone;
		this.empSex = empSex;
		this.empAddre = empAddre;
		this.empInDate = empInDate;
		this.empStatus = empStatus;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpPym() {
		return empPym;
	}

	public void setEmpPym(String empPym) {
		this.empPym = empPym;
	}

	public int getEmpAge() {
		return empAge;
	}

	public void setEmpAge(int empAge) {
		this.empAge = empAge;
	}

	public float getEmpBaseSal() {
		return empBaseSal;
	}

	public void setEmpBaseSal(float empBaseSal) {
		this.empBaseSal = empBaseSal;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public int getEmpSex() {
		return empSex;
	}

	public void setEmpSex(int empSex) {
		this.empSex = empSex;
	}

	public String getEmpAddre() {
		return empAddre;
	}

	public void setEmpAddre(String empAddre) {
		this.empAddre = empAddre;
	}

	public Date getEmpInDate() {
		return empInDate;
	}

	public void setEmpInDate(Date empInDate) {
		this.empInDate = empInDate;
	}

	public int getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(int empStatus) {
		this.empStatus = empStatus;
	}

}
