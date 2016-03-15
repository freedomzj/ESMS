package com.sxt.gmms.frame.base.employeeinfo;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.sxt.gmms.dao.base.EmployeeInfoDao;
import com.sxt.gmms.entity.Employee;
import com.sxt.gmms.frame.MainFrame;
import com.sxt.gmms.util.TableSetUtil;

/**
 * 商品信息类
 * 
 * @author ming
 * 
 */
public class EmployeeInfoFrame extends JInternalFrame implements ActionListener {

	private JTable table;
	private JTextField txtPinying;
	private JButton btnAdd;
	private JButton btnDel;
	private JButton btnEdit;
	private JButton btnExit;
	private JButton btnRefresh;
	MainFrame mf;

	/**
	 * Create the frame.
	 */
	public EmployeeInfoFrame(MainFrame mf) {
		super("员工资料", true, true, false, true);
		this.mf = mf;
		setBounds(100, 100, 737, 495);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 721, 72);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("员工资料");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("隶书", Font.BOLD | Font.ITALIC, 36));
		lblNewLabel.setBounds(0, 0, 723, 72);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 73, 721, 340);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 721, 340);
		panel_1.add(scrollPane);

		// 表头
		String[] header = { "编号", "姓名", "拼音码", "年龄", "性别", "基本工资", "电话", "地址",
				"状态", "入职时间" };
		// 数据模型
		DefaultTableModel dtm = new DefaultTableModel(null, header);
		table = new JTable(dtm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		String[] text = { "编号", "姓名", "拼音码", "性别", "地址", "状态", "入职时间" };
		String[] number = { "年龄", "电话", "基本工资" };
		TableSetUtil.setTextCellRenderer(table, text, number);
		scrollPane.setViewportView(table);

		btnAdd = new JButton("增加");
		btnAdd.addActionListener(this);
		btnAdd.setBounds(391, 429, 64, 23);
		getContentPane().add(btnAdd);

		btnEdit = new JButton("修改");
		btnEdit.addActionListener(this);
		btnEdit.setBounds(472, 429, 64, 23);
		getContentPane().add(btnEdit);

		btnDel = new JButton("删除");
		btnDel.addActionListener(this);
		btnDel.setBounds(553, 429, 64, 23);
		getContentPane().add(btnDel);

		btnExit = new JButton("退出");
		btnExit.addActionListener(this);
		btnExit.setBounds(634, 429, 64, 23);
		getContentPane().add(btnExit);

		btnRefresh = new JButton("刷新");
		btnRefresh.addActionListener(this);
		btnRefresh.setBounds(310, 429, 64, 23);
		getContentPane().add(btnRefresh);

		JLabel lblNewLabel_1 = new JLabel("支持拼音首字母查询：");
		lblNewLabel_1.setBounds(17, 433, 122, 19);
		getContentPane().add(lblNewLabel_1);

		txtPinying = new JTextField();
		txtPinying.setBounds(156, 430, 137, 21);
		txtPinying.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String pym = txtPinying.getText().toUpperCase();
					if (pym.equals("")) {
						loadEmpioyeeInfo();
					} else {
						EmployeeInfoDao employeeDao = new EmployeeInfoDao();
						loadEmployee(employeeDao.findEmployeeInfoByPinyin(pym));
						txtPinying.setText("");
						txtPinying.requestFocus();
					}
				}
			}
		});
		getContentPane().add(txtPinying);
		txtPinying.setColumns(10);

		// 启动加载
		loadEmpioyeeInfo();

		int width = (mf.getWidth() - this.getWidth() - 200) / 2;
		int height = (mf.getHeight() - this.getHeight() - 140) / 2;
		this.setLocation(width, height);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * 加载到table
	 */
	public void loadEmpioyeeInfo() {
		EmployeeInfoDao empDao = new EmployeeInfoDao();
		List<Employee> empList = empDao.loadEmployeeInfoList();
		loadEmployee(empList);
	}

	/**
	 * 加载指定list到table
	 * 
	 * @param empList
	 */
	private void loadEmployee(List<Employee> empList) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		for (Employee emp : empList) {
			dtm.addRow(new Object[] { emp.getEmpCode(), emp.getEmpName(),
					emp.getEmpPym(), emp.getEmpAge(),
					emp.getEmpSex() == 1 ? "男" : "女", emp.getEmpBaseSal(),
					emp.getEmpPhone(), emp.getEmpAddre(),
					emp.getEmpStatus() == 1 ? "在职" : "离职", emp.getEmpInDate() });
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			new AddEmployeeInfoDialog(this);
		} else if (e.getSource() == btnEdit) {
			// 获得选中行
			int rowCount = table.getSelectedRow();
			if (rowCount == -1) {
				JOptionPane.showMessageDialog(this, "请选择要修改的员工!", "温馨提示",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			String empCode = (String) table.getValueAt(rowCount, 0);
			new EditEmployeeInfoDialog(this, empCode);
		} else if (e.getSource() == btnDel) {
			// 获得选中行
			int rowCount = table.getSelectedRow();
			if (rowCount == -1) {
				JOptionPane.showMessageDialog(this, "请选择要删除的员工!", "温馨提示",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			String empCode = (String) table.getValueAt(rowCount, 0);
			int result = JOptionPane.showConfirmDialog(this,
					"确认删除 " + table.getValueAt(rowCount, 1) + " 的信息吗？", "温馨提示",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				// 调用DAO层删除
				EmployeeInfoDao empDao = new EmployeeInfoDao();
				empDao.delEmployeeInfo(empCode);
				// 刷新
				loadEmpioyeeInfo();
			}
		} else if (e.getSource() == btnRefresh) {
			loadEmpioyeeInfo();
		} else if (e.getSource() == btnExit) {
			this.dispose();
		}
	}
}
