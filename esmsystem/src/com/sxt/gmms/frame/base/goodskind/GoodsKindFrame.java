package com.sxt.gmms.frame.base.goodskind;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sxt.gmms.dao.base.GoodsKindDao;
import com.sxt.gmms.entity.GoodsType;
import com.sxt.gmms.frame.MainFrame;
import com.sxt.gmms.util.TableSetUtil;

/**
 * 商品类型管理类
 * 
 * @author ming
 * 
 */
public class GoodsKindFrame extends JInternalFrame implements ActionListener {
	JButton btnAdd;
	JButton btnDel;
	JButton btnEdit;
	private JButton btnCancel;
	MainFrame mf;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public GoodsKindFrame(MainFrame mf) {
		super("商品类型管理", true, true, false, true);
		this.mf = mf;
		setBounds(80, 80, 609, 484);
		getContentPane().setLayout(null);

		// 表头
		String[] header = { "类型编号", "类型名称" };
		// 数据模型
		DefaultTableModel dtm = new DefaultTableModel(null, header);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 573, 402);
		getContentPane().add(scrollPane);

		table = new JTable(dtm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		String[] text = { "类型编号", "类型名称" };
		TableSetUtil.setTextCellRenderer(table, text, null);
		scrollPane.setViewportView(table);

		btnAdd = new JButton("添加");
		btnAdd.addActionListener(this);
		btnAdd.setBounds(53, 422, 82, 23);
		getContentPane().add(btnAdd);

		btnDel = new JButton("删除");
		btnDel.addActionListener(this);
		btnDel.setBounds(188, 422, 82, 23);
		getContentPane().add(btnDel);

		btnEdit = new JButton("修改");
		btnEdit.addActionListener(this);
		btnEdit.setBounds(323, 422, 82, 23);
		getContentPane().add(btnEdit);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(458, 422, 82, 23);

		getContentPane().add(btnCancel);

		// 加载数据
		loadGoodsKind();

		// 居中
		int width = (mf.getWidth() - this.getWidth() - 360) / 2;
		int height = (mf.getHeight() - this.getHeight() - 200) / 2;
		this.setLocation(width, height);
		this.setResizable(false);
		this.setVisible(true);

	}

	/**
	 * 加载数据到table
	 */
	public void loadGoodsKind() {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		GoodsKindDao kindDao = new GoodsKindDao();
		List<GoodsType> typeLsit = kindDao.loatGYypeList();
		dtm.setRowCount(0);
		for (GoodsType type : typeLsit) {
			dtm.addRow(new Object[] { type.getTypeCode(), type.getTypeName() });
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			new AddGoodsKindDialog(this);
		} else if (e.getSource() == btnDel) {
			// 获得选中的行
			int rowCount = table.getSelectedRow();
			if (rowCount == -1) {
				JOptionPane.showMessageDialog(this, "请选择要删除的商品类型!", "温馨提示",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			String typeCode = (String) table.getValueAt(rowCount, 0);
			int result = JOptionPane.showConfirmDialog(this,
					"确认删除 " + table.getValueAt(rowCount, 1) + " 的信息吗？", "温馨提示",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				// 调用dao层删除
				GoodsKindDao kindDao = new GoodsKindDao();
				kindDao.delGoodsKind(typeCode);
				// 刷新
				loadGoodsKind();
			}
		} else if (e.getSource() == btnEdit) {
			// 获得选中的行
			int rowCount = table.getSelectedRow();
			if (rowCount == -1) {
				JOptionPane.showMessageDialog(this, "请选择要修改的商品类型!", "温馨提示",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			String typeCode = (String) table.getValueAt(rowCount, 0);
			new EditGoodsKindDialog(this, typeCode);
		} else if (e.getSource() == btnCancel) {
			this.dispose();
		}
	}
}
