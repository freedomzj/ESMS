package com.sxt.gmms.entity;

/**
 * 销售明细类
 * 
 * @author ming
 * 
 */
public class SellItem {

	private int seItemId;
	private float seItemPrice;
	private int seItemQty;
	private int seItemStatus;
	private Sell sell ;
	private Goods goods;

    public SellItem() {}

	public SellItem(int seItemId, float seItemPrice, int seItemQty,
			int seItemStatus, Sell sell, Goods goods) {
		super();
		this.seItemId = seItemId;
		this.seItemPrice = seItemPrice;
		this.seItemQty = seItemQty;
		this.seItemStatus = seItemStatus;
		this.sell = sell;
		this.goods = goods;
	}

	public int getSeItemId() {
		return seItemId;
	}

	public void setSeItemId(int seItemId) {
		this.seItemId = seItemId;
	}

	public float getSeItemPrice() {
		return seItemPrice;
	}

	public void setSeItemPrice(float seItemPrice) {
		this.seItemPrice = seItemPrice;
	}

	public int getSeItemQty() {
		return seItemQty;
	}

	public void setSeItemQty(int seItemQty) {
		this.seItemQty = seItemQty;
	}

	public int getSeItemStatus() {
		return seItemStatus;
	}

	public void setSeItemStatus(int seItemStatus) {
		this.seItemStatus = seItemStatus;
	}

	public Sell getSell() {
		return sell;
	}

	public void setSell(Sell sell) {
		this.sell = sell;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}
    
}
