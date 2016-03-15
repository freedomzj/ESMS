package com.sxt.gmms.frame.system.user;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.sxt.gmms.dao.system.UserDao;
import com.sxt.gmms.entity.User;
import com.sxt.gmms.frame.MainFrame;
import com.sxt.gmms.util.TableSetUtil;

public class UserManagerFrame extends JInternalFrame implements ActionListener {
	private JTable table;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDel;
	private JButton btnCancel;
	private MainFrame mf;

	/**
	 * Create the frame.
	 */
	public UserManagerFrame(MainFrame mf) {
		super("用户管理", true, true, false, true);
		this.mf = mf;
		setBounds(100, 100, 658, 498);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 642, 42);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("用户管理");
		lblNewLabel.setFont(new Font("隶书", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 640, 42);
		panel.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 43, 642, 383);
		getContentPane().add(scrollPane);

		String[] header = { "编号", "帐号", "密码", "状态", "描述", "权限" };
		DefaultTableModel dtm = new DefaultTableModel(null, header);
		table = new JTable(dtm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		String[] text = { "编号", "帐号", "密码", "状态", "描述", "权限" };
		TableSetUtil.setTextCellRenderer(table, text, null);
		scrollPane.setViewportView(table);

		btnAdd = new JButton("增加");
		btnAdd.addActionListener(this);
		btnAdd.setBounds(54, 436, 93, 23);
		getContentPane().add(btnAdd);

		btnEdit = new JButton("修改");
		btnEdit.addActionListener(this);
		btnEdit.setBounds(201, 436, 93, 23);
		getContentPane().add(btnEdit);

		btnDel = new JButton("删除");
		btnDel.addActionListener(this);
		btnDel.setBounds(348, 436, 93, 23);
		getContentPane().add(btnDel);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(495, 436, 93, 23);
		getContentPane().add(btnCancel);

		// 启动加载
		loadUser();

		int width = (this.mf.getWidth() - this.getWidth() - 200) / 2;
		int height = (this.mf.getHeight() - this.getHeight() - 140) / 2;
		this.setLocation(width, height);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * 加载
	 */
	public void loadUser() {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		UserDao userDao = new UserDao();
		List<User> userList = userDao.loadUserList();
		dtm.setRowCount(0);
		for (User user : userList) {
			dtm.addRow(new Object[] { user.getUserId(), user.getUserAccount(),
					user.getUserPwd(), user.getUserStatus() == 1 ? "启用" : "注销",
					user.getUserComm(), user.getRoleId().getRoleName() });
		}
	}

	/**
	 * 事件处理
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			new AddUserDialog(this);
		} else if (e.getSource() == btnEdit) {
			int rowCount = table.getSelectedRow();
			if (rowCount == -1) {
				JOptionPane.showMessageDialog(this, "请选择要修改的用户!", "温馨提示",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			int userId = (Integer) table.getValueAt(rowCount, 0);
			new EditUserDialog(this, userId);
		} else if (e.getSource() == btnDel) {
			int rowCount = table.getSelectedRow();
			if (rowCount == -1) {
				JOptionPane.showMessageDialog(this, "请选择要删除的用户!", "温馨提示",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			int userId = (Integer) table.getValueAt(rowCount, 0);
			int result = JOptionPane.showConfirmDialog(this,
					"确认删除 " + table.getValueAt(rowCount, 1), "温馨提示",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				// 调用DAD层删除
				UserDao userDao = new UserDao();
				userDao.delUser(userId);
				loadUser();
			}
		} else if (e.getSource() == btnCancel) {
			this.dispose();
		}
	}
}
