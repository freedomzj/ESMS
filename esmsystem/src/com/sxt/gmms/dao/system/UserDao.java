package com.sxt.gmms.dao.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sxt.gmms.dao.DBUtil;
import com.sxt.gmms.entity.Role;
import com.sxt.gmms.entity.User;

public class UserDao {

	/**
	 * 验证登录的用户
	 * 
	 * @param temp
	 * @return
	 */
	public User confirmUser(User temp) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		User user = null;
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select * from iss_user iu " + "join iss_role ir "
					+ "on iu.role_id = ir.role_id where user_account = '"
					+ temp.getUserAccount() + "' and user_pwd = '"
					+ temp.getUserPwd() + "'";
			System.out.println(temp.getUserAccount());
			System.out.println(temp.getUserPwd());
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				int userId = rs.getInt("user_id");
				String userAccount = rs.getString("user_account");
				String userPwd = rs.getString("user_pwd");
				int userStatus = rs.getInt("user_status");
				String userComm = rs.getString("user_comm");
				String roleName = rs.getString("role_name");
				Role role = new Role();
				role.setRoleName(roleName);
				user = new User(userId, userAccount, userPwd, userStatus,
						userComm, role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return user;
	}

	/**
	 * 修改指定id的用户
	 * 
	 * @param user
	 */

	public void updateUser(User user) {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = DBUtil.getConn();
			String sql = "update iss_user set user_account = ?,"
					+ " user_pwd = ?," + " user_status = ?,"
					+ " user_comm = ?, " + "role_id = " + "(select role_id "
					+ "from iss_role " + "where role_name = ?)"
					+ " where user_id = ? ";
			stat = con.prepareStatement(sql);
			stat.setString(1, user.getUserAccount());
			stat.setString(2, user.getUserPwd());
			stat.setInt(3, user.getUserStatus());
			stat.setString(4, user.getUserComm());
			stat.setString(5, user.getRoleId().getRoleName());
			stat.setInt(6, user.getUserId());
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, null);
		}
	}

	/**
	 * 按指定id查找用户
	 * 
	 * @param name
	 * @return
	 */
	public User findUser(int id) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		User user = null;
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select * from iss_user fu " + "join iss_role iss "
					+ "on fu.role_id = iss.role_id where user_id = '" + id + "'";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				int userId = rs.getInt("user_id");
				String userAccount = rs.getString("user_account");
				String userPwd = rs.getString("user_pwd");
				int userStatus = rs.getInt("user_status");
				String userComm = rs.getString("user_comm");
				String roleName = rs.getString("role_name");
				Role role = new Role();
				role.setRoleName(roleName);
				user = new User(userId, userAccount, userPwd, userStatus,
						userComm, role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return user;
	}

	/**
	 * 删除指定id的用户
	 * 
	 * @param userName
	 */
	public void delUser(int userId) {
		String sql = "delete from iss_user where user_id = '" + userId + "'";
		DBUtil.executeUpdate(sql);
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 */
	public void addUser(User user) {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = DBUtil.getConn();
			String sql = "insert into iss_user" + "(user_account, user_pwd,"
					+ " user_status, user_comm," + " role_id) values"
					+ "(?,?,?,?," + "(select role_id " + "from iss_role "
					+ "where role_name = ?))";
			stat = con.prepareStatement(sql);
			stat.setString(1, user.getUserAccount());
			stat.setString(2, user.getUserPwd());
			stat.setInt(3, user.getUserStatus());
			stat.setString(4, user.getUserComm());
			stat.setString(5, user.getRoleId().getRoleName());
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, null);
		}
	}

	/**
	 * 加载user到list
	 * 
	 * @return
	 */
	public List<User> loadUserList() {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<User>();
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select fu.*, fr.role_name " + "from iss_user fu "
					+ "join iss_role fr " + "on fu.role_id = fr.role_id "
					+ "order by user_id desc";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				int userId = rs.getInt("user_id");
				String userAccount = rs.getString("user_account");
				String userPwd = rs.getString("user_pwd");
				int userStatus = rs.getInt("user_status");
				String userComm = rs.getString("user_comm");
				String roleName = rs.getString("role_name");
				Role role = new Role();
				role.setRoleName(roleName);
				User user = new User(userId, userAccount, userPwd, userStatus,
						userComm, role);
				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return userList;
	}
}
