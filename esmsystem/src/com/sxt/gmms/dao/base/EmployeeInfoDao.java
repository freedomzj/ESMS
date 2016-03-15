package com.sxt.gmms.dao.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sxt.gmms.dao.DBUtil;
import com.sxt.gmms.entity.Employee;

/**
 * 员工DAO层
 * 
 * @author ming
 * 
 */
public class EmployeeInfoDao {

	/**
	 * 按指定拼音码查找
	 * 
	 * @param pym
	 * @return
	 */
	public List<Employee> findEmployeeInfoByPinyin(String pym) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Employee> empList = new ArrayList<Employee>();
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select * from iss_employee  where emp_pym " +
					" like '"+"%" + pym+"%"
					+ "'" + "order by emp_id desc ";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				int empId = rs.getInt("emp_id");
				String empCode = rs.getString("emp_code");
				String empName = rs.getString("emp_name");
				String empPym = rs.getString("emp_pym");
				int empAge = rs.getInt("emp_age");
				float empBaseSal = rs.getFloat("emp_base_sal");
				String empPhone = rs.getString("emp_phone");
				int empSex = rs.getInt("emp_sex");
				String empAddre = rs.getString("emp_address");
				Date empInDate = rs.getDate("emp_in_date");
				int empStatus = rs.getInt("emp_status");
				Employee emp = new Employee(empId, empCode, empName, empPym,
						empAge, empBaseSal, empPhone, empSex, empAddre,
						empInDate, empStatus);
				empList.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return empList;
	}

	/**
	 * 修改指定编号的员工
	 * 
	 * @param emp
	 */
	public void updateEmployeeInfo(Employee emp) {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = DBUtil.getConn();
			String sql = "update iss_employee set " + "emp_name = ?, "
					+ "emp_pym = ?, " + "emp_age = ?, " + "emp_base_sal = ?, "
					+ "emp_phone = ?, " + "emp_sex = ?, " + "emp_address = ?, "
					+ "emp_in_date = ?, " + "emp_status = ? where emp_code = ?";
			stat = con.prepareStatement(sql);
			stat = con.prepareStatement(sql);
			stat.setString(1, emp.getEmpName());
			stat.setString(2, emp.getEmpPym());
			stat.setInt(3, emp.getEmpAge());
			stat.setFloat(4, emp.getEmpBaseSal());
			stat.setString(5, emp.getEmpPhone());
			stat.setInt(6, emp.getEmpSex());
			stat.setString(7, emp.getEmpAddre());
			stat.setDate(8, new java.sql.Date(emp.getEmpInDate().getTime()));
			stat.setInt(9, emp.getEmpStatus());
			stat.setString(10, emp.getEmpCode());
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查找指定的编号的员工
	 * 
	 * @param code
	 * @return
	 */
	public Employee findEmployee(String code) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		Employee emp = null;
		try {
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select * from iss_employee where emp_code = '" + code
					+ "'";
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				int empId = rs.getInt("emp_id");
				String empCode = rs.getString("emp_code");
				String empName = rs.getString("emp_name");
				String empPym = rs.getString("emp_pym");
				int empAge = rs.getInt("emp_age");
				float empBaseSal = rs.getFloat("emp_base_sal");
				String empPhone = rs.getString("emp_phone");
				int empSex = rs.getInt("emp_sex");
				String empAddre = rs.getString("emp_address");
				Date empInDate = rs.getDate("emp_in_date");
				int empStatus = rs.getInt("emp_status");
				emp = new Employee(empId, empCode, empName, empPym, empAge,
						empBaseSal, empPhone, empSex, empAddre, empInDate,
						empStatus);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return emp;
	}

	/**
	 * 删除指定编号的员工
	 * 
	 * @param empCode
	 */
	public void delEmployeeInfo(String empCode) {
		String sql = "delete from iss_employee where emp_code = '" + empCode
				+ "'";
		DBUtil.executeUpdate(sql);
	}

	/**
	 * 添加新的员工到数据库
	 * 
	 * @param emp
	 */
	public void addEmployeeInfo(Employee emp) {
		Connection con = null;
		PreparedStatement stat = null;
		try {
			con = DBUtil.getConn();
			String sql = "insert into iss_employee( " + "emp_code, "
					+ "emp_name, " + "emp_pym, " + "emp_age, "
					+ "emp_base_sal, " + "emp_phone, " + "emp_sex, "
					+ "emp_address, " + "emp_in_date, " + "emp_status) "
					+ "values(?,?,?,?,?,?,?,?,?,?)";
			stat = con.prepareStatement(sql);
			stat.setString(1, emp.getEmpCode());
			stat.setString(2, emp.getEmpName());
			stat.setString(3, emp.getEmpPym());
			stat.setInt(4, emp.getEmpAge());
			stat.setFloat(5, emp.getEmpBaseSal());
			stat.setString(6, emp.getEmpPhone());
			stat.setInt(7, emp.getEmpSex());
			stat.setString(8, emp.getEmpAddre());
			stat.setDate(9, new java.sql.Date(emp.getEmpInDate().getTime()));
			stat.setInt(10, emp.getEmpStatus());
			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, null);
		}
	}

	/**
	 * 加载所有的数据到list
	 * 
	 * @return
	 */
	public List<Employee> loadEmployeeInfoList() {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		List<Employee> empList = new ArrayList<Employee>();
		try {

		/*	[emp_id]
		      ,[emp_code]
		      ,[emp_name]
		      ,[emp_pym]
		      ,[emp_age]
		      ,[emp_base_sal]
		      ,[emp_phone]
		      ,[emp_sex]
		      ,[emp_addressss]
		      ,[emp_in_date]
		      ,[emp_status]
			*/
			con = DBUtil.getConn();
			stat = con.createStatement();
			String sql = "select * from iss_employee order by emp_id desc";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				int empId = rs.getInt("emp_id");
				String empCode = rs.getString("emp_code");
				String empName = rs.getString("emp_name");
				String empPym = rs.getString("emp_pym");
				int empAge = rs.getInt("emp_age");
				float empBaseSal = rs.getFloat("emp_base_sal");
				String empPhone = rs.getString("emp_phone");
				int empSex = rs.getInt("emp_sex");
				String empAddre = rs.getString("emp_address");
				Date empInDate = rs.getDate("emp_in_date");
				int empStatus = rs.getInt("emp_status");
				Employee emp = new Employee(empId, empCode, empName, empPym,
						empAge, empBaseSal, empPhone, empSex, empAddre,
						empInDate, empStatus);
				empList.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConn(con, stat, rs);
		}
		return empList;
	}
}
