package com.sxt.gmms.frame.instorage.instorage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.sxt.gmms.dao.base.EmployeeInfoDao;
import com.sxt.gmms.dao.instorage.InStorageDao;
import com.sxt.gmms.entity.Employee;
import com.sxt.gmms.entity.Goods;
import com.sxt.gmms.entity.InStorage;
import com.sxt.gmms.entity.InStorageItem;
import com.sxt.gmms.frame.MainFrame;
import com.sxt.gmms.util.AutoCodeUtil;
import com.sxt.gmms.util.DateUI;
import com.sxt.gmms.util.DateUtil;
import com.sxt.gmms.util.TableSetUtil;

public class InStorageFrame extends JInternalFrame implements ActionListener {
	private JTextField txtDate;
	private JTextField txtCode;
	private JTextField txtMoney;
	private JTable table;
	private JComboBox listPeople;
	private JButton btnReset;
	private JButton btnCancel;
	private JButton btnSave;
	private JButton btnAddGood;
	MainFrame mf;

	/**
	 * Create the frame.
	 */
	public InStorageFrame(MainFrame mf) {
		super("采购入库", true, true, false, true);
		this.mf = mf;
		setBounds(100, 100, 800, 553);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 67, 784, 409);
		getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 784, 408);
		panel.add(scrollPane);

		// 表头
		String[] header = { "产品编号", "产品名称", "数量", "单价", "金额" };
		// 数据模型
		DefaultTableModel dtm = new DefaultTableModel(null, header);

		table = new JTable(dtm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		String[] text = { "产品编号", "产品名称" };
		String[] number = { "数量", "单价", "金额" };
		TableSetUtil.setTextCellRenderer(table, text, number);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("编号:");
		lblNewLabel.setBounds(10, 22, 43, 15);
		getContentPane().add(lblNewLabel);

		JLabel label = new JLabel("入库时间:");
		label.setBounds(163, 21, 66, 15);
		getContentPane().add(label);

		JLabel label_1 = new JLabel("经手人:");
		label_1.setBounds(394, 22, 54, 15);
		getContentPane().add(label_1);

		JLabel label_2 = new JLabel("总金额:");
		label_2.setBounds(562, 22, 54, 15);
		getContentPane().add(label_2);

		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setBounds(222, 19, 97, 21);
		getContentPane().add(txtDate);
		txtDate.setColumns(10);

		JButton btnChooseDate = new JButton("...");
		btnChooseDate.setBounds(329, 17, 43, 23);
		btnChooseDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DateUI dateUI = DateUI.getInstence(true, txtDate,
						e.getXOnScreen() - txtDate.getWidth(), e.getYOnScreen()
								- txtDate.getHeight() + 40);
			}
		});
		getContentPane().add(btnChooseDate);

		txtCode = new JTextField();
		txtCode.setEditable(false);
		txtCode.setBounds(45, 19, 108, 21);
		getContentPane().add(txtCode);
		txtCode.setColumns(10);

		listPeople = new JComboBox();
		listPeople.setBounds(458, 20, 82, 21);
		getContentPane().add(listPeople);

		txtMoney = new JTextField();
		txtMoney.setText("" + 0);
		txtMoney.setEditable(false);
		txtMoney.setBounds(626, 19, 82, 21);
		getContentPane().add(txtMoney);
		txtMoney.setColumns(10);

		btnReset = new JButton("重置");
		btnReset.addActionListener(this);
		btnReset.setBounds(71, 491, 93, 23);
		getContentPane().add(btnReset);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(235, 491, 93, 23);
		getContentPane().add(btnCancel);

		btnSave = new JButton("保存");
		btnSave.addActionListener(this);
		btnSave.setBounds(399, 491, 93, 23);
		getContentPane().add(btnSave);

		btnAddGood = new JButton("添加入库商品");
		btnAddGood.addActionListener(this);
		btnAddGood.setBounds(563, 491, 111, 23);
		getContentPane().add(btnAddGood);

		// 启动就加载经手人
		List<Employee> empList = new ArrayList<Employee>();
		EmployeeInfoDao empDao = new EmployeeInfoDao();
		empList = empDao.loadEmployeeInfoList();
		for (Employee emp : empList) {
			listPeople.addItem(emp.getEmpName());
		}

		// 界面出现就加载编号
		txtCode.setText(AutoCodeUtil.createOrderCode("GM"));

		int width = (mf.getWidth() - this.getWidth() - 200) / 2;
		int height = (mf.getHeight() - this.getHeight() - 140) / 2;
		this.setLocation(width, height);
		this.setResizable(false);
		this.setVisible(true);
	}

	// 加载选择的商品和数量到入库table
	public void loadGoodsAndQty(Goods goods, int qty) {
		// 金额
		float qtyPrice = goods.getGoodsPrice() * qty;
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.addRow(new Object[] { goods.getGoodsCode(), goods.getGoodsName(),
				qty, goods.getGoodsPrice(), qtyPrice });
		float money = Float.parseFloat(txtMoney.getText()) + qtyPrice;
		txtMoney.setText("" + money);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAddGood) {
			new AddGoodDialog(this);
		} else if (e.getSource() == btnSave) {
			// 用来装入库明细的list
			List<InStorageItem> itemList = new ArrayList<InStorageItem>();
			// 获得数据模型
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			for (int i = 0; i < dtm.getRowCount(); i++) {
				String goodsCode = (String) dtm.getValueAt(i, 0);
				// 组装称Goods
				Goods goods = new Goods();
				goods.setGoodsCode(goodsCode);

				// 组装入库明细,数量
				InStorageItem inStorageItem = new InStorageItem();
				int goodsQty = (Integer) dtm.getValueAt(i, 2);
				// 外键，产品
				inStorageItem.setGoods(goods);
				// 产品数量
				inStorageItem.setInItemQty(goodsQty);
				// 产品的单价
				float goodsPrice = (Float) dtm.getValueAt(i, 3);
				float inItemPrice = goodsQty * goodsPrice;
				// 一条明细的总价
				inStorageItem.setInItemPrice(inItemPrice);
				itemList.add(inStorageItem);
			}

			String inCode = txtCode.getText();
			// 入库单编号为空处理
			if (inCode == null || inCode.equals("")) {
				JOptionPane.showMessageDialog(this, "清添加入库编号", "温馨提示",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			// 入库单日期为空处理
			if (txtDate.getText() == null || txtDate.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "清选择入库时间", "温馨提示",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			Date inDate = DateUtil.formatStringToDate(txtDate.getText());
			Employee emp = new Employee();
			emp.setEmpName(listPeople.getSelectedItem().toString());
			// 组装入库单
			InStorage inStorage = new InStorage(0, inCode, inDate, 0, emp);
			// 调用Dao层加到数据库
			if (itemList.isEmpty()) {
				JOptionPane.showMessageDialog(this, "清选择要添加的商品", "温馨提示",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			InStorageDao inStorageDao = new InStorageDao();
			inStorageDao.addInStorageAndItem(inStorage, itemList);
			// 关闭
			this.dispose();
		} else if (e.getSource() == btnReset) {
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.setRowCount(0);
			table.setModel(dtm);
		} else if (e.getSource() == btnCancel) {
			this.dispose();
		}
	}
}
