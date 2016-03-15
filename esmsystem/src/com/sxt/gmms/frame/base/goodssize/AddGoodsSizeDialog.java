package com.sxt.gmms.frame.base.goodssize;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.sxt.gmms.dao.base.GoodsSizeDao;
import com.sxt.gmms.entity.Size;

/**
 * 添加商品规格类
 * 
 * @author ming
 * 
 */
public class AddGoodsSizeDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCode;
	private JTextField txtName;
	JButton btnAdd;
	JButton btnCancel;
	GoodsSizeFrame goodssizeFrame;

	/**
	 * Create the dialog.
	 */
	public AddGoodsSizeDialog(GoodsSizeFrame goodssizeFrame) {
		this.goodssizeFrame = goodssizeFrame;
		setTitle("添加商品规格");
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

		JLabel lblNewLabel = new JLabel("规格编号");
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

		JLabel lblNewLabel_1 = new JLabel("规格名称");
		lblNewLabel_1.setBounds(47, 72, 54, 15);
		contentPanel.add(lblNewLabel_1);

		this.setLocationRelativeTo(this.goodssizeFrame);
		this.setResizable(false);
		this.setModal(true);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			// 收集信息
			String sizeCode = txtCode.getText();
			Pattern p = Pattern.compile("\\d{1,12}");
			Matcher m = p.matcher(txtCode.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "编号输入有误,请重新输入!");
				return;
			}
			String sizeName = txtName.getText();
			p = Pattern.compile("[\\u4E00-\\u9FA5\\uF900-\\uFA2D]{1,5}|.{1,20}");
			m = p.matcher(txtName.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "名称输入有误,请重新输入!");
				return;
			}
			// 组装
			Size size = new Size(0, sizeCode, sizeName);
			// 加到数据库
			GoodsSizeDao sizeDao = new GoodsSizeDao();
			sizeDao.addGoodsSize(size);
			// 重新加载
			goodssizeFrame.loadGoodSize();
			this.dispose();
		} else if (e.getSource() == btnCancel) {
			this.dispose();
		}
	}
}
