package com.sxt.gmms.dao.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sxt.gmms.dao.DBUtil;
import com.sxt.gmms.entity.Supplie;

/**
 * 供应商DAO层
 * 
 * @author ming
 * 
 */
public class SupplierInfoDao {

	/**
	 * 按拼音码查询
	 * 
	 * @param pym
	 * @return
	 */
	public List<Supplie> findSupplierInfoList(String pym) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Supplie> supList = new ArrayList<Supplie>();
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select * from iss_supplie where sup_pym like '"+
			"%"+ pym+"%"
					+ "' order by sup_id desc";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				int supId = rs.getInt("sup_id");
				String supCode = rs.getString("sup_code");
				String supName = rs.getString("sup_name");
				String supPym = rs.getString("sup_pym");
				String supPhone = rs.getString("sup_phone");
				String supLinkman = rs.getString("sup_linkman");
				String supEmail = rs.getString("sup_email");
				String supAddr = rs.getString("sup_address");
				String supComment = rs.getString("sup_comment");
				int supStatus = rs.getInt("sup_status");
				Supplie supplie = new Supplie(supId, supCode, supName, supPym,
						supPhone, supLinkman, supEmail, supAddr, supComment,
						supStatus);
				supList.add(supplie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return supList;
	}

	/**
	 * 修改指定编号的供应商
	 * 
	 * @param supplie
	 */
	public void updateSupplierInfo(Supplie supplie) {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = DBUtil.getConn();
			String sql = "update iss_supplie set  " + "sup_code = ?, "
					+ "sup_name = ?, " + "sup_pym = ?, " + "sup_phone = ?, "
					+ "sup_linkman = ?, " + "sup_email = ?, "
					+ "sup_address = ?, " + "sup_comment = ?, "
					+ "sup_status = ? where sup_code = ?";
			stat = con.prepareStatement(sql);
			stat = con.prepareStatement(sql);
			stat.setString(1, supplie.getSupCode());
			stat.setString(2, supplie.getSupName());
			stat.setString(3, supplie.getSupPym());
			stat.setString(4, supplie.getSupPhone());
			stat.setString(5, supplie.getSupLinkman());
			stat.setString(6, supplie.getSupEmail());
			stat.setString(7, supplie.getSupAddr());
			stat.setString(8, supplie.getSupComment());
			stat.setInt(9, supplie.getSupStatus());
			stat.setString(10, supplie.getSupCode());
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, null);
		}
	}

	/**
	 * 查找指定编号的供应商
	 * 
	 * @param supplieCode
	 * @return
	 */
	public Supplie findSupplierInfo(String supplieCode) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		Supplie supplie = null;
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select * from iss_supplie where sup_code = '"
					+ supplieCode + "'";
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				int supId = rs.getInt("sup_id");
				String supCode = rs.getString("sup_code");
				String supName = rs.getString("sup_name");
				String supPym = rs.getString("sup_pym");
				String supPhone = rs.getString("sup_phone");
				String supLinkman = rs.getString("sup_linkman");
				String supEmail = rs.getString("sup_email");
				String supAddr = rs.getString("sup_address");
				String supComment = rs.getString("sup_comment");
				int supStatus = rs.getInt("sup_status");
				supplie = new Supplie(supId, supCode, supName, supPym,
						supPhone, supLinkman, supEmail, supAddr, supComment,
						supStatus);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return supplie;
	}

	/**
	 * 删除指定编号的数据
	 * 
	 * @param supplieCode
	 */
	public void delSupplierInfo(String supplieCode) {
		String sql = "delete from iss_supplie where sup_code = '" + supplieCode
				+ "'";
		DBUtil.executeUpdate(sql);
	}

	/**
	 * 添加供应商到数据库
	 * 
	 * @param supplie
	 */
	public void addSupplierInfo(Supplie supplie) {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = DBUtil.getConn();
			String sql = "insert into iss_supplie(" + "sup_code, "
					+ "sup_name, " + "sup_pym, " + "sup_phone, "
					+ "sup_linkman, " + "sup_email, " + "sup_address, "
					+ "sup_comment, "
					+ "sup_status) values(?,?,?,?,?,?,?,?,?) ";
			stat = con.prepareStatement(sql);
			stat.setString(1, supplie.getSupCode());
			stat.setString(2, supplie.getSupName());
			stat.setString(3, supplie.getSupPym());
			stat.setString(4, supplie.getSupPhone());
			stat.setString(5, supplie.getSupLinkman());
			stat.setString(6, supplie.getSupEmail());
			stat.setString(7, supplie.getSupAddr());
			stat.setString(8, supplie.getSupComment());
			stat.setInt(9, supplie.getSupStatus());
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, null);
		}
	}

	/**
	 * 加载数据库中Supplie数据到list
	 * 
	 * @return
	 */
	public List<Supplie> loadSupplierInfoList() {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Supplie> supList = new ArrayList<Supplie>();
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select * from iss_supplie order by sup_id desc";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				int supId = rs.getInt("sup_id");
				String supCode = rs.getString("sup_code");
				String supName = rs.getString("sup_name");
				String supPym = rs.getString("sup_pym");
				String supPhone = rs.getString("sup_phone");
				String supLinkman = rs.getString("sup_linkman");
				String supEmail = rs.getString("sup_email");
				String supAddr = rs.getString("sup_address");
				String supComment = rs.getString("sup_comment");
				int supStatus = rs.getInt("sup_status");
				Supplie supplie = new Supplie(supId, supCode, supName, supPym,
						supPhone, supLinkman, supEmail, supAddr, supComment,
						supStatus);
				supList.add(supplie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return supList;
	}
}
