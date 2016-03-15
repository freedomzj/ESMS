package com.sxt.gmms.frame.system.role;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sxt.gmms.dao.system.RoleDao;
import com.sxt.gmms.entity.Role;

public class AddRoleDialog extends JDialog implements ActionListener {
	private JButton btnCancel;
	private JButton btnAdd;
	private JTextArea txtComment;
	private JTextField txtName;
	RoleManagerFrame roleManagerFrame;

	/**
	 * Create the frame.
	 */
	public AddRoleDialog(RoleManagerFrame roleManagerFrame) {
		this.roleManagerFrame = roleManagerFrame;
		setTitle("添加角色");
		setBounds(100, 100, 327, 188);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("角色名称");
		lblNewLabel.setBounds(21, 13, 54, 15);
		getContentPane().add(lblNewLabel);

		txtName = new JTextField();
		txtName.setBounds(102, 10, 193, 21);
		getContentPane().add(txtName);
		txtName.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("角色描述");
		lblNewLabel_1.setBounds(21, 52, 54, 15);
		getContentPane().add(lblNewLabel_1);

		txtComment = new JTextArea();
		txtComment.setBounds(102, 54, 193, 47);
		getContentPane().add(txtComment);

		btnAdd = new JButton("添加");
		btnAdd.addActionListener(this);
		btnAdd.setBounds(21, 123, 93, 23);
		getContentPane().add(btnAdd);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(169, 123, 93, 23);
		getContentPane().add(btnCancel);

		this.setResizable(false);
		this.setLocationRelativeTo(roleManagerFrame);
		this.setModal(true);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			// 收集信息
			String roleName = txtName.getText();
			String roleComment = txtComment.getText();
			// 组装
			Role role = new Role(0, roleName, roleComment, 1);
			// 调用DAO层添加
			RoleDao roleDao = new RoleDao();
			roleDao.addRole(role);
			// 刷新
			roleManagerFrame.loadRole();
			this.dispose();
		} else if (e.getSource() == btnCancel) {
			this.dispose();
		}
	}
}
