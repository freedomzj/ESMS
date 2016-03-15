package com.sxt.gmms.util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * 设置表格样式
 * 
 * @author ming
 * 
 */
public class TableSetUtil {

	/**
	 * 设置表格的样式
	 * 
	 * @param table
	 *            要设置的表格
	 * @param textStr
	 *            要设置的文本的表头字段
	 * @param numberStr
	 *            要设置的数字的表头字段
	 */
	public static void setTextCellRenderer(JTable table, String[] textStr,
			String[] numberStr) {
		// 设置表头字体
		table.getTableHeader().setFont(new Font("仿宋体", Font.PLAIN, 16));
		table.getTableHeader().setBackground(Color.PINK);
		// 设置行高
		table.setRowHeight(22);
		// 设置每行的字体
		table.setFont(new Font("仿宋体", Font.PLAIN, 14));

		// 新建一个样式
		DefaultTableCellRenderer renderCenter = new DefaultTableCellRenderer();
		renderCenter.setHorizontalAlignment(SwingConstants.CENTER);
		if (textStr != null) {
			for (int i = 0; i < textStr.length; i++) {
				// 循环把样式加进去
				table.getColumn(textStr[i]).setCellRenderer(renderCenter);
			}
		}

		// 新建一个样式
		DefaultTableCellRenderer renderRight = new DefaultTableCellRenderer();
		renderRight.setHorizontalAlignment(SwingConstants.RIGHT);
		if (numberStr != null) {
			// 循环把样式加进去
			for (int i = 0; i < numberStr.length; i++) {
				table.getColumn(numberStr[i]).setCellRenderer(renderRight);
			}
		}
	}
}
