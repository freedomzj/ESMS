package com.sxt.gmms.frame.base.goodssize;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sxt.gmms.dao.base.GoodsSizeDao;
import com.sxt.gmms.entity.Size;
import com.sxt.gmms.frame.MainFrame;
import com.sxt.gmms.util.TableSetUtil;

/**
 * 商品规格管理类
 * 
 * @author ming
 * 
 */
public class GoodsSizeFrame extends JInternalFrame implements ActionListener {
	JButton btnAdd;
	JButton btnDel;
	JButton btnEdit;
	private JButton btnCancel;
	MainFrame mf;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public GoodsSizeFrame(MainFrame mf) {
		super("商品规格管理", true, true, false, true);
		this.mf = mf;
		setBounds(80, 80, 597, 463);
		getContentPane().setLayout(null);

		// 表头
		String[] header = { "规格编号", "规格名称" };
		// 数据模型
		DefaultTableModel dtm = new DefaultTableModel(null, header);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 561, 381);
		getContentPane().add(scrollPane);

		table = new JTable(dtm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		String[] text = { "规格编号", "规格名称" };
		TableSetUtil.setTextCellRenderer(table, text, null);
		scrollPane.setViewportView(table);

		btnAdd = new JButton("添加");
		btnAdd.addActionListener(this);
		btnAdd.setBounds(49, 401, 83, 23);
		getContentPane().add(btnAdd);

		btnDel = new JButton("删除");
		btnDel.addActionListener(this);
		btnDel.setBounds(181, 401, 83, 23);
		getContentPane().add(btnDel);

		btnEdit = new JButton("修改");
		btnEdit.addActionListener(this);
		btnEdit.setBounds(313, 401, 83, 23);
		getContentPane().add(btnEdit);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(445, 401, 83, 23);

		getContentPane().add(btnCancel);

		// 加载
		loadGoodSize();

		// 居中
		int width = (mf.getWidth() - this.getWidth() - 360) / 2;
		int height = (mf.getHeight() - this.getHeight() - 200) / 2;
		this.setLocation(width, height);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * 加载商品规格
	 */
	public void loadGoodSize() {
		// 调用dao层拿到list
		GoodsSizeDao sizeDao = new GoodsSizeDao();
		List<Size> sizeList = sizeDao.loadGoodsSizeList();
		// 获得数据模型
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		// 加进去
		for (Size size : sizeList) {
			dtm.addRow(new Object[] { size.getSizeCode(), size.getSizeName() });
		}
	}

	/**
	 * 事件
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			new AddGoodsSizeDialog(this);
		} else if (e.getSource() == btnDel) {
			// 获得选中的行
			int rowCount = table.getSelectedRow();
			if (rowCount == -1) {
				JOptionPane.showMessageDialog(this, "请选择要删除的商品规格!", "温馨提示",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			String sizeCode = (String) table.getValueAt(rowCount, 0);
			int result = JOptionPane.showConfirmDialog(this,
					"确认删除 " + table.getValueAt(rowCount, 1) + " 的信息吗？", "温馨提示",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.YES_OPTION) {
				// 调用dao删除
				GoodsSizeDao sizeDao = new GoodsSizeDao();
				sizeDao.delGoodsSize(sizeCode);
				// 重新加载
				loadGoodSize();
			}
		} else if (e.getSource() == btnEdit) {
			int rowCount = table.getSelectedRow();
			if (rowCount == -1) {
				JOptionPane.showMessageDialog(this, "请选择要修改的商品规格!", "温馨提示",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			String sizeCode = (String) table.getValueAt(rowCount, 0);
			new EditGoodsSizeDialog(this, sizeCode);
		} else if (e.getSource() == btnCancel) {
			this.dispose();
		}
	}
}
