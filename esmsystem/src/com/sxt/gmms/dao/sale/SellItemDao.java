package com.sxt.gmms.dao.sale;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sxt.gmms.dao.DBUtil;
import com.sxt.gmms.entity.Goods;
import com.sxt.gmms.entity.Sell;
import com.sxt.gmms.entity.SellItem;

/**
 * 销售明细的DAO层
 * 
 * @author Administrator
 * 
 */
public class SellItemDao {

	/**
	 * 用于销售统计报表
	 * 
	 * @return
	 */
	public List<SellItem> loadSellItemReport() {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<SellItem> itemList = new ArrayList<SellItem>();
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = " select ig.goods_name,SUM(se_item_qty) qty"
					+ " from iss_sell_item isi,iss_goods ig "
					+ " where isi.goods_id = ig.goods_id "
					+ " group by ig.goods_name";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				int seItemQty = rs.getInt("qty");
				// 货物外键处理
				Goods goods = new Goods();
				String goodsName = rs.getString("goods_name");
				goods.setGoodsName(goodsName);
				// 组装
				SellItem sellItem = new SellItem(0, 0, seItemQty, 1, null,
						goods);
				itemList.add(sellItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return itemList;
	}

	/**
	 * 按编号查找销售明细
	 * 
	 * @param inCode
	 * @return
	 */
	public List<SellItem> loadSellItemByCode(String sellCode) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<SellItem> itemList = new ArrayList<SellItem>();
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select isi.*,ig.goods_code,ig.goods_name,ig.goods_price "
					+ "from iss_sell_item isi "
					+ "left join iss_goods ig "
					+ "on ig.goods_id = isi.goods_id "
					+ "where isi.sell_id = ("
					+ "select sell_id from iss_sell "
					+ "where sell_code = '" + sellCode + "' )";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				float seItemPrice = rs.getFloat("se_item_price");
				int seItemQty = rs.getInt("se_item_qty");
				// 货物外键处理
				Goods goods = new Goods();
				int goodsId = rs.getInt("goods_id");
				goods.setGoodsId(goodsId);
				String goodsCode = rs.getString("goods_code");
				goods.setGoodsCode(goodsCode);
				String goodsName = rs.getString("goods_name");
				goods.setGoodsName(goodsName);
				float goodsPrice = rs.getFloat("goods_price");
				goods.setGoodsPrice(goodsPrice);
				// 入库单外键
				Sell sell = new Sell();
				sell.setSellCode(sellCode);
				// 组装
				SellItem sellItem = new SellItem(0, seItemPrice, seItemQty, 1,
						sell, goods);
				itemList.add(sellItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return itemList;
	}
}
