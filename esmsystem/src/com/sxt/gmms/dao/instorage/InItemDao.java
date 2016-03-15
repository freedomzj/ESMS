package com.sxt.gmms.dao.instorage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sxt.gmms.dao.DBUtil;
import com.sxt.gmms.entity.Goods;
import com.sxt.gmms.entity.InStorage;
import com.sxt.gmms.entity.InStorageItem;

/**
 * 入库明细的DAO层
 * 
 * @author Administrator
 * 
 */
public class InItemDao {

	/**
	 * 用于统计报表
	 * 
	 * @return
	 */
	public List<InStorageItem> loadInStorageReport() {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<InStorageItem> itemList = new ArrayList<InStorageItem>();
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = " select ig.goods_name, sum(in_item_qty) qty "
					+ " from iss_in_item iii, iss_goods ig where "
					+ " ig.goods_id = iii.goods_id "
					+ " group by ig.goods_name ";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				int inItemQty = rs.getInt("qty");
				// 货物外键处理
				Goods goods = new Goods();
				String goodsName = rs.getString("goods_name");
				goods.setGoodsName(goodsName);
				// 组装
				InStorageItem inStorageItem = new InStorageItem(0, 0,
						inItemQty, 1, goods, null);
				itemList.add(inStorageItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return itemList;
	}

	/**
	 * 按编号查找入库明细
	 * 
	 * @param inCode
	 * @return
	 */
	public List<InStorageItem> loadInStorageItem(String inCode) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<InStorageItem> itemList = new ArrayList<InStorageItem>();
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select iii.*,ig.goods_code,ig.goods_name,ig.goods_price "
					+ "from iss_in_item iii "
					+ "left join iss_goods ig "
					+ "on ig.goods_id = iii.goods_id "
					+ "where iii.in_id = ("
					+ "select in_id from iss_in "
					+ "where in_code = '"
					+ inCode + "' )";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				float inItemPrice = rs.getFloat("in_item_price");
				int inItemQty = rs.getInt("in_item_qty");
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
				InStorage in = new InStorage();
				in.setInCode(inCode);
				// 组装
				InStorageItem inStorageItem = new InStorageItem(0, inItemPrice,
						inItemQty, 1, goods, in);
				itemList.add(inStorageItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return itemList;
	}
}
