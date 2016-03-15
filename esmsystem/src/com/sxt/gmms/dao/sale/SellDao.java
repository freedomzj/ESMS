package com.sxt.gmms.dao.sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sxt.gmms.dao.DBUtil;
import com.sxt.gmms.entity.Sell;
import com.sxt.gmms.entity.SellItem;

/**
 * 商品销售的DAO层
 * 
 * @author Administrator
 * 
 */
public class SellDao {

	/**
	 * 保存销售单，销售明细
	 * 
	 * @param sell
	 * @param itemList
	 */
	public void addSellAndItem(Sell sell, List<SellItem> itemList) {
		Connection con = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConn();
			// 设置自动提交为false
			con.setAutoCommit(false);
			// 先保存销售单
			String inSql = "insert into iss_sell(" + "sell_code,"
					+ "sell_date," + "sell_status," + "emp_id)"
					+ " values(?,?,?,(" + "select emp_id from iss_employee "
					+ "where emp_name=?)) ";
			stat = con.prepareStatement(inSql, new String[] { "sell_id" });
			stat.setString(1, sell.getSellCode());
			stat.setDate(2, new java.sql.Date(sell.getSellDate().getTime()));
			stat.setInt(3, sell.getSellStatus());
			stat.setString(4, sell.getEmp().getEmpName());
			stat.executeUpdate();

			// 取得返回的销售单id
			int sellId = 0;
			rs = stat.getGeneratedKeys();
			if (rs.next()) {
				sellId = rs.getInt(1);
			}
			// 再保存销售明细
			String itemSql = "insert into iss_sell_item(" + "se_item_price,"
					+ "se_item_qty," + "se_item_status," + "goods_id,"
					+ "sell_id)" + "values(?,?,?,"
					+ "(select goods_id from iss_goods where goods_code =  ?),"
					+ "?)";
			stat = con.prepareStatement(itemSql);
			for (SellItem sellItem : itemList) {
				stat.setFloat(1, sellItem.getSeItemPrice());
				stat.setInt(2, sellItem.getSeItemQty());
				stat.setInt(3, 0);
				stat.setString(4, sellItem.getGoods().getGoodsCode());
				stat.setInt(5, sellId);
				stat.executeUpdate();
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
	}
}
