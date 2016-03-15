package com.sxt.gmms.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

/**
 * java mssql数据库的备份与还原
 * 
 * @author Administrator
 * 
 */
public class BackupSqlServer {

	private String path;
	private String dbName;
	private Connection conn;

	public BackupSqlServer(String path, String dbName, Connection conn) {
		super();
		this.path = path;
		this.dbName = dbName;
		this.conn = conn;
	}

	/**
	 * 
	 * 数据库备份
	 * 
	 * @param path存盘路径
	 * @param db_name要备份的数据库名称
	 * @param conn数据库连接对象
	 * @return
	 */
	public String backData() {
		String bk_name = ""; // 要返回的备份名称
		// 盘名是否正确
		if (path.lastIndexOf("/") == -1)
			path += "/";
		// ------------------------
		// 与数据库进行操作
		// ------------------------
		PreparedStatement stat = null;
		String sql = "";
		try {
			String file = "ESMS.bak";
			sql = "backup database " + dbName + " to disk='" + path + file
					+ "' with format,name='full backup of " + dbName + "'";
			stat = conn.prepareStatement(sql);
			stat.executeUpdate();
			bk_name = file; // 返回的文件名
		}
		// 异常
		catch (Exception e) {
			e.printStackTrace();
		}
		// 状态集释放
		finally {
			try {
				stat.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 返回
		return bk_name;
	}

	/**
	 * 数据库恢复
	 * 
	 * @param path备份文件存盘路径
	 * @param bk_name备份文件名称
	 * @param db_name要还原的数据库名称
	 * @param conn数据库连接对象
	 * @return
	 */
	public boolean recoverData(String backName) {
		boolean result = false;
		// 盘名是否正确
		if (path.lastIndexOf("/") == -1)
			path += "/";
		// ------------------------
		// 与数据库进行操作
		// ------------------------
		PreparedStatement stat = null;
		String sql = "";
		try {
			sql += "restore database  " + dbName + "  from disk='" + path
					+ backName + "'";
			sql += "with replace "; // 解决备尚未备份数据库 数据库 的日志尾部
			sql += "alter database  " + dbName
					+ "  set onLine with rollback immediate;";
			stat = conn.prepareStatement(sql);
			stat.executeUpdate();
			result = true;
		}
		// 有异常
		catch (Exception e) {
			e.printStackTrace();
		}
		// 数据库操作释放
		finally {
			try {
				stat.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 返回
		return result;
	}
}