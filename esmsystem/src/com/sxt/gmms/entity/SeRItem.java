package com.sxt.gmms.entity;

/**
 * 销售退货明细表
 * 
 * @author noko
 * 
 */
public class SeRItem {

	private int srItemId;
	private float srItemPrice;
	private int srItemQty;
	private int srItemStatus;
	private Goods goods;
	private SeReturn seReturn;

	public SeRItem() {
	}

	public SeRItem(int srItemId, float srItemPrice, int srItemQty,
			int srItemStatus, Goods goods, SeReturn seReturn) {
		super();
		this.srItemId = srItemId;
		this.srItemPrice = srItemPrice;
		this.srItemQty = srItemQty;
		this.srItemStatus = srItemStatus;
		this.goods = goods;
		this.seReturn = seReturn;
	}

	public int getSrItemId() {
		return srItemId;
	}

	public void setSrItemId(int srItemId) {
		this.srItemId = srItemId;
	}

	public float getSrItemPrice() {
		return srItemPrice;
	}

	public void setSrItemPrice(float srItemPrice) {
		this.srItemPrice = srItemPrice;
	}

	public int getSrItemQty() {
		return srItemQty;
	}

	public void setSrItemQty(int srItemQty) {
		this.srItemQty = srItemQty;
	}

	public int getSrItemStatus() {
		return srItemStatus;
	}

	public void setSrItemStatus(int srItemStatus) {
		this.srItemStatus = srItemStatus;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public SeReturn getSeReturn() {
		return seReturn;
	}

	public void setSeReturn(SeReturn seReturn) {
		this.seReturn = seReturn;
	}

}
