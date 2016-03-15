package com.sxt.gmms.frame.base.goodsinfo;

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
public class EditGoodsInfoDialog extends JDialog implements ActionListener {

	JButton btnEdit;
	JButton btnCancel;
	private JTextField txtCode;
	private JTextField txtPin;
	private JTextField txtName;
	private JTextField txtPrice;
	private JTextField txtProduct;
	GoodsInfoFrame goodsInfoFrame;
	private JTextArea txtComment;
	private JComboBox listSize;
	private JComboBox listKind;
	private JComboBox listSup;

	/**
	 * Create the dialog.
	 * 
	 * @param goodsInfoFrame
	 */
	public EditGoodsInfoDialog(GoodsInfoFrame goodsInfoFrame, String goodsCode) {
		this.goodsInfoFrame = goodsInfoFrame;
		setTitle("修改商品");
		setBounds(100, 100, 614, 411);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u5546\u54C1\u4FE1\u606F",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		panel.setBounds(0, 0, 598, 335);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("商品编号:");
		label.setBounds(34, 26, 54, 20);
		panel.add(label);

		txtCode = new JTextField();
		txtCode.setEditable(false);
		txtCode.setColumns(10);
		txtCode.setBounds(108, 26, 153, 21);
		panel.add(txtCode);

		JLabel label_1 = new JLabel("商品拼音码:");
		label_1.setBounds(34, 79, 76, 20);
		panel.add(label_1);

		txtPin = new JTextField();
		txtPin.setColumns(10);
		txtPin.setBounds(108, 79, 153, 21);
		txtPin.setEditable(false);
		panel.add(txtPin);

		JLabel label_2 = new JLabel("商品名称:");
		label_2.setBounds(303, 26, 54, 20);
		panel.add(label_2);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(389, 26, 166, 21);
		panel.add(txtName);

		JLabel label_3 = new JLabel("商品类型:");
		label_3.setBounds(303, 82, 54, 20);
		panel.add(label_3);

		listKind = new JComboBox();
		listKind.setBounds(389, 79, 166, 21);
		panel.add(listKind);

		listSize = new JComboBox();
		listSize.setBounds(108, 125, 153, 21);
		panel.add(listSize);
		
		listSup = new JComboBox();
		listSup.setBounds(389, 125, 166, 21);
		panel.add(listSup);

		JLabel label_4 = new JLabel("商品规格:");
		label_4.setBounds(34, 125, 54, 20);
		panel.add(label_4);

		JLabel label_8 = new JLabel("商品价格:");
		label_8.setBounds(34, 177, 54, 20);
		panel.add(label_8);

		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(108, 177, 153, 21);
		panel.add(txtPrice);

		txtProduct = new JTextField();
		txtProduct.setColumns(10);
		txtProduct.setBounds(389, 177, 166, 21);
		panel.add(txtProduct);

		JLabel label_9 = new JLabel("生 产 商:");
		label_9.setBounds(303, 177, 54, 20);
		panel.add(label_9);

		JLabel label_10 = new JLabel("商品备注:");
		label_10.setBounds(34, 228, 54, 20);
		panel.add(label_10);

		txtComment = new JTextArea();
		txtComment.setBounds(115, 231, 440, 61);
		panel.add(txtComment);

		btnEdit = new JButton("修改");
		btnEdit.addActionListener(this);
		btnEdit.setBounds(137, 345, 93, 23);
		getContentPane().add(btnEdit);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(367, 345, 93, 23);
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

		// 加载要修改的商品信息
		GoodsInfoDao infoDao = new GoodsInfoDao();
		Goods goods = infoDao.findGoodsInfo(goodsCode);
		txtCode.setText(goods.getGoodsCode());
		txtName.setText(goods.getGoodsName());
		txtPin.setText(goods.getGoodsPym());
		txtComment.setText(goods.getGoodsComment());
		txtPrice.setText("" + goods.getGoodsPrice());
		txtProduct.setText(goods.getGoodsProduct());
		listKind.setSelectedItem(goods.getType().getTypeName());
		listSize.setSelectedItem(goods.getSize().getSizeName());
		listSup.setSelectedItem(goods.getSupplie().getSupName());

		JLabel label_5 = new JLabel("供 应 商:");
		label_5.setBounds(303, 128, 54, 20);
		panel.add(label_5);

		this.setLocationRelativeTo(this.goodsInfoFrame);
		this.setModal(true);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEdit) {
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
			int result = JOptionPane.showConfirmDialog(this, "确认修改 "
					+ goodsName + " 的信息吗？", "温馨提示", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				// 调用DAO层修改
				GoodsInfoDao infoDao = new GoodsInfoDao();
				infoDao.updateGoodsInfo(goods);
				// 刷新
				goodsInfoFrame.loadGoodsInfo();
				this.dispose();
			}
		} else if (e.getSource() == btnCancel) {
			this.dispose();
		}
	}
}
