package com.sxt.gmms.event;

import java.beans.PropertyVetoException;

import javax.swing.JOptionPane;

import com.sxt.gmms.dao.DBUtil;
import com.sxt.gmms.frame.LoginFrame;
import com.sxt.gmms.frame.MainFrame;
import com.sxt.gmms.frame.system.AboutDialog;
import com.sxt.gmms.frame.system.role.RoleManagerFrame;
import com.sxt.gmms.frame.system.user.UserManagerFrame;
import com.sxt.gmms.util.BackupOracleDatabase;
import com.sxt.gmms.util.BackupSqlServer;

public class SystemEvent {

	private String event;
	private MainFrame mf;
	private String backName;

	public SystemEvent(MainFrame mf, String event) {
		this.event = event;
		this.mf = mf;
	}

	public void doActionEvent() {
		if (event.equals("数据备份")) {
			BackupSqlServer bss = new BackupSqlServer("d:/", "ESMSDB",
					DBUtil.getConn());
			int result = JOptionPane.showConfirmDialog(mf, "确认备份数据吗?",
					"温馨提示", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if(result == JOptionPane.YES_OPTION) {
				backName = bss.backData();
			}
		} else if (event.equals("数据恢复")) {
			BackupSqlServer bss = new BackupSqlServer("d:/", "ESMSDB",
					DBUtil.getConn());
			int result = JOptionPane.showConfirmDialog(mf, "确认恢复数据吗？", "温馨提示",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				bss.recoverData("ESMS.bak");
				JOptionPane.showMessageDialog(mf, "数据恢复成功!");
			}
		} else if (event.equals("角色管理")) {
			try {
				RoleManagerFrame roleFrame = new RoleManagerFrame(mf);
				mf.desktopPane.add(roleFrame);
				roleFrame.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		} else if (event.equals("用户管理")) {
			try {
				UserManagerFrame userFrame = new UserManagerFrame(mf);
				mf.desktopPane.add(userFrame);
				userFrame.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		} else if (event.equals("关于")) {
			try {
				AboutDialog aboutDialog=new AboutDialog(mf);
				mf.desktopPane.add(aboutDialog);
				aboutDialog.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		} else if (event.equals("退出系统")) {
			int result = JOptionPane.showConfirmDialog(mf, "确认退出吗？", "温馨提示",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		} else if (event.equals("注销")) {
			int result = JOptionPane.showConfirmDialog(mf, "确认注销吗？", "温馨提示",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				mf.dispose();
				new LoginFrame();
			}
		}
	}
}
