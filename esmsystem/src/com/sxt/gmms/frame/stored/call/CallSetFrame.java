package com.sxt.gmms.frame.stored.call;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.sxt.gmms.frame.MainFrame;

public class CallSetFrame extends JInternalFrame implements ActionListener {

	private JTextField txtCurrent;
	private JTextField txtNum;
	private MainFrame mf;
	private JButton btnCancel;
	private JButton btnEdit;

	/**
	 * Create the frame.
	 */
	public CallSetFrame(MainFrame mf) {
		super("库存报警设置", true, true, false, true);
		this.mf = mf;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"),
				"\u5E93\u5B58\u62A5\u8B66\u8BBE\u7F6E", TitledBorder.LEADING,
				TitledBorder.TOP, null, Color.RED));
		panel.setBackground(UIManager.getColor("CheckBox.interiorBackground"));
		panel.setBounds(0, 71, 434, 147);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblCurrent = new JLabel("当前报警数量:");
		lblCurrent.setBounds(59, 28, 114, 20);
		panel.add(lblCurrent);

		JLabel label = new JLabel("自定义报警数量:");
		label.setBounds(59, 84, 114, 20);
		panel.add(label);

		txtCurrent = new JTextField();
		txtCurrent.setEditable(false);
		txtCurrent.setBounds(183, 28, 137, 21);
		panel.add(txtCurrent);
		txtCurrent.setColumns(10);

		txtNum = new JTextField();
		txtNum.setBounds(183, 84, 137, 21);
		panel.add(txtNum);
		txtNum.setColumns(10);

		btnEdit = new JButton("修改");
		btnEdit.addActionListener(this);
		btnEdit.setBounds(82, 237, 93, 23);
		getContentPane().add(btnEdit);

		btnCancel = new JButton("取消");
		btnCancel.addActionListener(this);
		btnCancel.setBounds(257, 237, 93, 23);
		getContentPane().add(btnCancel);

		JTextArea textArea = new JTextArea();
		textArea.setBorder(new EmptyBorder(0, 0, 0, 0));
		textArea.setBackground(UIManager
				.getColor("CheckBox.interiorBackground"));
		textArea.setText("\t\r\n\t用户可以根据需要自定义库存报警的数量，重启程序生效");
		textArea.setEditable(false);
		textArea.setBounds(0, 0, 434, 60);
		getContentPane().add(textArea);

		String fileName = "call.properties";
		int callNum = parseProperties(fileName);
		txtCurrent.setText(String.valueOf(callNum));

		int width = (this.mf.getWidth() - this.getWidth() - 200) / 2;
		int height = (this.mf.getHeight() - this.getHeight() - 140) / 2;
		this.setLocation(width, height);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * 读取配置文件的信息
	 * 
	 * @param fileName
	 * @return
	 */
	public int parseProperties(String fileName) {
		Properties pro = new Properties();
		InputStream in = null;
		int num = -1;
		try {
			in = new FileInputStream("config/call.properties");
			pro.load(in);
			String str = pro.getProperty("callNum");
			num = Integer.parseInt(str);
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return num;
	}

	/**
	 * 设置配置文件的值
	 * 
	 * @param fileName
	 * @param proValue
	 */
	public void setProperties(String fileName, String proValue) {
		Properties pro = new Properties();
		InputStream in = null;
		OutputStream os = null;
		try {
			in = new FileInputStream(fileName);
			os = new FileOutputStream(fileName);
			pro.load(in);
			pro.setProperty("callNum", proValue);
			pro.store(os, null);
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				in.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEdit) {
			String numStr = txtNum.getText();
			String fileName = "config/call.properties";
			setProperties(fileName, numStr);
			this.dispose();
		} else if (e.getSource() == btnCancel) {
			this.dispose();
		}

	}
}
