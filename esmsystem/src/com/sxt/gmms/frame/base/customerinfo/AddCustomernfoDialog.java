package com.sxt.gmms.frame.base.customerinfo;

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

import com.sxt.gmms.dao.base.CustomerDao;
import com.sxt.gmms.entity.Customer;
import com.sxt.gmms.util.PinyinUtil;

/**
 * 添加商品类
 * 
 * @author ming
 * 
 */
public class AddCustomernfoDialog extends JDialog implements ActionListener {

	JButton btnAdd;
	JButton btnCancel;
	private JTextField txtPin;
	private JTextField txtName;
	private JTextField txtComName;
	private JTextField txtPhone;
	CustomerInfoFrame memberInfoFrame;
	private JTextField txtAge;
	private JTextField txtCode;
	private JComboBox listSex;
	private JTextArea txtAddress;
	private JComboBox listStatus;

	/**
	 * Create the dialog.
	 * 
	 * @param goodsInfoFrame
	 */
	public AddCustomernfoDialog(CustomerInfoFrame memberInfoFrame) {
		this.memberInfoFrame = memberInfoFrame;
		setTitle("添加客户");
		setBounds(100, 100, 614, 376);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u5BA2\u6237\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel.setBounds(0, 0, 598, 304);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("客户编号:");
		label.setBounds(34, 26, 54, 20);
		panel.add(label);

		txtCode = new JTextField();
		txtCode.setColumns(10);
		txtCode.setBounds(118, 26, 153, 21);
		panel.add(txtCode);

		JLabel label_1 = new JLabel("拼音码:");
		label_1.setBounds(34, 77, 59, 20);
		panel.add(label_1);

		txtPin = new JTextField();
		txtPin.setText("回车自动生成");
		txtPin.setColumns(10);
		txtPin.setBounds(118, 77, 153, 21);
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

		JLabel label_2 = new JLabel("客户姓名:");
		label_2.setBounds(303, 26, 67, 20);
		panel.add(label_2);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(389, 26, 166, 21);
		panel.add(txtName);

		JLabel label_3 = new JLabel("客户年龄:");
		label_3.setBounds(303, 78, 67, 20);
		panel.add(label_3);

		JLabel label_4 = new JLabel("客户性别:");
		label_4.setBounds(34, 125, 74, 20);
		panel.add(label_4);

		JLabel label_6 = new JLabel("公司名称:");
		label_6.setBounds(303, 178, 67, 20);
		panel.add(label_6);

		txtComName = new JTextField();
		txtComName.setColumns(10);
		txtComName.setBounds(389, 178, 166, 21);
		panel.add(txtComName);

		JLabel label_7 = new JLabel("客户电话:");
		label_7.setBounds(34, 179, 74, 20);
		panel.add(label_7);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(118, 179, 153, 21);
		panel.add(txtPhone);

		JLabel label_9 = new JLabel("客户状态:");
		label_9.setBounds(303, 125, 67, 20);
		panel.add(label_9);

		JLabel label_10 = new JLabel("公司地址:");
		label_10.setBounds(34, 227, 54, 20);
		panel.add(label_10);

		txtAddress = new JTextArea();
		txtAddress.setBounds(115, 230, 440, 61);
		panel.add(txtAddress);

		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(389, 75, 166, 21);
		panel.add(txtAge);

		listSex = new JComboBox();
		listSex.setModel(new DefaultComboBoxModel(new String[] { "男", "女" }));
		listSex.setBounds(118, 125, 153, 21);
		panel.add(listSex);

		listStatus = new JComboBox();
		listStatus.setModel(new DefaultComboBoxModel(
				new String[] { "启用", "注销" }));
		listStatus.setBounds(389, 125, 166, 21);
		panel.add(listStatus);

		btnAdd = new JButton("添加");
		btnAdd.addActionListener(this);
		btnAdd.setBounds(137, 309, 93, 23);
		getContentPane().add(btnAdd);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(367, 309, 93, 23);
		getContentPane().add(btnCancel);

		this.setLocationRelativeTo(this.memberInfoFrame);
		this.setModal(true);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			// 收集信息
			String cusCode = txtCode.getText();
			Pattern p = Pattern.compile("\\d{1,12}");
			Matcher m = p.matcher(txtCode.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "客户编号输入有误,请重新输入!");
				return;
			}
			// 客户名字
			String cusName = txtName.getText();
			p = Pattern
					.compile("[\\u4E00-\\u9FA5\\uF900-\\uFA2D]{1,20}|[a-zA-Z]{1,20}");
			m = p.matcher(txtName.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "名称输入有误请重新输入！");
				return;
			}
			// 客户拼音码
			String cusPym = PinyinUtil.stringArrayToString(PinyinUtil
					.getHeadByString(txtName.getText()));
			// 客户年龄
			p = Pattern.compile("\\d{1,3}");
			m = p.matcher(txtAge.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "客户年龄输入有误请重新输入！");
				return;
			}
			int cusAge = Integer.parseInt(txtAge.getText());
			// 客户公司名称
			p = Pattern
					.compile("[\\u4E00-\\u9FA5\\uF900-\\uFA2D]{1,20}|[a-zA-Z]{1,20}");
			m = p.matcher(txtComName.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "公司名称输入有误,请重新输入!");
				return;
			}
			String cusComName = txtComName.getText();
			// 客户电话
			String cusPhone = txtPhone.getText();
			p = Pattern.compile("1\\d{10}|\\d{3,4}-\\d{8}");
			m = p.matcher(txtPhone.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "客户电话输入有误请重新输入！");
				return;
			}

			int cusSex = listSex.getSelectedItem().equals("男") ? 1 : 0;
			String cusAddre = txtAddress.getText();
			int cusStatus = listStatus.getSelectedItem().equals("启用") ? 1 : 0;

			// 组装
			Customer cus = new Customer(0, cusCode, cusName, cusPym, cusAge,
					cusPhone, cusSex, cusComName, cusAddre, cusStatus);
			// 调用DAO层加到数据库
			CustomerDao cusDao = new CustomerDao();
			cusDao.addCustomer(cus);
			// 刷新
			memberInfoFrame.loadCustomer();
			this.dispose();
		} else if (e.getSource() == btnCancel) {
			this.dispose();
		}
	}
}
