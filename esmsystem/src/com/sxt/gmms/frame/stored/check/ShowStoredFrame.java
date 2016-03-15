package com.sxt.gmms.frame.stored.check;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.sxt.gmms.dao.stored.StoredDao;
import com.sxt.gmms.entity.CheckItem;
import com.sxt.gmms.entity.Goods;
import com.sxt.gmms.entity.Stock;
import com.sxt.gmms.frame.MainFrame;
import com.sxt.gmms.util.AutoCodeUtil;
import com.sxt.gmms.util.TableSetUtil;

/**
 * 商品信息类
 * 
 * @author ming
 * 
 */
public class ShowStoredFrame extends JInternalFrame implements ActionListener {

	private JTable table;
	private JTextField txtPinying;
	private JButton btnExit;
	private MainFrame mf;
	private JButton btnSave;

	/**
	 * Create the frame.
	 */
	public ShowStoredFrame(MainFrame mf) {
		super("库存管理", true, true, false, true);
		this.mf = mf;
		setBounds(100, 100, 737, 495);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 721, 72);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("库存资料");
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
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int rowCount = table.getSelectedRow();
					int qty = Integer.parseInt(table.getValueAt(rowCount, 9)
							.toString());
					String goodCode = (String) table.getValueAt(rowCount, 0);
					new ChangeStoredQty(ShowStoredFrame.this, goodCode,
							rowCount, qty);
				}
			}
		});

		String[] text = { "编号", "名称", "拼音码", "类型", "规格", "供应商", "生产商", "备注",
				"当前库存" };
		String[] number = { "价格" };
		TableSetUtil.setTextCellRenderer(table, text, number);
		scrollPane.setViewportView(table);

		btnExit = new JButton("退出");
		btnExit.addActionListener(this);
		btnExit.setBounds(564, 431, 93, 23);
		getContentPane().add(btnExit);

		JLabel lblNewLabel_1 = new JLabel("支持拼音首字母查询：");
		lblNewLabel_1.setBounds(17, 433, 122, 19);
		getContentPane().add(lblNewLabel_1);

		txtPinying = new JTextField();
		txtPinying.setBounds(156, 430, 150, 21);
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

		btnSave = new JButton("保存");
		btnSave.addActionListener(this);
		btnSave.setBounds(388, 431, 93, 23);
		getContentPane().add(btnSave);

		// 加载
		loadGoodsInfo();

		int width = (this.mf.getWidth() - this.getWidth() - 200) / 2;
		int height = (this.mf.getHeight() - this.getHeight() - 140) / 2;
		this.setLocation(width, height);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * 加载数据到list
	 */
	public void loadGoodsInfo() {
		StoredDao storedDao = new StoredDao();
		List<Stock> stocksList = storedDao.loadStockInfo();
		loadGoods(stocksList);
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
		} else if (e.getSource() == btnSave) {
			// 用来更新库存数量的list
			List<Stock> stockList = new ArrayList<Stock>();
			// 用来记录盘点明细的list
			List<CheckItem> itemList = new ArrayList<CheckItem>();
			for (int i = 0; i < table.getRowCount(); i++) {
				String goodsCode = (String) table.getValueAt(i, 0);
				// 组装Goods
				Goods goods = new Goods();
				goods.setGoodsCode(goodsCode);
				
				int stockQty = (Integer) table.getValueAt(i, 9);
				Stock stock = new Stock();
				stock.setGoods(goods);
				// 组装称库存对象
				stock.setStockQty(stockQty);
				stockList.add(stock);
				
				// 组装盘点明细
				CheckItem item = new CheckItem();
				// 自动产生编号
				String chCode = AutoCodeUtil.createOrderCode("PD");
				// 设置真实的库存
				item.setChRealQty(stockQty);
				item.setChCode(chCode);
				item.setGoods(goods);
				itemList.add(item);
			}
			
			StoredDao storedDao = new StoredDao();
			// 调用DAO层添加盘点明细
			storedDao.addCheckItem(itemList);
			
			// 调用DAO层更新库存数量
			storedDao.checkGoodsStock(stockList);
			// 重新加载
			loadGoodsInfo();
			// 关闭
			this.dispose();
		}
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
}
