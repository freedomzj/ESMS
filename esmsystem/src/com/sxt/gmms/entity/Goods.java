package com.sxt.gmms.entity;

/**
 * 商品信息类
 * 
 * @author ming
 * 
 */
public class Goods {

	private int goodsId;
	private String goodsCode;
	private String goodsName;
	private String goodsPym;
	private String goodsComment;
	private float goodsPrice;
	private String goodsProduct;
	private int goodsStatus;
	private GoodsType gtype;
	private Size size;
	private Supplie supplie;

	public Goods() {
	}

	public Goods(int goodsId, String goodsCode, String goodsName,
			String goodsPym, String goodsComment, float goodsPrice,
			String goodsProduct, int goodsStatus, GoodsType gtype, Size size,
			Supplie supplie) {
		super();
		this.goodsId = goodsId;
		this.goodsCode = goodsCode;
		this.goodsName = goodsName;
		this.goodsPym = goodsPym;
		this.goodsComment = goodsComment;
		this.goodsPrice = goodsPrice;
		this.goodsProduct = goodsProduct;
		this.goodsStatus = goodsStatus;
		this.gtype = gtype;
		this.size = size;
		this.supplie = supplie;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsPym() {
		return goodsPym;
	}

	public void setGoodsPym(String goodsPym) {
		this.goodsPym = goodsPym;
	}

	public String getGoodsComment() {
		return goodsComment;
	}

	public void setGoodsComment(String goodsComment) {
		this.goodsComment = goodsComment;
	}

	public float getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(float goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsProduct() {
		return goodsProduct;
	}

	public void setGoodsProduct(String goodsProduct) {
		this.goodsProduct = goodsProduct;
	}

	public int getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(int goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public GoodsType getType() {
		return gtype;
	}

	public void setType(GoodsType gtype) {
		this.gtype = gtype;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Supplie getSupplie() {
		return supplie;
	}

	public void setSupplie(Supplie supplie) {
		this.supplie = supplie;
	}
	
}
