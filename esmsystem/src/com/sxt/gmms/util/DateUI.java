package com.sxt.gmms.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.JDateComponentFactory;
import net.sourceforge.jdatepicker.JDatePanel;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 * 单例模式
 * 
 * @author Administrator
 * 
 */
public class DateUI extends JDialog {
	private static DateUI dateUI = null;

	/**
	 * @param jframe
	 *            外部的JFrame对象
	 * @param model
	 *            是否模式窗口，即该窗口独占所有操�?
	 * @param jtextfield
	 *            外部JFrame里面的一个JTextField对象即要填入的日期文本域
	 * @param screen_x
	 *            外部的JTextField点击触发�?��MouseListener事件，传入e.getXOnScreen()
	 *            �?e.getYOnScreen(); 即决定随�?��标位置弹出该日期�?
	 * @param screen_y
	 *            即MouseEvent的e.getXOnScreen() �?e.getYOnScreen()
	 */
	private DateUI(boolean model, final JTextField jtextfield, int screen_x,
			int screen_y) {
		this.setModal(model);
		final JDatePanel jp = JDateComponentFactory
				.createJDatePanel(new UtilDateModel(new Date()));
		jp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					jtextfield.setText(new SimpleDateFormat("yyyy-MM-dd")
							.format(jp.getModel().getValue()));
					// 如果选中日期后，想消除JDialog，那么jp.addActionListener（new�?��ActionListener的实现类，将this对象传�?进去）然后调用dispose()方法
				} catch (Exception ex) {
					// 该日期控件点击Clear的时候会出异常，因为没有选中日期，如果要消除该异常，那么直接导入源代码，在源码里面改动�?
					// 这里直接�?��操作，点击Clear出现异常，直接将jtextfield赋�?�?"
					jtextfield.setText("");
				}
			}
		});

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				dateUI = null;
			}
		});
		JPanel jpanel = (JPanel) jp;
		this.add(jpanel);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setBounds(screen_x, screen_y, 300, 240);
		this.setVisible(true);
	}

	/**
	 * 
	 * @param jFame
	 * @param model
	 * @param jtextfield
	 * @param screen_x
	 * @param screen_y
	 * @return
	 */
	public static DateUI getInstence(boolean model,
			final JTextField jtextfield, int screen_x, int screen_y) {
		if (dateUI == null) {
			dateUI = new DateUI(model, jtextfield, screen_x, screen_y);
		}
		return dateUI;
	}

	/**
	 * 点击窗口事件dateUI置为空
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		new DateUI(true, null, 300, 240);
	}
}