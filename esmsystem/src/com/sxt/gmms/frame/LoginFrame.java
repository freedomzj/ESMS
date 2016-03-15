package com.sxt.gmms.frame;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.button.ClassicButtonShaper;

import com.sxt.gmms.dao.system.UserDao;
import com.sxt.gmms.entity.User;

public class LoginFrame extends JFrame implements ActionListener {
	private JButton btnLogin;
	private JButton btnCancel;
	private JLabel lblLogin;
	private JTextField txtAccount;
	private JPasswordField txtPwd;
	User user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 318);

		this.setIconImage(new ImageIcon("image/main/ico.png").getImage());
		this.setResizable(false);
		getContentPane().setLayout(null);

		btnLogin = new JButton("登录");
		btnLogin.addActionListener(this);
		btnLogin.setBounds(239, 193, 72, 25);
		getContentPane().add(btnLogin);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(323, 192, 72, 25);
		getContentPane().add(btnCancel);

		int width = (Toolkit.getDefaultToolkit().getScreenSize().width - this
				.getWidth()) / 2;
		int height = (Toolkit.getDefaultToolkit().getScreenSize().height - this
				.getHeight()) / 2;
		this.setLocation(width, height);

		this.getRootPane().setDefaultButton(btnLogin);

		lblLogin = new JLabel("");
		lblLogin.setBounds(0, 0, 513, 290);
		lblLogin.setIcon(new ImageIcon("image/main/login.jpg"));
		getContentPane().add(lblLogin);

		txtAccount = new JTextField();
		txtAccount.setFont(new Font("Courier New", txtAccount.getFont()
				.getStyle() | Font.BOLD, 16));
		txtAccount.setColumns(10);
		txtAccount.setBounds(236, 130, 162, 25);
		getContentPane().add(txtAccount);

		txtPwd = new JPasswordField();
		txtPwd.setFont(new Font("Courier New", txtPwd.getFont().getStyle()
				| Font.BOLD, 16));
		txtPwd.setBounds(235, 164, 165, 25);
		getContentPane().add(txtPwd);
		// 设置按钮的形状
		SubstanceLookAndFeel.setCurrentButtonShaper(new ClassicButtonShaper());
		this.setVisible(true);
		txtAccount.requestFocus();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogin) {
			if (txtAccount.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "请输入帐号!", "系统提示",
						JOptionPane.WARNING_MESSAGE);
				txtAccount.setText("");
				txtPwd.setText("");
				txtAccount.requestFocus();
				return;
			} else if (new String(txtPwd.getPassword()).equals("")) {
				JOptionPane.showMessageDialog(this, "请输入密码!", "系统提示",
						JOptionPane.WARNING_MESSAGE);
				txtPwd.setText("");
				txtPwd.requestFocus();
				return;
			} else {
				String userAccount = txtAccount.getText().trim();
				String userPwd = new String(txtPwd.getPassword());
				user = new User(0, userAccount, userPwd, 1, null, null);
				UserDao userDao = new UserDao();
				user = userDao.confirmUser(user);
				if (user == null) {
					JOptionPane.showMessageDialog(this, "帐号或者密码错误,请重新输入!",
							"系统提示", JOptionPane.WARNING_MESSAGE);
					txtAccount.setText("");
					txtPwd.setText("");
					txtAccount.requestFocus();
				} else {
					this.dispose();
					new MainFrame(user);
				}
			}
		} else if (e.getSource() == btnCancel) {
			System.exit(0);
		}
	}
}
