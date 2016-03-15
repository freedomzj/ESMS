package com.sxt.gmms.entity;

/**
 * 用户类
 * 
 * @author ming
 * 
 */
public class User {

	private int userId;
	private String userAccount;
	private String userPwd;
	private int userStatus;
	private String userComm;
	private Role role;

	public User() {
	}

	public User(int userId, String userAccount, String userPwd, int userStatus,
			String userComm, Role roleId) {
		super();
		this.userId = userId;
		this.userAccount = userAccount;
		this.userPwd = userPwd;
		this.userStatus = userStatus;
		this.userComm = userComm;
		this.role = roleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserComm() {
		return userComm;
	}

	public void setUserComm(String userComm) {
		this.userComm = userComm;
	}

	public Role getRoleId() {
		return role;
	}

	public void setRoleId(Role roleId) {
		this.role = roleId;
	}

}
