package com.sxt.gmms.frame.stored.all;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.sxt.gmms.dao.stored.StoredDao;
import com.sxt.gmms.entity.CheckItem;
import com.sxt.gmms.frame.MainFrame;
import com.sxt.gmms.util.TableSetUtil;
import javax.swing.UIManager;

public class CheckItemAll extends JInternalFrame implements ActionListener {

	private JPanel contentPane;
	private MainFrame mf;
	private JTable itemTable;
	private JTextField txtCode;
	private JButton btnQuery;
	private JButton btnExit;

	/**
	 * Create the frame.
	 */
	public CheckItemAll(MainFrame mf) {
		super("历史盘点汇总", true, true, false, true);
		this.mf = mf;
		setBounds(100, 100, 700, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u76D8\u70B9\u660E\u7EC6", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_2.setBounds(0, 0, 684, 480);
		contentPane.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, BorderLayout.CENTER);

		// 表头
		String[] itemHeader = { "盘点编号", "产品名称", "产品单价", "库存数量", "实际数量", "差值",
				"金额" };
		// 数据模型
		DefaultTableModel itemDtm = new DefaultTableModel(null, itemHeader);
		itemTable = new JTable(itemDtm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		String[] textItem = { "盘点编号", "产品名称" };
		String[] numberItem = { "产品单价", "金额", "库存数量", "实际数量", "差值" };
		TableSetUtil.setTextCellRenderer(itemTable, textItem, numberItem);
		scrollPane_1.setViewportView(itemTable);

		txtCode = new JTextField();
		txtCode.setBounds(162, 490, 173, 21);
		txtCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					String chCode = txtCode.getText();
					if (chCode.equals("")) {
						loadAll();
					} else {
						StoredDao storedDao = new StoredDao();
						List<CheckItem> itemList = storedDao
								.findCheckItemByCode(chCode);
						// 刷新
						loadAllItemList(itemList);
						txtCode.setText("");
						txtCode.requestFocus();
					}
				}
			}
		});
		contentPane.add(txtCode);
		txtCode.setColumns(10);

		JLabel label = new JLabel("按编号查询");
		label.setBounds(54, 490, 74, 20);
		contentPane.add(label);

		btnQuery = new JButton("查询");
		btnQuery.addActionListener(this);
		btnQuery.setBounds(389, 489, 93, 23);
		contentPane.add(btnQuery);

		btnExit = new JButton("退出");
		btnExit.addActionListener(this);
		btnExit.setBounds(536, 489, 93, 23);
		contentPane.add(btnExit);

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
		StoredDao storedDao = new StoredDao();
		List<CheckItem> itemList = storedDao.loadCheckItem();
		loadAllItemList(itemList);
	}

	/**
	 * 加载入库明细到table
	 * 
	 * @param itemList
	 */
	private void loadAllItemList(List<CheckItem> itemList) {
		DefaultTableModel dtm = (DefaultTableModel) itemTable.getModel();
		dtm.setRowCount(0);
		for (CheckItem item : itemList) {
			int temp = item.getChRealQty() - item.getChStockQty();
			float price = temp * item.getGoods().getGoodsPrice();
			dtm.addRow(new Object[] { item.getChCode(),
					item.getGoods().getGoodsName(),
					item.getGoods().getGoodsPrice(), item.getChStockQty(),
					item.getChRealQty(), temp, price });
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnExit) {
			this.dispose();
		} else if (e.getSource() == btnQuery) {
			String chCode = txtCode.getText();
			StoredDao storedDao = new StoredDao();
			List<CheckItem> itemList = storedDao.findCheckItemByCode(chCode);
			// 刷新
			loadAllItemList(itemList);
		}
	}
}
