package com.sxt.gmms.frame.system.role;

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

import com.sxt.gmms.dao.system.RoleDao;
import com.sxt.gmms.entity.Role;
import com.sxt.gmms.frame.MainFrame;
import com.sxt.gmms.util.TableSetUtil;

public class RoleManagerFrame extends JInternalFrame implements ActionListener {
	private JTable table;
	private JButton btnCancel;
	private JButton btnAdd;
	private JButton btnDel;
	private JButton btnEdit;
	private MainFrame mf;

	/**
	 * Create the frame.
	 */
	public RoleManagerFrame(MainFrame mf) {
		super("角色管理", true, true, false, true);
		this.mf = mf;
		setBounds(100, 100, 651, 464);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 635, 35);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("隶书", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 634, 35);
		panel.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 33, 635, 346);
		getContentPane().add(scrollPane);

		// 表头
		String[] header = { "角色名称", "权限描述" };
		// 数据模型
		DefaultTableModel dtm = new DefaultTableModel(null, header);

		table = new JTable(dtm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		String[] text = { "角色名称", "权限描述" };
		TableSetUtil.setTextCellRenderer(table, text, null);
		scrollPane.setViewportView(table);

		btnAdd = new JButton("添加");
		btnAdd.addActionListener(this);
		btnAdd.setBounds(42, 389, 86, 23);
		getContentPane().add(btnAdd);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(426, 389, 86, 23);
		getContentPane().add(btnCancel);

		btnDel = new JButton("删除");
		btnDel.addActionListener(this);
		btnDel.setBounds(298, 389, 86, 23);
		getContentPane().add(btnDel);

		btnEdit = new JButton("修改");
		btnEdit.addActionListener(this);
		btnEdit.setBounds(170, 389, 86, 23);
		getContentPane().add(btnEdit);

		// 启动加载
		loadRole();

		int width = (this.mf.getWidth() - this.getWidth() - 200) / 2;
		int height = (this.mf.getHeight() - this.getHeight() - 140) / 2;
		this.setLocation(width, height);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * 加载role到table
	 */
	public void loadRole() {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		RoleDao roleDao = new RoleDao();
		List<Role> roleList = roleDao.loadRoleList();
		dtm.setRowCount(0);
		for (Role role : roleList) {
			dtm.addRow(new Object[] { role.getRoleName(), role.getRoleDescr() });
		}
	}

	/**
	 * 事件处理
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			new AddRoleDialog(this);
		} else if (e.getSource() == btnEdit) {
			int rowCount = table.getSelectedRow();
			if (rowCount == -1) {
				JOptionPane.showMessageDialog(this, "请选择要修改的角色!", "温馨提示",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			String roleName = (String) table.getValueAt(rowCount, 0);
			new EditRoleDialog(this, roleName);
		} else if (e.getSource() == btnDel) {
			int rowCount = table.getSelectedRow();
			if (rowCount == -1) {
				JOptionPane.showMessageDialog(this, "请选择要删除的角色!", "温馨提示",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			String roleName = (String) table.getValueAt(rowCount, 0);
			int result = JOptionPane.showConfirmDialog(this,
					"确认删除	" + roleName + "	的信息吗?", "温馨提示",
					JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				RoleDao roleDao = new RoleDao();
				roleDao.delRole(roleName);
				loadRole();
			}
		} else if (e.getSource() == btnCancel) {
			this.dispose();
		}
	}
}
