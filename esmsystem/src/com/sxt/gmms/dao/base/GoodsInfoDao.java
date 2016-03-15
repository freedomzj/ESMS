package com.sxt.gmms.dao.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sxt.gmms.dao.DBUtil;
import com.sxt.gmms.entity.Goods;
import com.sxt.gmms.entity.Size;
import com.sxt.gmms.entity.Supplie;
import com.sxt.gmms.entity.GoodsType;

/**
 * 商品DAO层
 * 
 * @author ming
 * 
 */
public class GoodsInfoDao {

	/**
	 * 按指定拼音码查找商品信息
	 * 
	 * @param pym
	 * @return
	 */
	public List<Goods> findGoodsInfoByPinyin(String pym) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Goods> goodsList = new ArrayList<Goods>();
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select igood.*, itype.type_name,"
					+ "isize.size_name," + "isup.sup_name "
					+ "from iss_goods igood " + "left join iss_type itype "
					+ "on igood.type_id = itype.type_id "
					+ "left join iss_size isize "
					+ "on igood.size_id = isize.size_id "
					+ "left join iss_supplie isup "
					+ "on igood.sup_id = isup.sup_id"
					+ " where goods_pym like '" + "%" + pym + "%" + "'"
					+ " order by goods_id desc";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				String goodsCode = rs.getString("goods_code");
				String goodsName = rs.getString("goods_name");
				String goodsPym = rs.getString("goods_pym");
				String goodsComment = rs.getString("goods_comm");
				float goodsPrice = rs.getFloat("goods_price");
				String goodsProduct = rs.getString("goods_product");
				int goodsStatus = rs.getInt("goods_status");
				// 外键处理
				GoodsType type = new GoodsType();
				type.setTypeName(rs.getString("type_name"));
				Size size = new Size();
				size.setSizeName(rs.getString("size_name"));
				Supplie supplie = new Supplie();
				supplie.setSupName(rs.getString("sup_name"));
				// 组装
				Goods goods = new Goods(0, goodsCode, goodsName, goodsPym,
						goodsComment, goodsPrice, goodsProduct, goodsStatus,
						type, size, supplie);
				goodsList.add(goods);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return goodsList;
	}

	/**
	 * 修改指定编号的值
	 * 
	 * @param goods
	 */
	public void updateGoodsInfo(Goods goods) {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = DBUtil.getConn();
			String sql = "update iss_goods set "
					+ "goods_name =?, "
					+ "goods_pym =?, "
					+ "goods_comm = ?, "
					+ "goods_price = ?, "
					+ "goods_product = ?, "
					+ "goods_status = ?, "
					+ "type_id = (select type_id from iss_type where type_name = ?), "
					+ "size_id = (select size_id from iss_size where size_name = ?), "
					+ "sup_id = (select sup_id from iss_supplie where sup_name = ?)"
					+ " where goods_code = ?";
			stat = con.prepareStatement(sql);

			stat.setString(1, goods.getGoodsName());
			stat.setString(2, goods.getGoodsPym());
			stat.setString(3, goods.getGoodsComment());
			stat.setFloat(4, goods.getGoodsPrice());
			stat.setString(5, goods.getGoodsProduct());
			stat.setInt(6, goods.getGoodsStatus());
			stat.setString(7, goods.getType().getTypeName());
			stat.setString(8, goods.getSize().getSizeName());
			stat.setString(9, goods.getSupplie().getSupName());
			stat.setString(10, goods.getGoodsCode());
			// 执行
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查找指定编号的信息
	 * 
	 * @param code
	 * @return
	 */
	public Goods findGoodsInfo(String code) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		Goods goods = null;
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select igood.*, itype.type_name,"
					+ "isize.size_name," + "isup.sup_name "
					+ "from iss_goods igood " + "left join iss_type itype "
					+ "on igood.type_id = itype.type_id "
					+ "left join iss_size isize "
					+ "on igood.size_id = isize.size_id "
					+ "left join iss_supplie isup "
					+ "on igood.sup_id = isup.sup_id where goods_code = '"
					+ code + "'" + " order by goods_id desc";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				String goodsCode = rs.getString("goods_code");
				String goodsName = rs.getString("goods_name");
				String goodsPym = rs.getString("goods_pym");
				String goodsComment = rs.getString("goods_comm");
				float goodsPrice = rs.getFloat("goods_price");
				String goodsProduct = rs.getString("goods_product");
				int goodsStatus = rs.getInt("goods_status");
				// 外键处理
				GoodsType type = new GoodsType();
				type.setTypeName(rs.getString("type_name"));
				Size size = new Size();
				size.setSizeName(rs.getString("size_name"));
				Supplie supplie = new Supplie();
				supplie.setSupName(rs.getString("sup_name"));
				// 组装
				goods = new Goods(0, goodsCode, goodsName, goodsPym,
						goodsComment, goodsPrice, goodsProduct, goodsStatus,
						type, size, supplie);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return goods;
	}

	/**
	 * 用于删除指定编号的商品信息
	 * 
	 * @param code
	 */
	public void delGoodsInfo(String goodsCode) {
		String sql = "delete from iss_goods where goods_code = '" + goodsCode
				+ "'";
		DBUtil.executeUpdate(sql);
	}

	/**
	 * 添加商品到数据库
	 * 
	 * @param goods
	 */
	public void addGoofsInfo(Goods goods) {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = DBUtil.getConn();
			String sql = "insert into iss_goods( " + "goods_code, "
					+ "goods_name, " + "goods_pym, " + "goods_comm, "
					+ "goods_price, " + "goods_product, " + "goods_status, "
					+ "type_id, " + "size_id, "
					+ "sup_id) values(?,?,?,?,?,?,1,"
					+ "(select type_id from iss_type where type_name = ?)"
					+ ",(select size_id from iss_size where size_name = ?),"
					+ "(select sup_id from iss_supplie where sup_name = ?))";
			stat = con.prepareStatement(sql);
			stat.setString(1, goods.getGoodsCode());
			stat.setString(2, goods.getGoodsName());
			stat.setString(3, goods.getGoodsPym());
			stat.setString(4, goods.getGoodsComment());
			stat.setFloat(5, goods.getGoodsPrice());
			stat.setString(6, goods.getGoodsProduct());
			stat.setString(7, goods.getType().getTypeName());
			stat.setString(8, goods.getSize().getSizeName());
			stat.setString(9, goods.getSupplie().getSupName());
			// 执行
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, null);
		}
	}

	/**
	 * 查找数据库中的所有商品信息
	 * 
	 * @return
	 */
	public List<Goods> loadGoodsList() {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Goods> goodsList = new ArrayList<Goods>();
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select igood.*, itype.type_name,"
					+ "isize.size_name," + "isup.sup_name "
					+ "from iss_goods igood " + "left join iss_type itype "
					+ "on igood.type_id = itype.type_id "
					+ "left join iss_size isize "
					+ "on igood.size_id = isize.size_id "
					+ "left join iss_supplie isup "
					+ "on igood.sup_id = isup.sup_id "
					+ "order by goods_id desc";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				String goodsCode = rs.getString("goods_code");
				String goodsName = rs.getString("goods_name");
				String goodsPym = rs.getString("goods_pym");
				String goodsComment = rs.getString("goods_comm");
				float goodsPrice = rs.getFloat("goods_price");
				String goodsProduct = rs.getString("goods_product");
				int goodsStatus = rs.getInt("goods_status");
				// 外键处理
				GoodsType type = new GoodsType();
				type.setTypeName(rs.getString("type_name"));
				Size size = new Size();
				size.setSizeName(rs.getString("size_name"));
				Supplie supplie = new Supplie();
				supplie.setSupName(rs.getString("sup_name"));
				// 组装
				Goods goods = new Goods(0, goodsCode, goodsName, goodsPym,
						goodsComment, goodsPrice, goodsProduct, goodsStatus,
						type, size, supplie);
				goodsList.add(goods);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return goodsList;
	}
}
