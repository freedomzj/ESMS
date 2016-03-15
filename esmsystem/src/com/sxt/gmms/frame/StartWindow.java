
package com.sxt.gmms.frame;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.painter.StandardGradientPainter;
import org.jvnet.substance.skin.CremeCoffeeSkin;
import org.jvnet.substance.theme.SubstanceTerracottaTheme;
import org.jvnet.substance.title.FlatTitlePainter;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;

public class StartWindow extends JWindow implements Runnable {

	public final static int LOAD_WIDTH = 399;
	public final static int LOAD_HEIGHT = 252;
	public final static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	public final static int height = Toolkit.getDefaultToolkit()
			.getScreenSize().height;
	public JProgressBar jpb;
	public JLabel lbl;

	public static void main(String[] args) {
		try {
			// 样式设置
			UIManager.setLookAndFeel(new SubstanceLookAndFeel());
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			SubstanceLookAndFeel
					.setCurrentTheme(new SubstanceTerracottaTheme());
			SubstanceLookAndFeel.setSkin(new CremeCoffeeSkin());
			SubstanceLookAndFeel
					.setCurrentButtonShaper(new ClassicButtonShaper());
			SubstanceLookAndFeel
					.setCurrentWatermark(new SubstanceBubblesWatermark());
			SubstanceLookAndFeel
					.setCurrentBorderPainter(new StandardBorderPainter());
			SubstanceLookAndFeel
					.setCurrentGradientPainter(new StandardGradientPainter());
			SubstanceLookAndFeel.setCurrentTitlePainter(new FlatTitlePainter());
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		// 新建启动线程
		Thread t = new Thread(new StartWindow());
		// 启动
		t.start();
		try {
			// 等待该线程结束
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 结束之后进去登录窗口
		new LoginFrame();
	}

	public StartWindow() {
		lbl = new JLabel(new ImageIcon("image/main/start.jpg"));
		lbl.setBounds(0, 0, LOAD_WIDTH, LOAD_HEIGHT - 15);
		jpb = new JProgressBar();
		jpb.setStringPainted(true);
		jpb.setBorderPainted(false);
		jpb.setForeground(Color.GREEN);
		jpb.setBackground(Color.WHITE);
		jpb.setBounds(0, LOAD_HEIGHT - 15, LOAD_WIDTH, 15);
		this.add(lbl);
		this.add(jpb);
		this.setLayout(null);
		this.setBounds((width - LOAD_WIDTH) / 2, (height - LOAD_HEIGHT) / 2,
				LOAD_WIDTH, LOAD_HEIGHT);
		this.setVisible(true);
	}

	@Override
	public void run() {
		Random ran = new Random();
		for (int i = 0; i < 100; i+= ran.nextInt(5)) {
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
			jpb.setValue(i);
		}
		this.dispose();
	}
}
