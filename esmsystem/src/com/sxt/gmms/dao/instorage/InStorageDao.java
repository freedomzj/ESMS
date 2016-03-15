package com.sxt.gmms.dao.instorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sxt.gmms.dao.DBUtil;
import com.sxt.gmms.entity.InStorage;
import com.sxt.gmms.entity.InStorageItem;

/**
 * 商品入库的DAO层
 * 
 * @author Administrator
 * 
 */
public class InStorageDao {

	public void addInStorageAndItem(InStorage inStorage,
			List<InStorageItem> itemList) {
		Connection con = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConn();
			// 设置自动提交为false
			con.setAutoCommit(false);
			// 先保存入库单
			String inSql = "insert into iss_in(" + "in_code," + "in_date,"
					+ "in_status," + "emp_id)" + " values(?,?,?,("
					+ "select emp_id from iss_employee "
					+ "where emp_name=?)) ";
			stat = con.prepareStatement(inSql, new String[] { "in_id" });
			stat.setString(1, inStorage.getInCode());
			stat.setDate(2, new java.sql.Date(inStorage.getInDate().getTime()));
			stat.setInt(3, inStorage.getInStatus());
			stat.setString(4, inStorage.getEmp().getEmpName());
			stat.executeUpdate();

			// 取得返回的入库单id
			int inId = 0;
			rs = stat.getGeneratedKeys();
			if (rs.next()) {
				inId = rs.getInt(1);
			}
			// 再保存入库明细
			String itemSql = "insert into iss_in_item(" + "in_item_price,"
					+ "in_item_qty," + "in_item_status," + "goods_id,"
					+ "in_id)" + "values(?,?,?,"
					+ "(select goods_id from iss_goods where goods_code =  ?),"
					+ "?)";
			stat = con.prepareStatement(itemSql);
			for (InStorageItem inStorageItem : itemList) {
				stat.setFloat(1, inStorageItem.getInItemPrice());
				stat.setInt(2, inStorageItem.getInItemQty());
				stat.setInt(3, 1);
				stat.setString(4, inStorageItem.getGoods().getGoodsCode());
				stat.setInt(5, inId);
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
