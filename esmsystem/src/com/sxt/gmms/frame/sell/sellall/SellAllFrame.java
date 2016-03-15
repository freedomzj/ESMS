package com.sxt.gmms.frame.sell.sellall;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.sxt.gmms.dao.base.EmployeeInfoDao;
import com.sxt.gmms.dao.sale.SellAllDao;
import com.sxt.gmms.dao.sale.SellItemDao;
import com.sxt.gmms.dao.stored.StoredDao;
import com.sxt.gmms.entity.Employee;
import com.sxt.gmms.entity.Sell;
import com.sxt.gmms.entity.SellItem;
import com.sxt.gmms.frame.MainFrame;
import com.sxt.gmms.util.DateUI;
import com.sxt.gmms.util.DateUtil;
import com.sxt.gmms.util.TableSetUtil;
import javax.swing.UIManager;

public class SellAllFrame extends JInternalFrame implements ActionListener {

	private JPanel contentPane;
	private MainFrame mf;
	private JTable allTable;
	private JTable itemTable;
	private JTextField txtStartDate;
	private JTextField txtEndDate;
	private JComboBox listEmployee;
	private JButton btnReset;
	private JButton btnQuery;

	/**
	 * Create the frame.
	 */
	public SellAllFrame(MainFrame mf) {
		super("出库汇总", true, true, false, true);
		this.mf = mf;
		setBounds(100, 100, 700, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 684, 53);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel();
		label.setText("经手人");
		label.setBounds(6, 24, 65, 18);
		panel.add(label);

		listEmployee = new JComboBox();
		listEmployee.setModel(new DefaultComboBoxModel(new String[] { "" }));
		listEmployee.setBounds(77, 20, 87, 22);
		panel.add(listEmployee);

		JLabel label_1 = new JLabel();
		label_1.setText("日期");
		label_1.setBounds(170, 24, 41, 18);
		panel.add(label_1);

		txtStartDate = new JTextField();
		txtStartDate.setEditable(false);
		txtStartDate.setBounds(217, 22, 102, 22);
		panel.add(txtStartDate);

		JLabel label_2 = new JLabel();
		label_2.setText("至");
		label_2.setBounds(361, 24, 29, 18);
		panel.add(label_2);

		txtEndDate = new JTextField();
		txtEndDate.setEditable(false);
		txtEndDate.setBounds(396, 22, 102, 22);
		panel.add(txtEndDate);

		btnQuery = new JButton();
		btnQuery.addActionListener(this);
		btnQuery.setText("查询");
		btnQuery.setBounds(540, 21, 66, 25);
		panel.add(btnQuery);

		btnReset = new JButton();
		btnReset.addActionListener(this);
		btnReset.setText("重置");
		btnReset.setBounds(612, 21, 66, 25);
		panel.add(btnReset);

		JButton btnStartDate = new JButton();
		btnStartDate.setText("...");
		btnStartDate.setBounds(325, 20, 30, 22);
		btnStartDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DateUI dateUI = DateUI.getInstence(true, txtStartDate,
						e.getXOnScreen() - txtStartDate.getWidth(),
						e.getYOnScreen() - txtStartDate.getHeight() + 40);
			}
		});
		panel.add(btnStartDate);

		JButton btnEndDate = new JButton();
		btnEndDate.setText("...");
		btnEndDate.setBounds(504, 22, 30, 22);
		btnEndDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DateUI dateUI = DateUI.getInstence(true, txtEndDate,
						e.getXOnScreen() - txtEndDate.getWidth(),
						e.getYOnScreen() - txtEndDate.getHeight() + 40);
			}
		});
		panel.add(btnEndDate);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "销售单",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0,
						0)));
		panel_1.setBounds(0, 63, 684, 203);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);

		// 表头
		String[] header = { "出库编号", "出库日期", "操作员", "审核状态 " };
		// 数据模型
		DefaultTableModel allDtm = new DefaultTableModel(null, header);
		allTable = new JTable(allDtm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		String[] text = { "出库编号", "出库日期", "操作员", "审核状态 " };
		TableSetUtil.setTextCellRenderer(allTable, text, null);
		allTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowCount = allTable.getSelectedRow();
				String sellCode = (String) allTable.getValueAt(rowCount, 0);
				SellItemDao itemDao = new SellItemDao();

				List<SellItem> itemList = itemDao.loadSellItemByCode(sellCode);
				loadAllItemList(itemList);

				if (e.getClickCount() == 2) {
					SellAllDao sellDao = new SellAllDao();
					if (sellDao.findStatusByCode(sellCode) == 1) {
						JOptionPane
								.showMessageDialog(SellAllFrame.this,
										"该项已通过审核!", "温馨提示",
										JOptionPane.WARNING_MESSAGE);
						return;
					}
					int result = JOptionPane.showConfirmDialog(
							SellAllFrame.this, "确定通过审核吗？", "验证审核",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
						// 调用Dao层批量更改库存中的商品数量
						StoredDao storedDao = new StoredDao();
						storedDao.editStockQty(itemList);
						// 更新状态
						sellDao.updateStatus(sellCode);
						// 重新加载
						loadAll();
					}
				}
			}

		});
		scrollPane.setViewportView(allTable);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "销售明细",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0,
						0)));
		panel_2.setBounds(0, 276, 684, 245);
		contentPane.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, BorderLayout.CENTER);

		// 表头
		String[] itemHeader = { "产品编号", "产品名称", "产品数量", "产品单价", "总金额" };
		// 数据模型
		DefaultTableModel itemDtm = new DefaultTableModel(null, itemHeader);
		itemTable = new JTable(itemDtm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		String[] textItem = { "产品编号", "产品名称", "产品数量" };
		String[] numberItem = { "产品单价", "总金额" };
		TableSetUtil.setTextCellRenderer(itemTable, textItem, numberItem);
		scrollPane_1.setViewportView(itemTable);

		// 启动就加载经手人
		List<Employee> empList = new ArrayList<Employee>();
		EmployeeInfoDao empDao = new EmployeeInfoDao();
		empList = empDao.loadEmployeeInfoList();
		for (Employee emp : empList) {
			listEmployee.addItem(emp.getEmpName());
		}

		// 启动就加载信息
		loadAll();

		int width = (this.mf.getWidth() - this.getWidth() - 200) / 2;
		int height = (this.mf.getHeight() - this.getHeight() - 140) / 2;
		this.setLocation(width, height);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * 加载所有的入库系信息
	 */
	public void loadAll() {
		SellAllDao allDao = new SellAllDao();
		List<Sell> inList = allDao.loadSell();
		loadAllList(inList);
	}

	/**
	 * 加载指定的入库信息到table
	 * 
	 * @param inList
	 */
	private void loadAllList(List<Sell> inList) {
		DefaultTableModel dtm = (DefaultTableModel) allTable.getModel();
		dtm.setRowCount(0);
		for (Sell sell : inList) {
			dtm.addRow(new Object[] { sell.getSellCode(), sell.getSellDate(),
					sell.getEmp().getEmpName(),
					sell.getSellStatus() == 1 ? "已审核" : "未审核" });
		}
	}

	/**
	 * 加载入库明细到table
	 * 
	 * @param itemList
	 */
	private void loadAllItemList(List<SellItem> itemList) {
		DefaultTableModel dtm = (DefaultTableModel) itemTable.getModel();
		dtm.setRowCount(0);
		for (SellItem sellItem : itemList) {
			dtm.addRow(new Object[] { sellItem.getGoods().getGoodsCode(),
					sellItem.getGoods().getGoodsName(),
					sellItem.getSeItemQty(),
					sellItem.getGoods().getGoodsPrice(),
					sellItem.getSeItemPrice() });
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnQuery) {
			String empName = listEmployee.getSelectedItem().toString();
			String startDate = txtStartDate.getText();
			String endDate = txtEndDate.getText();

			if (!startDate.equals("") && !endDate.equals("")) {
				Date sDate = DateUtil.formatStringToDate(startDate);
				Date eDate = DateUtil.formatStringToDate(endDate);
				if (sDate.getTime() > eDate.getTime()) {
					JOptionPane.showMessageDialog(this, "开始时间不能大于结束时间，请从新输入!",
							"温馨提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}

			// 调用DAO层查找
			SellAllDao allDao = new SellAllDao();
			List<Sell> inList = allDao.findSellByCondition(empName, startDate,
					endDate);
			if (inList.isEmpty()) {
				JOptionPane.showMessageDialog(this, "没有相关信息,请重新选择查找条件", "温馨提示",
						JOptionPane.WARNING_MESSAGE);
				return;
			} else {
				loadAllList(inList);
			}
		} else if (e.getSource() == btnReset) {
			loadAll();
			txtEndDate.setText("");
			txtStartDate.setText("");
			listEmployee.setSelectedItem("");
		}
	}
}
