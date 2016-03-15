package com.sxt.gmms.frame.base.goodsinfo;

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

import com.sxt.gmms.dao.base.GoodsInfoDao;
import com.sxt.gmms.entity.Goods;
import com.sxt.gmms.frame.MainFrame;
import com.sxt.gmms.util.TableSetUtil;

/**
 * 商品信息类
 * 
 * @author ming
 * 
 */
public class GoodsInfoFrame extends JInternalFrame implements ActionListener {

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
	public GoodsInfoFrame(MainFrame mf) {
		super("商品资料", true, true, false, true);
		this.mf = mf;
		setBounds(100, 100, 737, 495);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 721, 72);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("商品资料");
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
				"备注" };

		// 数据模型
		DefaultTableModel dtm = new DefaultTableModel(null, header);
		table = new JTable(dtm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		String[] text = { "编号", "名称", "拼音码", "类型", "规格", "供应商", "生产商", "备注" };
		String[] number = { "价格" };
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
						loadGoodsInfo();
					} else {
						GoodsInfoDao goodsDao = new GoodsInfoDao();
						loadGoods(goodsDao.findGoodsInfoByPinyin(pym));
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

		int width = (mf.getWidth() - this.getWidth() - 200) / 2;
		int height = (mf.getHeight() - this.getHeight() - 140) / 2;
		this.setLocation(width, height);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * 加载数据到table
	 */
	public void loadGoodsInfo() {
		GoodsInfoDao infoDao = new GoodsInfoDao();
		List<Goods> goodsList = infoDao.loadGoodsList();
		loadGoods(goodsList);
	}

	// "编号", "名称", "拼音码", "类型","规格","供应商","价格","生产商","备注"

	/**
	 * 加载指定list到table
	 * 
	 * @param goodsList
	 */
	private void loadGoods(List<Goods> goodsList) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		for (Goods goods : goodsList) {
			dtm.addRow(new Object[] { goods.getGoodsCode(),
					goods.getGoodsName(), goods.getGoodsPym(),
					goods.getType().getTypeName(),
					goods.getSize().getSizeName(),
					goods.getSupplie().getSupName(), goods.getGoodsPrice(),
					goods.getGoodsProduct(), goods.getGoodsComment() });
		}
	}

	/**
	 * 事件处理
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			new AddGoodsInfoDialog(this);
		} else if (e.getSource() == btnEdit) {
			// 获得选中行数
			int rowCount = table.getSelectedRow();
			if (rowCount == -1) {
				JOptionPane.showMessageDialog(this, "请选择要修改的商品！", "温馨提示",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			String goodsCode = (String) table.getValueAt(rowCount, 0);
			new EditGoodsInfoDialog(this, goodsCode);
		} else if (e.getSource() == btnDel) {
			// 获得选中行数
			int rowCount = table.getSelectedRow();
			if (rowCount == -1) {
				JOptionPane.showMessageDialog(this, "请选择要删除的商品！", "温馨提示",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			String goodsCode = (String) table.getValueAt(rowCount, 0);
			int result = JOptionPane.showConfirmDialog(this,
					"确认删除 " + table.getValueAt(rowCount, 1) + " 的信息吗？", "温馨提示",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				// 调用DAO层删除数据
				GoodsInfoDao infoDao = new GoodsInfoDao();
				infoDao.delGoodsInfo(goodsCode);
				// 刷新
				loadGoodsInfo();
			}
		} else if (e.getSource() == btnRefresh) {
			loadGoodsInfo();
		} else if (e.getSource() == btnExit) {
			this.dispose();
		}
	}
}
