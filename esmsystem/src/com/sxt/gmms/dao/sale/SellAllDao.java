package com.sxt.gmms.dao.sale;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sxt.gmms.dao.DBUtil;
import com.sxt.gmms.entity.Employee;
import com.sxt.gmms.entity.InStorage;
import com.sxt.gmms.entity.Sell;

/**
 * 销售汇总的DAO层
 * 
 * @author Administrator
 * 
 */
public class SellAllDao {

	/**
	 * 按指定条件查找销售单
	 * 
	 * @param empName
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<Sell> findSellByCondition(String empName, String startDate,
			String endDate) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Sell> sellList = new ArrayList<Sell>();
		String condi = "";
		if (empName != null && !empName.equals("")) {
			condi += "and ie.emp_name='" + empName + "'";
		}
		if (startDate != null && endDate != null && !startDate.equals("")
				&& !endDate.equals("")) {
			condi += " and iss.sell_date between '" + startDate + "' and '"
					+ endDate + "'";
		}
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select iss.*,ie.emp_name " + "from iss_sell iss "
					+ "left join iss_employee ie "
					+ "on ie.emp_id = iss.emp_id where 1=1 " + condi
					+ " order by iss.sell_id desc";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				int sellId = rs.getInt("sell_id");
				String sellCode = rs.getString("sell_code");
				Date sellDate = rs.getDate("sell_date");
				int sellStatus = rs.getInt("sell_status");
				// 外键处理
				Employee emp = new Employee();
				emp.setEmpName(rs.getString("emp_name"));
				// 组装
				Sell sell = new Sell(sellId, sellCode, sellDate, sellStatus,
						emp);
				// 添加到list
				sellList.add(sell);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return sellList;
	}

	/**
	 * 查找指定编号的状态
	 * 
	 * @param inCode
	 * @return
	 */
	public int findStatusByCode(String sellCode) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select sell_status from iss_sell where sell_code = '"
					+ sellCode + "'";
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("sell_status");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, null);
		}
		return result;
	}

	/**
	 * 按指定编号通过审核
	 * 
	 * @param inCode
	 */
	public void updateStatus(String sellCode) {
		Connection con = null;
		Statement stat = null;
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "update iss_sell set sell_status = 1 where sell_code='"
					+ sellCode+"'";
			stat.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, null);
		}
	}

	/**
	 * 查找全部销售单信息
	 * 
	 * @return
	 */
	public List<Sell> loadSell() {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Sell> sellList = new ArrayList<Sell>();
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select iss.*,ie.emp_name " + "from iss_sell iss "
					+ "left join iss_employee ie "
					+ "on iss.emp_id = ie.emp_id " + "order by sell_id desc";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				int sellId = rs.getInt("sell_id");
				String sellCode = rs.getString("sell_code");
				Date sellDate = rs.getDate("sell_date");
				int sellStatus = rs.getInt("sell_status");
				// 外键处理
				Employee emp = new Employee();
				emp.setEmpName(rs.getString("emp_name"));
				// 组装
				Sell sell = new Sell(sellId, sellCode, sellDate, sellStatus,
						emp);
				// 添加到list
				sellList.add(sell);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return sellList;
	}
}
