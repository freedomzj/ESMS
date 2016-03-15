package com.sxt.gmms.entity;

/**
 * 角色类
 * 
 * @author ming
 * 
 */
public class Role {

	private int roleId;
	private String roleName;
	private String roleDescr;
	private int roleStatus;

	public Role() {
	}

	public Role(int roleId, String roleName, String roleDescr, int roleStatus) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDescr = roleDescr;
		this.roleStatus = roleStatus;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescr() {
		return roleDescr;
	}

	public void setRoleDescr(String roleDescr) {
		this.roleDescr = roleDescr;
	}

	public int getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(int roleStatus) {
		this.roleStatus = roleStatus;
	}

}
