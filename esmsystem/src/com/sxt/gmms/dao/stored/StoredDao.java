package com.sxt.gmms.dao.stored;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sxt.gmms.dao.DBUtil;
import com.sxt.gmms.entity.CheckItem;
import com.sxt.gmms.entity.Goods;
import com.sxt.gmms.entity.InStorageItem;
import com.sxt.gmms.entity.SellItem;
import com.sxt.gmms.entity.Size;
import com.sxt.gmms.entity.Stock;
import com.sxt.gmms.entity.Supplie;
import com.sxt.gmms.entity.GoodsType;

/**
 * 库存DAO层
 * 
 * @author Administrator
 * 
 */
public class StoredDao {

	/**
	 * 按明细编号进行模糊查找
	 * 
	 * @param chCode
	 * @return
	 */
	public List<CheckItem> findCheckItemByCode(String chCode) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<CheckItem> itemList = new ArrayList<CheckItem>();
		try {
			con = DBUtil.getConn();
			String sql = "select * from iss_check_item ici "
					+ "left join iss_goods ig on ici.goods_id = ig.goods_id "
					+ "where ici.ch_item_code like '%" + chCode + "%'";
			stat = con.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				int chStockQty = rs.getInt("ch_stock_qty");
				int chRealQty = rs.getInt("ch_real_qty");
				String chItemCode = rs.getString("ch_item_code");
				// 外键处理
				Goods goods = new Goods();
				String goodsCode = rs.getString("goods_code");
				String goodsName = rs.getString("goods_name");
				float goodsPrice = rs.getFloat("goods_price");
				goods.setGoodsCode(goodsCode);
				goods.setGoodsName(goodsName);
				goods.setGoodsPrice(goodsPrice);

				// 组装
				CheckItem item = new CheckItem();
				item.setChStockQty(chStockQty);
				item.setChRealQty(chRealQty);
				item.setChCode(chItemCode);
				item.setGoods(goods);

				itemList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return itemList;
	}

	/**
	 * 加载数据库中所有的盘点明细
	 * 
	 * @return
	 */
	public List<CheckItem> loadCheckItem() {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<CheckItem> itemList = new ArrayList<CheckItem>();
		try {
			con = DBUtil.getConn();
			String sql = "select * from iss_check_item ici "
					+ "left join iss_goods ig on ici.goods_id = ig.goods_id";
			stat = con.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				int chStockQty = rs.getInt("ch_stock_qty");
				int chRealQty = rs.getInt("ch_real_qty");
				String chItemCode = rs.getString("ch_item_code");
				// 外键处理
				Goods goods = new Goods();
				String goodsCode = rs.getString("goods_code");
				String goodsName = rs.getString("goods_name");
				float goodsPrice = rs.getFloat("goods_price");
				goods.setGoodsCode(goodsCode);
				goods.setGoodsName(goodsName);
				goods.setGoodsPrice(goodsPrice);

				// 组装
				CheckItem item = new CheckItem();
				item.setChStockQty(chStockQty);
				item.setChRealQty(chRealQty);
				item.setChCode(chItemCode);
				item.setGoods(goods);

				itemList.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return itemList;
	}

	/**
	 * 添加盘点明细
	 * 
	 * @param itemList
	 */
	public void addCheckItem(List<CheckItem> itemList) {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = DBUtil.getConn();
			String sql = "insert into iss_check_item(" + "ch_item_code,"
					+ "ch_stock_qty," + "ch_real_qty,"
					+ "goods_id) values(?,?,?,("
					+ "select goods_id from iss_goods "
					+ "where goods_code = ?))";
			stat = con.prepareStatement(sql);
			for (CheckItem item : itemList) {
				// 查找原来的库存数量
				int stockQty = findQtyByGoodsCode(item.getGoods()
						.getGoodsCode());
				// 把库存数量和真实数量不相等的商品插入到盘点明细中
				if (stockQty != item.getChRealQty()) {
					stat.setString(1, item.getChCode());
					stat.setInt(2, stockQty);
					stat.setInt(3, item.getChRealQty());
					stat.setString(4, item.getGoods().getGoodsCode());
					stat.executeUpdate();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, null);
		}
	}

	/**
	 * 更新库存数量，用于盘点
	 * 
	 * @param stockList
	 */
	public void checkGoodsStock(List<Stock> stockList) {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = DBUtil.getConn();
			String sql = "update iss_stock set stock_qty = ? "
					+ " where goods_id = ( " + " select goods_id "
					+ " from iss_goods " + " where goods_code = ?) ";
			stat = con.prepareStatement(sql);
			for (Stock stock : stockList) {
				stat.setInt(1, stock.getStockQty());
				stat.setString(2, stock.getGoods().getGoodsCode());
				stat.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, null);
		}
	}

	/**
	 * 按商品编号查找库存中的商品数量
	 * 
	 * @param goodsId
	 * @return
	 */
	public int findQtyByGoodsCode(String goodsCode) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select stock_qty from iss_stock "
					+ "where goods_id=( " + "select goods_id from iss_goods "
					+ "where goods_code = '" + goodsCode + "' )";
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("stock_qty");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return result;
	}

	/**
	 * 按商品id查找库存中的商品数量
	 * 
	 * @param goodsId
	 * @return
	 */
	public int findQtyByGoodsId(int goodsId) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select stock_qty from iss_stock where goods_id="
					+ goodsId;
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("stock_qty");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return result;
	}

	/**
	 * 更新库存中的商品数量,用于销售
	 * 
	 * @param itemList
	 */
	public void editStockQty(List<SellItem> itemList) {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = DBUtil.getConn();
			String sql = "update iss_stock set stock_qty=? where goods_id=?";
			stat = con.prepareStatement(sql);
			for (SellItem sellItem : itemList) {
				int temp = findQtyByGoodsId(sellItem.getGoods().getGoodsId());
				int qty = temp - sellItem.getSeItemQty();
				stat.setInt(1, qty);
				stat.setInt(2, sellItem.getGoods().getGoodsId());
				stat.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, null);
		}
	}

	/**
	 * 按拼音码查找库存中的库存信息
	 * 
	 * @param pym
	 * @return
	 */
	public List<Stock> loadStockInfoByPym(String pym) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Stock> stockList = new ArrayList<Stock>();
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select gtemp.*,it.type_name,size.size_name,sup.sup_name from "
					+ " (select iss.stock_id,iss.stock_qty,iss.stock_status,"
					+ " ig.* from iss_stock iss "
					+ " left join iss_goods ig "
					+ " on iss.goods_id=ig.goods_id) gtemp "
					+ " left join iss_type it "
					+ " on gtemp.type_id=it.type_id "
					+ " left join iss_size size "
					+ " on gtemp.size_id=size.size_id "
					+ " left join iss_supplie sup "
					+ " on gtemp.sup_id=sup.sup_id "
					+ " where goods_pym like '%" + pym + "%'";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				int stockId = rs.getInt("stock_id");
				// 库存中的商品外键处理
				int goodsId = rs.getInt("goods_id");
				String goodsCode = rs.getString("goods_code");
				String goodsName = rs.getString("goods_name");
				String goodsPym = rs.getString("goods_pym");
				String goodsComment = rs.getString("goods_comm");
				float goodsPrice = rs.getFloat("goods_price");
				String goodsProduct = rs.getString("goods_product");
				int goodsStatus = rs.getInt("goods_status");
				// 商品中的外键处理
				GoodsType type = new GoodsType();
				type.setTypeName(rs.getString("type_name"));
				Size size = new Size();
				size.setSizeName(rs.getString("size_name"));
				Supplie supplie = new Supplie();
				supplie.setSupName(rs.getString("sup_name"));
				// 组装
				Goods goods = new Goods(goodsId, goodsCode, goodsName,
						goodsPym, goodsComment, goodsPrice, goodsProduct,
						goodsStatus, type, size, supplie);
				int stockQty = rs.getInt("stock_qty");
				int stockStatus = rs.getInt("stock_status");
				Stock stock = new Stock(stockId, goods, stockQty, stockStatus);
				stockList.add(stock);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return stockList;
	}

	/**
	 * 加载数据库中的库存小于预警值的对象
	 * 
	 * @return
	 */
	public List<Stock> findStockByCallNum(int qty) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Stock> stockList = new ArrayList<Stock>();
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select gtemp.*,it.type_name,size.size_name,sup.sup_name from "
					+ " (select iss.stock_id,iss.stock_qty,iss.stock_status,"
					+ " ig.* from iss_stock iss "
					+ " left join iss_goods ig "
					+ " on iss.goods_id=ig.goods_id where iss.stock_qty<"
					+ qty
					+ ") gtemp "
					+ " left join iss_type it "
					+ " on gtemp.type_id=it.type_id "
					+ " left join iss_size size "
					+ " on gtemp.size_id=size.size_id "
					+ " left join iss_supplie sup "
					+ " on gtemp.sup_id=sup.sup_id";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				int stockId = rs.getInt("stock_id");
				// 库存中的商品外键处理
				int goodsId = rs.getInt("goods_id");
				String goodsCode = rs.getString("goods_code");
				String goodsName = rs.getString("goods_name");
				String goodsPym = rs.getString("goods_pym");
				String goodsComment = rs.getString("goods_comm");
				float goodsPrice = rs.getFloat("goods_price");
				String goodsProduct = rs.getString("goods_product");
				int goodsStatus = rs.getInt("goods_status");
				// 商品中的外键处理
				GoodsType type = new GoodsType();
				type.setTypeName(rs.getString("type_name"));
				Size size = new Size();
				size.setSizeName(rs.getString("size_name"));
				Supplie supplie = new Supplie();
				supplie.setSupName(rs.getString("sup_name"));
				// 组装
				Goods goods = new Goods(goodsId, goodsCode, goodsName,
						goodsPym, goodsComment, goodsPrice, goodsProduct,
						goodsStatus, type, size, supplie);
				int stockQty = rs.getInt("stock_qty");
				int stockStatus = rs.getInt("stock_status");
				Stock stock = new Stock(stockId, goods, stockQty, stockStatus);
				stockList.add(stock);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return stockList;
	}

	/**
	 * 加载数据库中的库存对象
	 * 
	 * @return
	 */
	public List<Stock> loadStockInfo() {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Stock> stockList = new ArrayList<Stock>();
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select gtemp.*,it.type_name,size.size_name,sup.sup_name from "
					+ " (select iss.stock_id,iss.stock_qty,iss.stock_status,"
					+ " ig.* from iss_stock iss "
					+ " left join iss_goods ig "
					+ " on iss.goods_id=ig.goods_id) gtemp "
					+ " left join iss_type it "
					+ " on gtemp.type_id=it.type_id "
					+ " left join iss_size size "
					+ " on gtemp.size_id=size.size_id "
					+ " left join iss_supplie sup "
					+ " on gtemp.sup_id=sup.sup_id";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				int stockId = rs.getInt("stock_id");
				// 库存中的商品外键处理
				int goodsId = rs.getInt("goods_id");
				String goodsCode = rs.getString("goods_code");
				String goodsName = rs.getString("goods_name");
				String goodsPym = rs.getString("goods_pym");
				String goodsComment = rs.getString("goods_comm");
				float goodsPrice = rs.getFloat("goods_price");
				String goodsProduct = rs.getString("goods_product");
				int goodsStatus = rs.getInt("goods_status");
				// 商品中的外键处理
				GoodsType type = new GoodsType();
				type.setTypeName(rs.getString("type_name"));
				Size size = new Size();
				size.setSizeName(rs.getString("size_name"));
				Supplie supplie = new Supplie();
				supplie.setSupName(rs.getString("sup_name"));
				// 组装
				Goods goods = new Goods(goodsId, goodsCode, goodsName,
						goodsPym, goodsComment, goodsPrice, goodsProduct,
						goodsStatus, type, size, supplie);
				int stockQty = rs.getInt("stock_qty");
				int stockStatus = rs.getInt("stock_status");
				Stock stock = new Stock(stockId, goods, stockQty, stockStatus);
				stockList.add(stock);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return stockList;
	}

	/**
	 * 增加已存在的商品在库存中的数量，用于入库
	 * 
	 * @param stock
	 */
	public void updateStockQty(Stock stock) {
		Connection con = null;
		Statement stat = null;
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "update iss_stock set stock_qty="
					+ stock.getStockQty() + " where stock_id="
					+ stock.getStockId();
			stat.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, null);
		}
	}

	/**
	 * 查找入库明细中的商品在库存中是否存在
	 * 
	 * @param item
	 *            明细
	 * @return 库存对象
	 */
	public Stock isGoodsExist(InStorageItem item) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		Stock stock = null;
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select * from iss_stock where goods_id="
					+ item.getGoods().getGoodsId();
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				int stockId = rs.getInt("stock_id");
				// 外键处理
				int goodsId = rs.getInt("goods_id");
				Goods goods = new Goods();
				goods.setGoodsId(goodsId);
				int stockQty = rs.getInt("stock_qty");
				int stockStatus = rs.getInt("stock_status");
				stock = new Stock(stockId, goods, stockQty, stockStatus);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return stock;
	}

	/**
	 * 把通过审核的入库单的明细加到库存中
	 * 
	 * @param itemList
	 */
	public void addInStorageItem(List<InStorageItem> itemList) {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = DBUtil.getConn();
			String sql = "insert into iss_stock(stock_qty,stock_status,goods_id)"
					+ "values(?,?,?)";
			stat = con.prepareStatement(sql);
			for (InStorageItem item : itemList) {
				Stock stock = isGoodsExist(item);
				if (stock != null) {
					int temp = stock.getStockQty();
					int qty = temp + item.getInItemQty();
					stock.setStockQty(qty);
					updateStockQty(stock);
					continue;
				}
				stat.setInt(1, item.getInItemQty());
				stat.setInt(2, item.getInItemStatus());
				stat.setInt(3, item.getGoods().getGoodsId());
				stat.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, null);
		}
	}
}
