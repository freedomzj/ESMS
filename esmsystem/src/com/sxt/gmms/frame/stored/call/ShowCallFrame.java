package com.sxt.gmms.frame.stored.call;

import java.awt.Color;
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
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import com.sxt.gmms.dao.stored.StoredDao;
import com.sxt.gmms.entity.Stock;
import com.sxt.gmms.frame.MainFrame;
import com.sxt.gmms.util.TableSetUtil;

public class ShowCallFrame extends JInternalFrame implements ActionListener {

	private JTable table;
	private JTextField txtPinying;
	private JButton btnExit;
	private MainFrame mf;
	private List<Stock> stockList;

	/**
	 * Create the frame.
	 */
	public ShowCallFrame(MainFrame mf, final List<Stock> stockList) {
		super("库存预警", true, true, false, true);
		this.mf = mf;
		this.stockList = stockList;
		this.setTitle("库存预警");
		this.setSize(737, 495);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 721, 72);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("库存预警资料");
		lblNewLabel.setForeground(Color.RED);
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
		String[] header = { "编号", "名称", "拼音码", "类型", "规格", "供应商", "价格", "生产商",
				"备注", "当前库存" };

		// 数据模型
		DefaultTableModel dtm = new DefaultTableModel(null, header);
		table = new JTable(dtm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		String[] text = { "编号", "名称", "拼音码", "类型", "规格", "供应商", "生产商", "备注",
				"当前库存" };
		String[] number = { "价格" };
		TableSetUtil.setTextCellRenderer(table, text, number);
		scrollPane.setViewportView(table);

		btnExit = new JButton("退出");
		btnExit.addActionListener(this);
		btnExit.setBounds(539, 431, 93, 23);
		getContentPane().add(btnExit);

		JLabel lblNewLabel_1 = new JLabel("支持拼音首字母查询：");
		lblNewLabel_1.setBounds(89, 433, 122, 19);
		getContentPane().add(lblNewLabel_1);

		txtPinying = new JTextField();
		txtPinying.setBounds(300, 430, 150, 21);
		txtPinying.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String pym = txtPinying.getText().toUpperCase();
					if (pym.equals("")) {
						loadGoodsInfo();
					} else {
						StoredDao storedDao = new StoredDao();
						loadGoods(storedDao.loadStockInfoByPym(pym));
						txtPinying.setText("");
						txtPinying.requestFocus();
					}
				}
			}
		});

		getContentPane().add(txtPinying);
		txtPinying.setColumns(10);

		// 加载
		loadGoodsInfo();

		int width = (this.mf.getWidth() - this.getWidth() - 200) / 2;
		int height = (this.mf.getHeight() - this.getHeight() - 140) / 2;
		this.setLocation(width, height);
		this.setResizable(false);
		this.setVisible(true);

		/**
		 * 窗口被激活事件
		 */
		this.addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				if (stockList.isEmpty()) {
					int result = JOptionPane.showConfirmDialog(
							ShowCallFrame.this, "没有少于预警值的商品,是否关闭本窗口?", "温馨提示",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
						ShowCallFrame.this.dispose();
					}
				}
			}
		});
	}

	/**
	 * 加载数据到list
	 */
	public void loadGoodsInfo() {
		loadGoods(stockList);
	}

	/**
	 * 加载指定list到table
	 * 
	 * @param goodsList
	 */
	private void loadGoods(List<Stock> stockList) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		for (Stock stock : stockList) {
			dtm.addRow(new Object[] { stock.getGoods().getGoodsCode(),
					stock.getGoods().getGoodsName(),
					stock.getGoods().getGoodsPym(),
					stock.getGoods().getType().getTypeName(),
					stock.getGoods().getSize().getSizeName(),
					stock.getGoods().getSupplie().getSupName(),
					stock.getGoods().getGoodsPrice(),
					stock.getGoods().getGoodsProduct(),
					stock.getGoods().getGoodsComment(), stock.getStockQty() });
		}
	}

	/**
	 * 事件处理
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnExit) {
			this.dispose();
		}
	}
}
