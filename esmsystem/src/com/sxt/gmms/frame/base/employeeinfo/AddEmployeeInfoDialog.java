package com.sxt.gmms.frame.base.employeeinfo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
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

import com.sxt.gmms.dao.base.EmployeeInfoDao;
import com.sxt.gmms.entity.Employee;
import com.sxt.gmms.util.DateUI;
import com.sxt.gmms.util.DateUtil;
import com.sxt.gmms.util.PinyinUtil;

/**
 * 添加商品类
 * 
 * @author ming
 * 
 */
public class AddEmployeeInfoDialog extends JDialog implements ActionListener {

	JButton btnAdd;
	JButton btnCancel;
	private JTextField txtCode;
	private JTextField txtPin;
	private JTextField txtName;
	private JTextField txtSal;
	private JTextField txtPhone;
	EmployeeInfoFrame employeeInfoFrame;
	private JTextField txtAge;
	private JComboBox listSex;
	private JComboBox listStatus;
	private JTextArea txtAddress;
	private final JTextField txtDate;

	/**
	 * Create the dialog.
	 * 
	 * @param goodsInfoFrame
	 */
	public AddEmployeeInfoDialog(EmployeeInfoFrame employeeInfoFrame) {
		this.employeeInfoFrame = employeeInfoFrame;
		setTitle("添加员工");
		setBounds(100, 100, 614, 427);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u5458\u5DE5\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel.setBounds(0, 0, 598, 346);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("员工编号:");
		label.setBounds(34, 26, 54, 20);
		panel.add(label);

		txtCode = new JTextField();
		txtCode.setColumns(10);
		txtCode.setBounds(108, 26, 153, 21);
		panel.add(txtCode);

		JLabel label_1 = new JLabel("拼音码:");
		label_1.setBounds(34, 77, 54, 20);
		panel.add(label_1);

		txtPin = new JTextField();
		txtPin.setText("回车自动生成");
		txtPin.setColumns(10);
		txtPin.setBounds(108, 77, 153, 21);
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

		JLabel label_2 = new JLabel("员工姓名:");
		label_2.setBounds(303, 26, 54, 20);
		panel.add(label_2);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(386, 26, 169, 21);
		panel.add(txtName);

		JLabel label_3 = new JLabel("员工年龄:");
		label_3.setBounds(303, 78, 54, 20);
		panel.add(label_3);

		JLabel label_4 = new JLabel("员工性别:");
		label_4.setBounds(34, 176, 54, 20);
		panel.add(label_4);

		JLabel label_6 = new JLabel("基本工资:");
		label_6.setBounds(34, 125, 54, 20);
		panel.add(label_6);

		txtSal = new JTextField();
		txtSal.setColumns(10);
		txtSal.setBounds(108, 125, 153, 21);
		panel.add(txtSal);

		JLabel label_7 = new JLabel("员工电话:");
		label_7.setBounds(303, 124, 54, 20);
		panel.add(label_7);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(389, 124, 166, 21);
		panel.add(txtPhone);

		JLabel label_9 = new JLabel("员工状态:");
		label_9.setBounds(303, 175, 54, 20);
		panel.add(label_9);

		JLabel lblYuangong = new JLabel("员工备注:");
		lblYuangong.setBounds(34, 260, 54, 20);
		panel.add(lblYuangong);

		txtAddress = new JTextArea();
		txtAddress.setBounds(108, 263, 447, 61);
		panel.add(txtAddress);

		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(389, 75, 166, 21);
		panel.add(txtAge);

		listSex = new JComboBox();
		listSex.setModel(new DefaultComboBoxModel(new String[] { "男", "女" }));
		listSex.setBounds(108, 176, 153, 21);
		panel.add(listSex);

		listStatus = new JComboBox();
		listStatus.setModel(new DefaultComboBoxModel(
				new String[] { "在职", "离职" }));
		listStatus.setBounds(389, 175, 166, 21);
		panel.add(listStatus);

		JLabel label_5 = new JLabel("入职时间:");
		label_5.setBounds(34, 216, 54, 20);
		panel.add(label_5);

		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setBounds(108, 216, 350, 21);
		panel.add(txtDate);
		txtDate.setColumns(10);

		JButton btnChooseTime = new JButton("选择时间");
		btnChooseTime.addActionListener(this);
		btnChooseTime.setBounds(462, 215, 93, 23);
		btnChooseTime.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DateUI dateUI = DateUI.getInstence(true, txtDate,
						e.getXOnScreen() - txtDate.getWidth(), e.getYOnScreen()
								- txtDate.getHeight());
			}
		});
		panel.add(btnChooseTime);

		btnAdd = new JButton("添加");
		btnAdd.addActionListener(this);
		btnAdd.setBounds(139, 356, 93, 23);
		getContentPane().add(btnAdd);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(371, 356, 93, 23);
		getContentPane().add(btnCancel);

		this.setLocationRelativeTo(this.employeeInfoFrame);
		this.setModal(true);
		this.setVisible(true);
	}

	/**
	 * 事件处理
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			// 收集信息
			String empCode = txtCode.getText();
			Pattern p = Pattern.compile("\\d{1,12}");
			Matcher m = p.matcher(txtCode.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "员工编号输入有误,请重新输入!");
				return;
			}
			// 员工名称
			String empName = txtName.getText();
			p = Pattern
					.compile("[\\u4E00-\\u9FA5\\uF900-\\uFA2D]{1,20}|[a-zA-Z]{1,20}");
			m = p.matcher(txtName.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "员工名称输入有误请重新输入！");
				return;
			}
			// 员工拼音码
			String empPym = PinyinUtil.stringArrayToString(PinyinUtil
					.getHeadByString(txtName.getText()));
			// 员工年龄
			p = Pattern.compile("\\d{1,3}");
			m = p.matcher(txtAge.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "员工年龄输入有误请重新输入！");
				return;
			}
			int empAge = Integer.parseInt(txtAge.getText());
			// 员工基本工资
			p = Pattern.compile("\\d*\\.?\\d+");
			m = p.matcher(txtSal.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "员工基本工资输入有误,请重新输入!");
				return;
			}
			float empBaseSal = Float.parseFloat(txtSal.getText());
			// 员工电话
			String empPhone = txtPhone.getText();
			p = Pattern.compile("1\\d{10}|\\d{3,4}-\\d{8}");
			m = p.matcher(txtPhone.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "员工电话输入有误请重新输入！");
				return;
			}
			int empSex = listSex.getSelectedItem().equals("男") ? 1 : 0;
			String empAddre = txtAddress.getText();
			// 入职时间
			if (txtDate.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "员工入职时间输入有误请重新输入！");
				return;
			}
			Date empInDate = DateUtil.formatStringToDate(txtDate.getText());
			int empStatus = listStatus.getSelectedItem().equals("在职") ? 1 : 0;
			// 组装
			Employee emp = new Employee(0, empCode, empName, empPym, empAge,
					empBaseSal, empPhone, empSex, empAddre, empInDate,
					empStatus);
			// 调用DAO层添加
			EmployeeInfoDao empDao = new EmployeeInfoDao();
			empDao.addEmployeeInfo(emp);
			// 刷新
			employeeInfoFrame.loadEmpioyeeInfo();
			this.dispose();
		} else if (e.getSource() == btnCancel) {
			this.dispose();
		}
	}
}
