package com.sxt.gmms.entity;

/**
 * 商品类型类
 * 
 * @author ming
 * 
 */
public class GoodsType {

	private int typeId;
	private String typeCode;
	private String typeName;
	private int typeStatus;

	public GoodsType() {
	}

	public GoodsType(int typeId, String typeCode, String typeName,
			int typeStatus) {
		super();
		this.typeId = typeId;
		this.typeCode = typeCode;
		this.typeName = typeName;
		this.typeStatus = typeStatus;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getTypeStatus() {
		return typeStatus;
	}

	public void setTypeStatus(int typeStatus) {
		this.typeStatus = typeStatus;
	}

}
