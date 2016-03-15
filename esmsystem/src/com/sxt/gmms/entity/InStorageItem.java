package com.sxt.gmms.entity;

/**
 * 入库明细类
 * 
 * @author ming
 * 
 */
public class InStorageItem {

	private int inItemId;
	private float inItemPrice;
	private int inItemQty;
	private int inItemStatus;
	private Goods goods;
	private InStorage in;

	public InStorageItem() {
	}

	public InStorageItem(int inItemId, float inItemPrice, int inItemQty,
			int inItemStatus, Goods goods, InStorage in) {
		super();
		this.inItemId = inItemId;
		this.inItemPrice = inItemPrice;
		this.inItemQty = inItemQty;
		this.inItemStatus = inItemStatus;
		this.goods = goods;
		this.in = in;
	}

	public int getInItemId() {
		return inItemId;
	}

	public void setInItemId(int inItemId) {
		this.inItemId = inItemId;
	}

	public float getInItemPrice() {
		return inItemPrice;
	}

	public void setInItemPrice(float inItemPrice) {
		this.inItemPrice = inItemPrice;
	}

	public int getInItemQty() {
		return inItemQty;
	}

	public void setInItemQty(int inItemQty) {
		this.inItemQty = inItemQty;
	}

	public int getInItemStatus() {
		return inItemStatus;
	}

	public void setInItemStatus(int inItemStatus) {
		this.inItemStatus = inItemStatus;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public InStorage getIn() {
		return in;
	}

	public void setIn(InStorage in) {
		this.in = in;
	}

	

}
