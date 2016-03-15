package com.sxt.gmms.dao.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.sxt.gmms.dao.DBUtil;
import com.sxt.gmms.entity.Role;

/**
 * 角色DAO层
 * 
 * @author ming
 * 
 */
public class RoleDao {

	/**
	 * 修改指定名称的角色
	 * 
	 * @param role
	 */
	public void updateRole(Role role) {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = DBUtil.getConn();
			String sql = "update iss_role set role_desc = ? where role_name = ?";
			stat = con.prepareStatement(sql);
			stat.setString(1, role.getRoleDescr());
			stat.setString(2, role.getRoleName());
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, null);
		}
	}

	/**
	 * 查找指定名称的角色
	 * 
	 * @param name
	 * @return
	 */
	public Role findRole(String name) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		Role role = null;
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select * from iss_role where role_name = '" + name
					+ "'";
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				String roleName = rs.getString("role_name");
				String roleDescr = rs.getString("role_desc");
				int roleStatus = rs.getInt("role_status");
				role = new Role(0, roleName, roleDescr, roleStatus);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return role;
	}

	/**
	 * 删除指定名称的角色
	 * 
	 * @param roleName
	 */
	public void delRole(String roleName) {
		String sql = "delete from iss_role where role_name = '" + roleName + "'";
		DBUtil.executeUpdate(sql);
	}

	/**
	 * 添加角色
	 * 
	 * @param role
	 */
	public void addRole(Role role) {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = DBUtil.getConn();
			String sql = "insert into iss_role(role_name, role_desc) values(?,?)";
			stat = con.prepareStatement(sql);
			stat.setString(1, role.getRoleName());
			stat.setString(2, role.getRoleDescr());
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, null);
		}
	}

	/**
	 * 加载role到list
	 * 
	 * @return
	 */
	public List<Role> loadRoleList() {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Role> roleList = new ArrayList<Role>();
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select * from iss_role order by role_id desc";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				String roleName = rs.getString("role_name");
				String roleDescr = rs.getString("role_desc");
				int roleStatus = rs.getInt("role_status");
				Role role = new Role(0, roleName, roleDescr, roleStatus);
				roleList.add(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return roleList;
	}
}
