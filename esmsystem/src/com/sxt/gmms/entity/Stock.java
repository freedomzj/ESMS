package com.sxt.gmms.entity;

/**
 * 库存类
 * 
 * @author ming
 * 
 */
public class Stock {

	private int stockId;
	private Goods goods;
	private int stockQty;
	private int stockStatus;

	public Stock() {
	}

	public Stock(int stockId, Goods goods, int stockQty, int stockStatus) {
		super();
		this.stockId = stockId;
		this.goods = goods;
		this.stockQty = stockQty;
		this.stockStatus = stockStatus;
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public int getStockQty() {
		return stockQty;
	}

	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}

	public int getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(int stockStatus) {
		this.stockStatus = stockStatus;
	}

}
