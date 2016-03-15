package com.sxt.gmms.frame.instorage.instorage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.sxt.gmms.dao.base.GoodsInfoDao;
import com.sxt.gmms.dao.base.GoodsKindDao;
import com.sxt.gmms.dao.base.GoodsSizeDao;
import com.sxt.gmms.dao.base.SupplierInfoDao;
import com.sxt.gmms.entity.Goods;
import com.sxt.gmms.entity.Size;
import com.sxt.gmms.entity.Supplie;
import com.sxt.gmms.entity.GoodsType;
import com.sxt.gmms.util.PinyinUtil;

/**
 * 添加商品类
 * 
 * @author ming
 * 
 */
public class AddGoodsInfoDialog extends JDialog implements ActionListener {
	AddGoodDialog addGoodDialog;
	private JTextField txtCode;
	private JTextField txtPin;
	private JTextField txtName;
	private JComboBox listKind;
	private JComboBox listSize;
	private JTextField txtPrice;
	private JTextField txtProduct;
	private JTextArea txtComment;
	private JComboBox listSup;
	private JButton btnAdd;
	private JButton btnCancel;

	/**
	 * Create the dialog.
	 * 
	 * @param goodsInfoFrame
	 */
	public AddGoodsInfoDialog(AddGoodDialog addGoodDialog) {
		this.addGoodDialog = addGoodDialog;
		setTitle("添加商品");
		setBounds(100, 100, 614, 404);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u5546\u54C1\u4FE1\u606F",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		panel.setBounds(0, 0, 598, 323);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("商品编号:");
		label.setBounds(36, 23, 54, 20);
		panel.add(label);

		txtCode = new JTextField();
		txtCode.setText((String) null);
		txtCode.setColumns(10);
		txtCode.setBounds(110, 23, 153, 21);
		panel.add(txtCode);

		JLabel label_1 = new JLabel("商品拼音码:");
		label_1.setBounds(36, 76, 76, 20);
		panel.add(label_1);

		txtPin = new JTextField();
		txtPin.setText("回车自动生成");
		txtPin.setEditable(false);
		txtPin.setColumns(10);
		txtPin.setBounds(110, 76, 153, 21);
		txtPin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtPin.setText(PinyinUtil.stringArrayToString(PinyinUtil
							.getHeadByString(txtName.getText())));
				}
			}
		});
		panel.add(txtPin);

		JLabel label_2 = new JLabel("商品名称:");
		label_2.setBounds(305, 23, 54, 20);
		panel.add(label_2);

		txtName = new JTextField();
		txtName.setText((String) null);
		txtName.setColumns(10);
		txtName.setBounds(391, 23, 166, 21);
		panel.add(txtName);

		JLabel label_3 = new JLabel("商品类型:");
		label_3.setBounds(305, 79, 54, 20);
		panel.add(label_3);

		listKind = new JComboBox();
		listKind.setBounds(391, 76, 166, 21);
		panel.add(listKind);

		listSize = new JComboBox();
		listSize.setBounds(110, 122, 153, 21);
		panel.add(listSize);

		JLabel label_4 = new JLabel("商品规格:");
		label_4.setBounds(36, 122, 54, 20);
		panel.add(label_4);

		JLabel label_5 = new JLabel("商品价格:");
		label_5.setBounds(36, 174, 54, 20);
		panel.add(label_5);

		txtPrice = new JTextField();
		txtPrice.setText("0.0");
		txtPrice.setColumns(10);
		txtPrice.setBounds(110, 174, 153, 21);
		panel.add(txtPrice);

		txtProduct = new JTextField();
		txtProduct.setText((String) null);
		txtProduct.setColumns(10);
		txtProduct.setBounds(391, 174, 166, 21);
		panel.add(txtProduct);

		JLabel label_6 = new JLabel("生 产 商:");
		label_6.setBounds(305, 174, 54, 20);
		panel.add(label_6);

		JLabel label_7 = new JLabel("商品备注:");
		label_7.setBounds(36, 225, 54, 20);
		panel.add(label_7);

		txtComment = new JTextArea();
		txtComment.setText((String) null);
		txtComment.setBounds(117, 228, 440, 61);
		panel.add(txtComment);

		JLabel label_8 = new JLabel("供 应 商:");
		label_8.setBounds(305, 125, 54, 20);
		panel.add(label_8);

		listSup = new JComboBox();
		listSup.setBounds(391, 122, 166, 21);
		panel.add(listSup);

		btnAdd = new JButton("添加");
		btnAdd.addActionListener(this);
		btnAdd.setBounds(134, 333, 93, 23);
		getContentPane().add(btnAdd);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(367, 333, 93, 23);
		getContentPane().add(btnCancel);

		// 加载商品类型
		GoodsKindDao kindDao = new GoodsKindDao();
		List<GoodsType> kindList = kindDao.loatGYypeList();
		for (GoodsType type : kindList) {
			listKind.addItem(type.getTypeName());
		}

		// 加载规格
		GoodsSizeDao sizeDao = new GoodsSizeDao();
		List<Size> sizeList = sizeDao.loadGoodsSizeList();
		for (Size size : sizeList) {
			listSize.addItem(size.getSizeName());
		}

		// 加载供应商
		SupplierInfoDao supDao = new SupplierInfoDao();
		List<Supplie> supList = supDao.loadSupplierInfoList();
		for (Supplie supplie : supList) {
			listSup.addItem(supplie.getSupName());
		}

		this.setLocationRelativeTo(this.addGoodDialog);
		this.setModal(true);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			// 收集信息
			String goodsCode = txtCode.getText();
			// 商品名
			String goodsName = txtName.getText();
			// 商品拼音码
			String goodsPym = PinyinUtil.stringArrayToString(PinyinUtil
					.getHeadByString(txtName.getText()));
			String goodsComment = txtComment.getText();
			// 商品价格
			Pattern p = Pattern.compile("\\d*\\.?\\d+");
			Matcher m = p.matcher(txtPrice.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "商品价格输入有误,请重新输入!");
				return;
			}
			float goodsPrice = Float.parseFloat(txtPrice.getText());
			// 商品生产商
			String goodsProduct = txtProduct.getText();
			p = Pattern.compile("[\\u4E00-\\u9FA5\\uF900-\\uFA2D]{1,20}");
			m = p.matcher(txtProduct.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "商品生产商输入有误,请重新输入!");
				return;
			}
			// 商品类型
			GoodsType type = new GoodsType();
			type.setTypeName(listKind.getSelectedItem().toString());
			// 商品规格
			Size size = new Size();
			size.setSizeName(listSize.getSelectedItem().toString());
			// 供应商
			Supplie supplie = new Supplie();
			supplie.setSupName(listSup.getSelectedItem().toString());
			// 组装
			Goods goods = new Goods(0, goodsCode, goodsName, goodsPym,
					goodsComment, goodsPrice, goodsProduct, 1, type, size,
					supplie);
			// 调用DAO层添加
			GoodsInfoDao infoDao = new GoodsInfoDao();
			infoDao.addGoofsInfo(goods);
			// 刷新
			addGoodDialog.loadGoodsInfo();
			this.dispose();
		} else if (e.getSource() == btnCancel) {
			this.dispose();
		}
	}
}
