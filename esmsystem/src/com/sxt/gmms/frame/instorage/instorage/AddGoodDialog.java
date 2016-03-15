package com.sxt.gmms.frame.instorage.instorage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.sxt.gmms.dao.base.GoodsInfoDao;
import com.sxt.gmms.entity.Goods;
import com.sxt.gmms.util.TableSetUtil;

/**
 * 商品信息类
 * 
 * @author ming
 * 
 */
public class AddGoodDialog extends JDialog implements ActionListener {

	private JTable table;
	private JTextField txtPinying;
	private JButton btnExit;
	InStorageFrame isf;
	private JButton btnAdd;

	/**
	 * Create the frame.
	 */
	public AddGoodDialog(InStorageFrame isf) {
		setTitle("添加商品");
		this.isf = isf;
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
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int rowCount = table.getSelectedRow();
					String goodCode = (String) table.getValueAt(rowCount, 0);
					new ChooseQtyDialog(AddGoodDialog.this, goodCode);
				}
			}
		});

		String[] text = { "编号", "名称", "拼音码", "类型", "规格", "供应商", "生产商", "备注" };
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

		btnAdd = new JButton("添加商品");
		btnAdd.addActionListener(this);
		btnAdd.setBounds(396, 431, 93, 23);
		getContentPane().add(btnAdd);

		// 加载
		loadGoodsInfo();

		this.setLocationRelativeTo(isf);
		this.setResizable(false);
		this.setModal(true);
		this.setVisible(true);
	}

	/**
	 * 加载数据到list
	 */
	public void loadGoodsInfo() {
		GoodsInfoDao infoDao = new GoodsInfoDao();
		List<Goods> goodsList = infoDao.loadGoodsList();
		loadGoods(goodsList);
	}

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
		if (e.getSource() == btnExit) {
			this.dispose();
		} else if (e.getSource() == btnAdd) {
			new AddGoodsInfoDialog(this);
		}
	}
}
