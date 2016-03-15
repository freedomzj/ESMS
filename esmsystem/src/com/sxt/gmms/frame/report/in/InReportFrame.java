package com.sxt.gmms.frame.report.in;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.sxt.gmms.frame.MainFrame;

public class InReportFrame extends JInternalFrame {

	private MainFrame mf;
	private JLabel lblImage;

	/**
	 * Create the frame.
	 */
	public InReportFrame(MainFrame mf,final String path) {
		super("进货报表", true, true, false, true);
		this.mf = mf;
		setBounds(100, 100, 636, 521);
		getContentPane().setLayout(null);

		lblImage = new JLabel("");
		lblImage.setBounds(10, 5, 600, 480);
		getContentPane().add(lblImage);

		lblImage.setIcon(new ImageIcon(path));
		
		this.addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosed(InternalFrameEvent e) {
				File file = new File(path);
				if(file.exists()) {
					file.delete();
					lblImage.setIcon(null);
				}
			}
		});
		
		int width = (this.mf.getWidth() - this.getWidth() - 200) / 2;
		int height = (this.mf.getHeight() - this.getHeight() - 140) / 2;
		this.setLocation(width, height);
		this.setResizable(false);
		this.setVisible(true);
	}
}
