package com.sxt.gmms.dao.instorage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sxt.gmms.dao.DBUtil;
import com.sxt.gmms.entity.Employee;
import com.sxt.gmms.entity.InStorage;

/**
 * 入库单的DAO层
 * 
 * @author Administrator
 * 
 */
public class InAllDao {

	/**
	 * 按指定条件查找入库单
	 * 
	 * @param empName
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<InStorage> findInStorageByCondition(String empName,
			String startDate, String endDate) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<InStorage> inList = new ArrayList<InStorage>();
		String condi = "";
		if (empName != null && !empName.equals("")) {
			condi += "and ie.emp_name='" + empName + "'";
		}
		if (startDate != null && endDate != null && !startDate.equals("")
				&& !endDate.equals("")) {
			condi += " and ii.in_date between '" + startDate + "' and '"
					+ endDate + "'";
		}
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select ii.*,ie.emp_name " + "from iss_in ii l"
					+ "eft join iss_employee ie "
					+ "on ie.emp_id = ii.emp_id where 1=1 " + condi
					+ " order by ii.in_id desc";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				int inId = rs.getInt("in_id");
				String inCode = rs.getString("in_code");
				Date inDate = rs.getDate("in_date");
				int inStatus = rs.getInt("in_status");
				// 外键处理
				Employee emp = new Employee();
				emp.setEmpName(rs.getString("emp_name"));
				// 组装
				InStorage inStorage = new InStorage(inId, inCode, inDate,
						inStatus, emp);
				// 添加到list
				inList.add(inStorage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return inList;
	}

	/**
	 * 查找指定编号的状态
	 * 
	 * @param inCode
	 * @return
	 */
	public int findStatusByCode(String inCode) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select in_status from iss_in where in_code = '"
					+ inCode + "'";
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("in_status");
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
	public void updateStatus(String inCode) {
		Connection con = null;
		Statement stat = null;
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "update iss_in set in_status = 1 where in_code='"
					+ inCode+"'";
			stat.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, null);
		}
	}

	/**
	 * 查找全部入库单信息
	 * 
	 * @return
	 */
	public List<InStorage> loadInStorage() {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<InStorage> inList = new ArrayList<InStorage>();
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select ii.*,ie.emp_name " + "from iss_in ii "
					+ "left join iss_employee ie "
					+ "on ii.emp_id = ie.emp_id " + "order by in_id desc";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				int inId = rs.getInt("in_id");
				String inCode = rs.getString("in_code");
				Date inDate = rs.getDate("in_date");
				int inStatus = rs.getInt("in_status");
				// 外键处理
				Employee emp = new Employee();
				emp.setEmpName(rs.getString("emp_name"));
				// 组装
				InStorage inStorage = new InStorage(inId, inCode, inDate,
						inStatus, emp);
				// 添加到list
				inList.add(inStorage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return inList;
	}
}
