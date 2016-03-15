package com.sxt.gmms.dao.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sxt.gmms.dao.DBUtil;
import com.sxt.gmms.entity.GoodsType;

/**
 * 商品类型DAO层
 * 
 * @author ming
 * 
 */
public class GoodsKindDao {

	/**
	 * 修改所选择的类型信息
	 * 
	 * @param type
	 */
	public void updateGoodsKind(GoodsType type) {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = DBUtil.getConn();
			String sql = "update iss_type set type_name = ? where type_code = ?";
			stat = con.prepareStatement(sql);
			stat.setString(1, type.getTypeName());
			stat.setString(2, type.getTypeCode());
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, null);
		}
	}

	/**
	 * 按编号查找指定的商品类型
	 * 
	 * @param code
	 * @return
	 */
	public GoodsType findGoodsKind(String code) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		GoodsType type = null;
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select * from iss_type where type_code = '" + code + "'";
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				String typeCode = rs.getString("type_code");
				String typeName = rs.getString("type_name");
				type = new GoodsType(0, typeCode, typeName, 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return type;
	}

	/**
	 * 删除指定编号的商品类型
	 * 
	 * @param typeCode
	 */
	public void delGoodsKind(String typeCode) {
		String sql = "delete from iss_type where type_code = '" + typeCode
				+ "'";
		DBUtil.executeUpdate(sql);
	}

	/**
	 * 添加新的商品类型
	 * 
	 * @param type
	 */
	public void addGoodsKind(GoodsType type) {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = DBUtil.getConn();
			String sql = "insert into iss_type(type_code,type_name) values(?,?)";
			stat = con.prepareStatement(sql);
			stat.setString(1, type.getTypeCode());
			stat.setString(2, type.getTypeName());
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, null);
		}
	}

	/**
	 * 加载商品类型到list
	 * 
	 * @return
	 */
	public List<GoodsType> loatGYypeList() {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<GoodsType> typeList = new ArrayList<GoodsType>();
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select * from iss_type order by type_id desc";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				String typeCode = rs.getString("type_code");
				String typeName = rs.getString("type_name");
				GoodsType type = new GoodsType(0, typeCode, typeName, 0);
				typeList.add(type);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return typeList;
	}
}
