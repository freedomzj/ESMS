package com.sxt.gmms.entity;

/**
 * 规格类
 * 
 * @author ming
 * 
 */
public class Size {

	private int sizeId;
	private String sizeCode;
	private String sizeName;

	public Size() {
	}

	public Size(int sizeId, String sizeCode, String sizeName) {
		super();
		this.sizeId = sizeId;
		this.sizeCode = sizeCode;
		this.sizeName = sizeName;
	}

	public int getSizeId() {
		return sizeId;
	}

	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}

	public String getSizeCode() {
		return sizeCode;
	}

	public void setSizeCode(String sizeCode) {
		this.sizeCode = sizeCode;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

}
