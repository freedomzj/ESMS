package com.sxt.gmms.dao.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sxt.gmms.dao.DBUtil;
import com.sxt.gmms.entity.Customer;

/**
 * 会员DAO层
 * 
 * @author ming
 * 
 */
public class CustomerDao {

	/**
	 * 按拼音码查询
	 * 
	 * @param pym
	 * @return
	 */
	public List<Customer> findCustomerList(String pym) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Customer> cusList = new ArrayList<Customer>();
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select * from iss_customer where cus_pym like '"
					+ "%" + pym + "%" + "' order by cus_id desc";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				// 收集信息
				int cusId = rs.getInt("cus_id");
				String cusCode = rs.getString("cus_code");
				String cusName = rs.getString("cus_name");
				String cusPym = rs.getString("cus_pym");
				int cusAge = rs.getInt("cus_age");
				String cusPhone = rs.getString("cus_phone");
				int cusSex = rs.getInt("cus_sex");
				String cusAddre = rs.getString("cus_comaddress");
				int cusStatus = rs.getInt("cus_status");
				String cusComName = rs.getString("cus_comname");
				// 组装
				Customer cus = new Customer(cusId, cusCode, cusName, cusPym,
						cusAge, cusPhone, cusSex, cusComName, cusAddre,
						cusStatus);
				cusList.add(cus);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return cusList;
	}

	/**
	 * 修改指定的会员
	 * 
	 * @param cus
	 */
	public void updateCustomer(Customer cus) {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = DBUtil.getConn();
			String sql = "update iss_customer set  " + "cus_code = ?, "
					+ "cus_name = ?, " + "cus_pym = ?, " + "cus_age = ?, "
					+ "cus_phone = ?, " + "cus_sex = ?, "+ "cus_comaddress = ?, " 
					+ "cus_status = ?, "+ "cus_comname = ? " 
					+ " where cus_code = '" + cus.getCusCode() + "'";
			stat = con.prepareStatement(sql);
			stat = con.prepareStatement(sql);
			stat.setString(1, cus.getCusCode());
			stat.setString(2, cus.getCusName());
			stat.setString(3, cus.getCusPym());
			stat.setInt(4, cus.getCusAge());
			stat.setString(5, cus.getCusPhone());
			stat.setInt(6, cus.getCusSex());
			stat.setString(7, cus.getCusAddre());
			stat.setInt(8, cus.getCusStatus());
			stat.setString(9, cus.getCusComName());
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, null);
		}
	}

	/**
	 * 查找指定会员的信息
	 * 
	 * @param code
	 * @return
	 */
	public Customer findCustomer(String code) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		Customer cus = null;
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select * from iss_customer where cus_code = '" + code
					+ "'";
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				// 收集信息
				int cusId = rs.getInt("cus_id");
				String cusCode = rs.getString("cus_code");
				String cusName = rs.getString("cus_name");
				String cusPym = rs.getString("cus_pym");
				int cusAge = rs.getInt("cus_age");
				String cusPhone = rs.getString("cus_phone");
				int cusSex = rs.getInt("cus_sex");
				String cusAddre = rs.getString("cus_comaddress");
				int cusStatus = rs.getInt("cus_status");
				String cusComName = rs.getString("cus_comname");
				// 组装
				cus = new Customer(cusId, cusCode, cusName, cusPym, cusAge,
						cusPhone, cusSex, cusComName, cusAddre, cusStatus);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return cus;
	}

	/**
	 * 删除指定编号的会员
	 * 
	 * @param cusCode
	 */
	public void delCustomer(String cusCode) {
		String sql = "delete from iss_customer where cus_code = '" + cusCode
				+ "'";
		DBUtil.executeUpdate(sql);
	}

	/**
	 * 添加会员到数据库
	 * 
	 * @param cus
	 */
	public void addCustomer(Customer cus) {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = DBUtil.getConn();
			String sql = "insert into iss_customer(" + "cus_code, "
					+ "cus_name, " + "cus_pym, " + "cus_age, " + "cus_phone, "
					+ "cus_sex, " + "cus_comaddress, " + "cus_status,"
					+ "cus_comname) " + " values(?,?,?,?,?,?,?,?,?)";
			stat = con.prepareStatement(sql);
			stat.setString(1, cus.getCusCode());
			stat.setString(2, cus.getCusName());
			stat.setString(3, cus.getCusPym());
			stat.setInt(4, cus.getCusAge());
			stat.setString(5, cus.getCusPhone());
			stat.setInt(6, cus.getCusSex());
			stat.setString(7, cus.getCusAddre());
			stat.setInt(8, cus.getCusStatus());
			stat.setString(9, cus.getCusComName());
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, null);
		}
	}

	/**
	 * 把Customer的信息从数据库中取出来
	 * 
	 * @return
	 */
	public List<Customer> loadCustomerList() {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Customer> cusList = new ArrayList<Customer>();
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select * from iss_customer order by cus_id desc";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				// 收集信息
				int cusId = rs.getInt("cus_id");
				String cusCode = rs.getString("cus_code");
				String cusName = rs.getString("cus_name");
				String cusPym = rs.getString("cus_pym");
				int cusAge = rs.getInt("cus_age");
				String cusPhone = rs.getString("cus_phone");
				int cusSex = rs.getInt("cus_sex");
				String cusAddre = rs.getString("cus_comaddress");
				int cusStatus = rs.getInt("cus_status");
				String cusComName = rs.getString("cus_comname");
				// 组装
				Customer cus = new Customer(cusId, cusCode, cusName, cusPym,
						cusAge, cusPhone, cusSex, cusComName, cusAddre,
						cusStatus);
				cusList.add(cus);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return cusList;
	}
}
