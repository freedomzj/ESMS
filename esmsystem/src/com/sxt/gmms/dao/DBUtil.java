package com.sxt.gmms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 数据库工具类
 * @author ming
 *
 */
public class DBUtil {
	
	/**
	 * 公用的一个普通的增删改的执行方法
	 * @param sql
	 * @return
	 */
	public static int executeUpdate(String sql){
		
		Connection con=null;
		Statement stat=null;
		int rowCount=0;
		try {
			con=getConn();
			stat=con.createStatement();
			rowCount=stat.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConn(con, stat,null);
		}
		return rowCount;
	}
	
	/**
	 * 专用于取得数据库连接
	 * @return 数据库连接 
	 */
	public static Connection getConn(){
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433; DatabaseName=esmsdb";
			con = DriverManager.getConnection(url, "sa", "serical");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * 专用于关闭数据库连接
	 * @param con
	 * @param stat
	 * @param rs
	 */
	public static void closeConn(Connection con,Statement stat,ResultSet rs){
		try {
			if(rs!=null){
				rs.close();
			}
			if(stat!=null){
				stat.close();
			}
			if(con!=null){
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
