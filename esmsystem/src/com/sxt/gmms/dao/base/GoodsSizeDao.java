package com.sxt.gmms.dao.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sxt.gmms.dao.DBUtil;
import com.sxt.gmms.entity.Size;

/**
 * 商品规格DAO层
 * 
 * @author ming
 * 
 */
public class GoodsSizeDao {

	/**
	 * 修改数据库中的规格信息
	 * 
	 * @param size
	 */
	public void updateGoodsSize(Size size) {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = DBUtil.getConn();
			String sql = "update iss_size set size_name = ? where size_code = ?";
			stat = con.prepareStatement(sql);
			stat.setString(1, size.getSizeName());
			stat.setString(2, size.getSizeCode());
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, null);
		}
	}

	/**
	 * 按编号查询指定的规格
	 * 
	 * @param Code
	 * @return
	 */
	public Size findGoodsSize(String code) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		Size size = null;
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select * from iss_size where size_code = '" + code
					+ "'";
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				String sizeCode = rs.getString("size_code");
				String sizeName = rs.getString("size_name");
				size = new Size(0, sizeCode, sizeName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return size;
	}

	/**
	 * 删除指定编号的规格
	 * 
	 * @param sizeCode
	 */
	public void delGoodsSize(String sizeCode) {
		String sql = "delete from iss_size where size_code = '" + sizeCode + "'";
		DBUtil.executeUpdate(sql);
	}

	/**
	 * 把商品规格添加到数据库中
	 * 
	 * @param size
	 */
	public void addGoodsSize(Size size) {
		Connection con = null;
		PreparedStatement stat = null;

		try {
			con = DBUtil.getConn();
			String sql = "insert into iss_size(size_code, size_name) values(?,?)";
			stat = con.prepareStatement(sql);
			stat.setString(1, size.getSizeCode());
			stat.setString(2, size.getSizeName());
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, null);
		}
	}

	/**
	 * 查询数据库中的规格信息
	 * 
	 * @return
	 */
	public List<Size> loadGoodsSizeList() {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Size> sizeList = new ArrayList<Size>();
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select * from iss_size order by size_id desc";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				String sizeCode = rs.getString("size_code");
				String sizeName = rs.getString("size_name");
				Size size = new Size(0, sizeCode, sizeName);
				sizeList.add(size);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return sizeList;
	}
}
