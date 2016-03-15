package com.sxt.gmms.frame.instorage.instorage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class ChooseQtyDialog extends JDialog implements ActionListener {

	JButton btnSure;
	JButton btnCancel;
	private JTextField txtCode;
	private JTextField txtPin;
	private JTextField txtName;
	private JTextField txtPrice;
	private JTextField txtProduct;
	private JTextField txtSize;
	private JTextField txtKind;
	private JTextField txtSupplie;
	AddGoodDialog addGoodDialog;
	private JTextField txtQty;

	/**
	 * Create the dialog.
	 * 
	 * @param goodsInfoFrame
	 */
	public ChooseQtyDialog(AddGoodDialog addGoodDialog, String goodsCode) {
		this.addGoodDialog = addGoodDialog;
		setTitle("选择商品数量");
		setBounds(100, 100, 518, 338);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u5546\u54C1\u4FE1\u606F",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		panel.setBounds(0, 0, 495, 253);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("商品编号:");
		label.setBounds(34, 26, 54, 20);
		panel.add(label);

		txtCode = new JTextField();
		txtCode.setEditable(false);
		txtCode.setColumns(10);
		txtCode.setBounds(108, 26, 120, 21);
		panel.add(txtCode);

		JLabel label_1 = new JLabel("商品拼音码:");
		label_1.setBounds(34, 79, 76, 20);
		panel.add(label_1);

		txtPin = new JTextField();
		txtPin.setColumns(10);
		txtPin.setBounds(108, 79, 120, 21);
		txtPin.setEditable(false);
		panel.add(txtPin);

		JLabel label_2 = new JLabel("商品名称:");
		label_2.setBounds(268, 25, 54, 20);
		panel.add(label_2);

		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setColumns(10);
		txtName.setBounds(354, 25, 120, 21);
		panel.add(txtName);

		JLabel label_3 = new JLabel("商品类型:");
		label_3.setBounds(268, 81, 54, 20);
		panel.add(label_3);

		JLabel label_4 = new JLabel("商品规格:");
		label_4.setBounds(34, 125, 54, 20);
		panel.add(label_4);

		JLabel label_8 = new JLabel("商品价格:");
		label_8.setBounds(34, 177, 54, 20);
		panel.add(label_8);

		txtPrice = new JTextField();
		txtPrice.setEditable(false);
		txtPrice.setColumns(10);
		txtPrice.setBounds(108, 177, 120, 21);
		panel.add(txtPrice);

		txtProduct = new JTextField();
		txtProduct.setEditable(false);
		txtProduct.setColumns(10);
		txtProduct.setBounds(354, 176, 120, 21);
		panel.add(txtProduct);

		txtSize = new JTextField();
		txtSize.setEditable(false);
		txtSize.setColumns(10);
		txtSize.setBounds(108, 125, 120, 21);
		panel.add(txtSize);

		txtKind = new JTextField();
		txtKind.setEditable(false);
		txtKind.setColumns(10);
		txtKind.setBounds(354, 78, 120, 21);
		panel.add(txtKind);

		txtSupplie = new JTextField();
		txtSupplie.setEditable(false);
		txtSupplie.setColumns(10);
		txtSupplie.setBounds(354, 124, 120, 21);
		panel.add(txtSupplie);

		txtQty = new JTextField();
		txtQty.setBounds(108, 218, 120, 21);
		panel.add(txtQty);
		txtQty.setColumns(10);

		JLabel label_9 = new JLabel("生 产 商:");
		label_9.setBounds(268, 176, 54, 20);
		panel.add(label_9);

		JLabel label_10 = new JLabel("商品数量:");
		label_10.setBounds(34, 218, 54, 20);
		panel.add(label_10);

		btnSure = new JButton("确定");
		btnSure.addActionListener(this);
		btnSure.setBounds(105, 274, 93, 23);
		getContentPane().add(btnSure);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(303, 274, 93, 23);
		getContentPane().add(btnCancel);

		// 加载商品信息
		GoodsInfoDao infoDao = new GoodsInfoDao();
		Goods goods = infoDao.findGoodsInfo(goodsCode);
		txtCode.setText(goods.getGoodsCode());
		txtName.setText(goods.getGoodsName());
		txtPin.setText(goods.getGoodsPym());
		txtPrice.setText("" + goods.getGoodsPrice());
		txtProduct.setText(goods.getGoodsProduct());
		txtKind.setText(goods.getType().getTypeName());
		txtSize.setText(goods.getSize().getSizeName());
		txtSupplie.setText(goods.getSupplie().getSupName());

		JLabel label_5 = new JLabel("供 应 商:");
		label_5.setBounds(268, 127, 54, 20);
		panel.add(label_5);

		txtQty.requestFocus();
		this.setLocationRelativeTo(this.addGoodDialog);
		this.setModal(true);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSure) {
			String goodsCode = txtCode.getText();
			String goodsName = txtName.getText();
			String goodsPym = txtPin.getText();
			float goodsPrice = Float.parseFloat(txtPrice.getText());
			String goodsProduct = txtProduct.getText();
			GoodsType type = new GoodsType();
			type.setTypeName(txtKind.getText());
			Size size = new Size();
			size.setSizeName(txtSize.getText());
			Supplie supplie = new Supplie();
			supplie.setSupName(txtSupplie.getText());
			// 组装
			Goods goods = new Goods(0, goodsCode, goodsName, goodsPym, "",
					goodsPrice, goodsProduct, 1, type, size, supplie);
			// 商品数量
			int qty = Integer.parseInt(txtQty.getText());
			addGoodDialog.isf.loadGoodsAndQty(goods, qty);
			// 关闭窗口
			this.dispose();
		} else if (e.getSource() == btnCancel) {
			this.dispose();
		}
	}
}
