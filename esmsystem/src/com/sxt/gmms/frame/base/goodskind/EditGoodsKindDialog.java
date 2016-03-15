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
public class EditGoodsKindDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCode;
	private JTextField txtName;
	JButton btnEdit;
	JButton btnCancel;
	GoodsKindFrame goodsKindFrame;

	/**
	 * Create the dialog.
	 */
	public EditGoodsKindDialog(GoodsKindFrame goodsKindFrame, String typeCode) {
		this.goodsKindFrame = goodsKindFrame;
		setTitle("修改商品类型");
		setBounds(100, 100, 328, 211);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			btnEdit = new JButton("修改");
			btnEdit.addActionListener(this);
			btnEdit.setBounds(47, 124, 93, 23);
			contentPanel.add(btnEdit);
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
		txtCode.setEditable(false);

		txtName = new JTextField();
		txtName.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(224,
				255, 255)));
		txtName.setBounds(121, 66, 150, 21);
		contentPanel.add(txtName);
		txtName.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("类型名称");
		lblNewLabel_1.setBounds(47, 72, 54, 15);
		contentPanel.add(lblNewLabel_1);

		// 加载要修改的数据
		GoodsKindDao kindDao = new GoodsKindDao();
		GoodsType type = kindDao.findGoodsKind(typeCode);
		txtCode.setText(type.getTypeCode());
		txtName.setText(type.getTypeName());

		this.setLocationRelativeTo(this.goodsKindFrame);
		this.setResizable(false);
		this.setModal(true);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEdit) {
			// 收集信息
			String typeCode = txtCode.getText();
			String typeName = txtName.getText();
			Pattern p = Pattern
					.compile("[\\u4E00-\\u9FA5\\uF900-\\uFA2D]{1,5}");
			Matcher m = p.matcher(txtName.getText());
			if (!m.matches()) {
				JOptionPane.showMessageDialog(this, "名称输入有误,请重新输入!");
				return;
			}
			// 组装
			GoodsType type = new GoodsType(0, typeCode, typeName, 0);
			int result = JOptionPane.showConfirmDialog(this, "确认修改 " + typeName
					+ " 的信息吗？", "温馨提示", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				// 调用dao层修改
				GoodsKindDao kindDao = new GoodsKindDao();
				kindDao.updateGoodsKind(type);
				// 刷新
				goodsKindFrame.loadGoodsKind();
				this.dispose();
			}
		} else if (e.getSource() == btnCancel) {
			this.dispose();
		}
	}
}
