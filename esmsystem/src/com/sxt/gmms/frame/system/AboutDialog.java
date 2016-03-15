package com.sxt.gmms.frame.system;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import com.sxt.gmms.frame.MainFrame;

public class AboutDialog extends JInternalFrame {

	private MainFrame mf;

	/**
	 * Create the dialog.
	 */
	public AboutDialog(MainFrame mf) {
		super("关于本程序",true,true,false,true);
		this.mf = mf;
		setBounds(100, 100, 620, 480);

		JLabel lblImage = new JLabel("");
		lblImage.setBounds(0, 0, 614, 452);
		lblImage.setIcon(new ImageIcon("image/system/about.jpg"));

		getContentPane().setLayout(null);
		getContentPane().add(lblImage);

		int width = (this.mf.getWidth() - this.getWidth() - 200) / 2;
		int height = (this.mf.getHeight() - this.getHeight() - 140) / 2;
		this.setLocation(width, height);
		this.setResizable(false);
		this.setVisible(true);
	}
}
