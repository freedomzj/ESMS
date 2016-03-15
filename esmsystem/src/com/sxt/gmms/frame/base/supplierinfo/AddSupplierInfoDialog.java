package com.sxt.gmms.frame.base.supplierinfo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.sxt.gmms.dao.base.SupplierInfoDao;
import com.sxt.gmms.entity.Supplie;
import com.sxt.gmms.util.PinyinUtil;

/**
 * 添加商品类
 * 
 * @author ming
 * 
 */
public class AddSupplierInfoDialog extends JDialog implements ActionListener {

	JButton btnAdd;
	JButton btnCancel;
	private JTextField txtCode;
	private JTextField txtPin;
	private JTextField txtName;
	private JTextField txtLinkMan;
	private JTextField txtComment;
	SupplierInfoFrame supplierInfoFrame;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextArea txtAddress;
	private JComboBox listStatus;

	/**
	 * Create the dialog.
	 * 
	 * @param goodsInfoFrame
	 */
	public AddSupplierInfoDialog(SupplierInfoFrame supplierInfoFrame) {
		this.supplierInfoFrame = supplierInfoFrame;
		setTitle("添加供应商");
		setBounds(100, 100, 614, 376);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"),
				"\u4F9B\u5E94\u5546\u4FE1\u606F", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel.setBounds(0, 0, 598, 304);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("供应商编号:");
		label.setBounds(34, 26, 66, 20);
		panel.add(label);

		txtCode = new JTextField();
		txtCode.setColumns(10);
		txtCode.setBounds(108, 26, 153, 21);
		panel.add(txtCode);

		JLabel label_1 = new JLabel("拼音码:");
		label_1.setBounds(34, 78, 54, 20);
		panel.add(label_1);

		txtPin = new JTextField();
		txtPin.setText("回车自动生成");
		txtPin.setColumns(10);
		txtPin.setBounds(108, 78, 153, 21);
		txtPin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtPin.setText(PinyinUtil.stringArrayToString(PinyinUtil
							.getHeadByString(txtName.getText())));
				}
			}
		});
		txtPin.setEditable(false);
		panel.add(txtPin);

		JLabel label_2 = new JLabel("供应商名称:");
		label_2.setBounds(303, 25, 66, 20);
		panel.add(label_2);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(389, 26, 166, 21);
		panel.add(txtName);

		JLabel label_3 = new JLabel("电话:");
		label_3.setBounds(303, 78, 54, 20);
		panel.add(label_3);

		JLabel lblEmail = new JLabel("E_mail:");
		lblEmail.setBounds(34, 175, 54, 20);
		panel.add(lblEmail);

		JLabel label_6 = new JLabel("经理:");
		label_6.setBounds(34, 124, 54, 20);
		panel.add(label_6);

		txtLinkMan = new JTextField();
		txtLinkMan.setColumns(10);
		txtLinkMan.setBounds(108, 124, 153, 21);
		panel.add(txtLinkMan);

		JLabel label_7 = new JLabel("备注:");
		label_7.setBounds(303, 176, 54, 20);
		panel.add(label_7);

		txtComment = new JTextField();
		txtComment.setColumns(10);
		txtComment.setBounds(389, 176, 166, 21);
		panel.add(txtComment);

		JLabel label_9 = new JLabel("状态:");
		label_9.setBounds(303, 125, 54, 20);
		panel.add(label_9);

		JLabel label_10 = new JLabel("地址:");
		label_10.setBounds(34, 227, 54, 20);
		panel.add(label_10);

		txtAddress = new JTextArea();
		txtAddress.setBounds(115, 230, 440, 61);
		panel.add(txtAddress);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(389, 75, 166, 21);
		panel.add(txtPhone);

		listStatus = new JComboBox();
		listStatus.setModel(new DefaultComboBoxModel(new String[] { "未破产",
				"已破产" }));
		listStatus.setBounds(389, 125, 166, 21);
		panel.add(listStatus);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(108, 175, 153, 21);
		panel.add(txtEmail);

		btnAdd = new JButton("添加");
		btnAdd.addActionListener(this);
		btnAdd.setBounds(137, 309, 93, 23);
		getContentPane().add(btnAdd);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(367, 309, 93, 23);
		getContentPane().add(btnCancel);

		this.setLocationRelativeTo(this.supplierInfoFrame);
		this.setModal(true);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			// 收集信息
			String supCode = txtCode.getText();
			Pattern p = Pattern.compile("\\d{1,12}");
			Matcher m = p.matcher(txtCode.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "供应商编号输入有误,请重新输入!");
				return;
			}
			// 供应商名称
			String supName = txtName.getText();
			p = Pattern
					.compile("[\\u4E00-\\u9FA5\\uF900-\\uFA2D]{1,20}|[a-zA-Z]{1,20}");
			m = p.matcher(txtName.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "供应商名称输入有误请重新输入！");
				return;
			}
			// 供应商拼音码
			String supPym = PinyinUtil.stringArrayToString(PinyinUtil
					.getHeadByString(txtName.getText()));
			// 供应商电话
			String supPhone = txtPhone.getText();
			p = Pattern.compile("1\\d{10}|\\d{3,4}-\\d{8}");
			m = p.matcher(txtPhone.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "供应商电话输入有误请重新输入！");
				return;
			}
			// 供应商联系人
			String supLinkman = txtLinkMan.getText();
			p = Pattern
					.compile("[\\u4E00-\\u9FA5\\uF900-\\uFA2D]{1,5}|[a-zA-Z]{1,20}");
			m = p.matcher(txtLinkMan.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "供应商联系人输入有误请重新输入！");
				return;
			}
			// 供应商邮箱
			String supEmail = txtEmail.getText();
			p = Pattern.compile("\\w{4,}@[a-zA-z\\d]+\\.(com|cn|com.cn)");
			m = p.matcher(txtEmail.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "供应商邮箱输入有误请重新输入！");
				return;
			}
			// 供应商地址
			String supAddr = txtAddress.getText();
			// 供应商描述
			String supComment = txtComment.getText();
			int supStatus = listStatus.getSelectedItem().equals("未破产") ? 1 : 0;
			// 组装
			Supplie supplie = new Supplie(0, supCode, supName, supPym,
					supPhone, supLinkman, supEmail, supAddr, supComment,
					supStatus);
			// 调用DAO层添加
			SupplierInfoDao supplieDao = new SupplierInfoDao();
			supplieDao.addSupplierInfo(supplie);
			// 刷新
			supplierInfoFrame.loadSupplierInfo();
			this.dispose();
		} else if (e.getSource() == btnCancel) {
			this.dispose();
		}
	}
}
