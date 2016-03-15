package com.sxt.gmms.frame.base.goodskind;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.sxt.gmms.dao.base.GoodsKindDao;
import com.sxt.gmms.entity.GoodsType;

/**
 * 添加商品类型类
 * 
 * @author ming
 * 
 */
public class AddGoodsKindDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCode;
	private JTextField txtName;
	JButton btnAdd;
	JButton btnCancel;
	GoodsKindFrame goodsKindFrame;

	/**
	 * Create the dialog.
	 */
	public AddGoodsKindDialog(GoodsKindFrame goodsKindFrame) {
		this.goodsKindFrame = goodsKindFrame;
		setTitle("添加商品类型");
		setBounds(100, 100, 328, 211);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			btnAdd = new JButton("添加");
			btnAdd.addActionListener(this);
			btnAdd.setBounds(47, 124, 93, 23);
			contentPanel.add(btnAdd);
		}
		{
			btnCancel = new JButton("取消");
			btnCancel.addActionListener(this);
			btnCancel.setBounds(165, 124, 93, 23);
			contentPanel.add(btnCancel);
		}

		JLabel lblNewLabel = new JLabel("类型编号");
		lblNewLabel.setBounds(47, 26, 54, 15);
		contentPanel.add(lblNewLabel);

		txtCode = new JTextField();
		txtCode.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(224,
				255, 255)));
		txtCode.setBounds(121, 23, 150, 21);
		contentPanel.add(txtCode);
		txtCode.setColumns(10);

		txtName = new JTextField();
		txtName.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(224,
				255, 255)));
		txtName.setBounds(121, 66, 150, 21);
		contentPanel.add(txtName);
		txtName.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("类型名称");
		lblNewLabel_1.setBounds(47, 72, 54, 15);
		contentPanel.add(lblNewLabel_1);

		this.setLocationRelativeTo(this.goodsKindFrame);
		this.setResizable(false);
		this.setModal(true);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			// 收集信息
			String typeCode = txtCode.getText();
			Pattern p = Pattern.compile("\\d{1,12}");
			Matcher m = p.matcher(txtCode.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "类型编号输入有误,请重新输入!");
				return;
			}
			String typeName = txtName.getText();
			p = Pattern.compile("[\\u4E00-\\u9FA5\\uF900-\\uFA2D]{1,5}");
			m = p.matcher(txtName.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "类型名称输入有误,请重新输入!");
				return;
			}
			// 组装
			GoodsType type = new GoodsType(0, typeCode, typeName, 0);
			GoodsKindDao typeDao = new GoodsKindDao();
			typeDao.addGoodsKind(type);
			// 重新刷新
			goodsKindFrame.loadGoodsKind();
			this.dispose();
		} else if (e.getSource() == btnCancel) {
			this.dispose();
		}
	}
}
