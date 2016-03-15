package com.sxt.gmms.util;

import java.io.IOException;

/**
 * 数据库备份
 * 
 * @author ming
 * 
 */
public class BackupOracleDatabase {

	private String userName;
	private String userPwd;
	private String dataName;
	private String path;
	private boolean isBack;

	public BackupOracleDatabase(String userName, String userPwd,
			String dataName, String path, boolean bool) {
		this.userName = userName;
		this.userPwd = userPwd;
		this.dataName = dataName;
		this.path = path;
		this.isBack = bool;
	}

	private String getRecoverCommand() {
		StringBuffer sb = new StringBuffer();
		sb.append("imp ");
		sb.append(userName);
		sb.append("/");
		sb.append(userPwd);
		sb.append("@");
		sb.append(dataName);
		sb.append(" file = '");
		sb.append(path);
		sb.append("' full=y");
		System.out.println(sb.toString());
		return sb.toString();
	}

	private String getBackCommand() {
		StringBuffer sb = new StringBuffer();
		sb.append("exp ");
		sb.append(userName);
		sb.append("/");
		sb.append(userPwd);
		sb.append("@");
		sb.append(dataName);
		sb.append(" file = '");
		sb.append(path);
		sb.append("'");
		System.out.println(sb.toString());
		return sb.toString();
	}

	public void exec() {
		try {
			if (isBack) {
				Runtime.getRuntime().exec(getBackCommand());
			} else {
				Runtime.getRuntime().exec(getRecoverCommand());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		BackupOracleDatabase bod = new BackupOracleDatabase("serical",
				"serical", "god", "d:/test.dmp", true);
		bod.exec();
	}
}
