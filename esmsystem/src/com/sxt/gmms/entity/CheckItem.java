package com.sxt.gmms.entity;

/**
 * 盘点明细类
 * 
 * @author ming
 * 
 */
public class CheckItem {

	private int chItemId;
	private String chCode;
	private int chStockQty;
	private int chRealQty;
	private int chItemStatus;
	private Goods goods;

	public CheckItem() {
	}


	public CheckItem(int chItemId, String chCode, int chStockQty,
			int chRealQty, int chItemStatus, Goods goods) {
		super();
		this.chItemId = chItemId;
		this.chCode = chCode;
		this.chStockQty = chStockQty;
		this.chRealQty = chRealQty;
		this.chItemStatus = chItemStatus;
		this.goods = goods;
	}


	public int getChItemId() {
		return chItemId;
	}

	public void setChItemId(int chItemId) {
		this.chItemId = chItemId;
	}

	public String getChCode() {
		return chCode;
	}

	public void setChCode(String chCode) {
		this.chCode = chCode;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public int getChStockQty() {
		return chStockQty;
	}

	public void setChStockQty(int chStockQty) {
		this.chStockQty = chStockQty;
	}

	public int getChRealQty() {
		return chRealQty;
	}

	public void setChRealQty(int chRealQty) {
		this.chRealQty = chRealQty;
	}

	public int getChItemStatus() {
		return chItemStatus;
	}

	public void setChItemStatus(int chItemStatus) {
		this.chItemStatus = chItemStatus;
	}

}
