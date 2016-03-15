package com.sxt.gmms.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.button.StandardButtonShaper;
import org.jvnet.substance.painter.StandardGradientPainter;
import org.jvnet.substance.skin.CremeCoffeeSkin;
import org.jvnet.substance.theme.SubstanceTerracottaTheme;
import org.jvnet.substance.title.FlatTitlePainter;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;

import com.sxt.gmms.dao.stored.StoredDao;
import com.sxt.gmms.entity.Stock;
import com.sxt.gmms.entity.User;
import com.sxt.gmms.event.BaseEvent;
import com.sxt.gmms.event.InStorageEvent;
import com.sxt.gmms.event.ReportManagerEvent;
import com.sxt.gmms.event.SaleManagerEvent;
import com.sxt.gmms.event.StoredManagerEvent;
import com.sxt.gmms.event.SystemEvent;
import com.sxt.gmms.frame.stored.call.CallSetFrame;
import com.sxt.gmms.frame.stored.call.ShowCallFrame;

/**
 * 主窗体
 * 
 * @author ming
 * 
 */
public class MainFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	// 基本设置
	JMenu basicMenu;
	JMenuItem goodsSizeItem;
	JMenuItem goodsKindItem;
	JMenuItem goodsInfoItem;
	JMenuItem customerInfoItem;
	JMenuItem supplierInfoItem;
	JMenuItem employeeInfoItem;
	// 入库管理
	JMenu inStorageManagerMenu;
	JMenuItem inStorageItem;
	JMenuItem inReturnItem;
	// 销售管理
	JMenu saleManagerMenu;
	JMenuItem sellOrderItem;
	// 库存管理
	JMenu storedManagerMenu;
	// 系统设置
	JMenu systemMenu;
	JMenuItem dataBakItem;
	JMenuItem dataRecoveryItem;
	JMenuItem userManagerItem;
	JMenuItem aboutItem;
	JMenuItem quitSystemItem;
	private JToolBar toolBar;
	private JButton btnGoodsKind;
	private JButton btnGoodsInfo;
	private JPanel panel_1;
	private JButton btnInStorage;
	private JButton btnSale;
	private JButton btnStore;
	private JButton btnReport;
	private JLabel lblUser;
	private JLabel lblWelcome;
	private JLabel lblTime;
	private JButton btnSystem;
	private JButton btnBasic;
	private JMenuItem storedCheckItem;
	private JMenuItem storedCallItem;
	private JMenu reportMenu;
	private JMenuItem saleReportItem;
	private JMenuItem inStorageReportItem;
	private JMenuItem orderReportItem;
	private JMenuItem inStorageAllItem;
	private JMenuItem sellAllItem;
	public JDesktopPane desktopPane;
	private JMenuItem roleManagerItem;
	private JPanel panBasic;
	private JPanel panInStorage;
	private JPanel panSale;
	private JPanel panStock;
	private JPanel panReport;
	private JPanel panSystem;
	User user;
	private JMenuItem logoutItem;
	private JButton btnSupplie;
	private JButton btnVip;
	private JButton btnEmp;
	private JMenuItem inReturnAllItem;
	private JButton btnInStr;
	private JButton btnOrder;
	private JButton btnExit;
	private JMenuItem storedAllItem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(new SubstanceLookAndFeel());
			// UIManager.setLookAndFeel(SubstanceLookAndFeel.BUTTON_OPEN_SIDE_PROPERTY);
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			SubstanceLookAndFeel
					.setCurrentTheme(new SubstanceTerracottaTheme());
			SubstanceLookAndFeel.setSkin(new CremeCoffeeSkin());
			SubstanceLookAndFeel
					.setCurrentButtonShaper(new StandardButtonShaper());
			SubstanceLookAndFeel
					.setCurrentWatermark(new SubstanceBubblesWatermark());
			SubstanceLookAndFeel
					.setCurrentBorderPainter(new StandardBorderPainter());
			SubstanceLookAndFeel
					.setCurrentGradientPainter(new StandardGradientPainter());
			SubstanceLookAndFeel.setCurrentTitlePainter(new FlatTitlePainter());
		} catch (Exception e) {
			System.err.println("Something went wrong!");
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainFrame frame = new MainFrame(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame(User user) {
		this.setTitle("GM进销存管理系统1.0版本");
		this.user = user;
		// 设置全屏
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		setBounds(0, 0, width - 100, height - 80);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		basicMenu = new JMenu("基本设置(B)");
		basicMenu.setMnemonic(KeyEvent.VK_B);
		menuBar.add(basicMenu);

		goodsSizeItem = new JMenuItem("商品规格");
		goodsSizeItem.addActionListener(this);
		basicMenu.add(goodsSizeItem);

		goodsKindItem = new JMenuItem("商品类型");
		goodsKindItem.addActionListener(this);
		basicMenu.add(goodsKindItem);

		goodsInfoItem = new JMenuItem("商品资料");
		goodsInfoItem.addActionListener(this);

		JSeparator separator = new JSeparator();
		basicMenu.add(separator);
		basicMenu.add(goodsInfoItem);

		JSeparator separator_1 = new JSeparator();
		basicMenu.add(separator_1);

		customerInfoItem = new JMenuItem("客户资料");
		customerInfoItem.addActionListener(this);
		basicMenu.add(customerInfoItem);

		supplierInfoItem = new JMenuItem("供应商资料");
		supplierInfoItem.addActionListener(this);
		basicMenu.add(supplierInfoItem);

		JSeparator separator_2 = new JSeparator();
		basicMenu.add(separator_2);

		employeeInfoItem = new JMenuItem("员工资料");
		employeeInfoItem.addActionListener(this);
		basicMenu.add(employeeInfoItem);

		inStorageManagerMenu = new JMenu("采购管理(O)");
		inStorageManagerMenu.setMnemonic(KeyEvent.VK_O);
		menuBar.add(inStorageManagerMenu);

		inStorageItem = new JMenuItem("采购入库");
		inStorageItem.addActionListener(this);
		inStorageManagerMenu.add(inStorageItem);

		inReturnItem = new JMenuItem("采购退货");
		inReturnItem.addActionListener(this);

		inStorageAllItem = new JMenuItem("入库汇总");
		inStorageAllItem.addActionListener(this);
		inStorageManagerMenu.add(inStorageAllItem);
		inStorageManagerMenu.add(inReturnItem);

		inReturnAllItem = new JMenuItem("退货汇总");
		inReturnAllItem.addActionListener(this);
		inStorageManagerMenu.add(inReturnAllItem);

		saleManagerMenu = new JMenu("销售管理(S)");
		saleManagerMenu.setMnemonic(KeyEvent.VK_S);
		menuBar.add(saleManagerMenu);

		sellOrderItem = new JMenuItem("销售出库");
		sellOrderItem.addActionListener(this);
		saleManagerMenu.add(sellOrderItem);

		sellAllItem = new JMenuItem("出库汇总");
		sellAllItem.addActionListener(this);
		saleManagerMenu.add(sellAllItem);

		storedManagerMenu = new JMenu("库存管理(D)");
		storedManagerMenu.setMnemonic(KeyEvent.VK_D);
		menuBar.add(storedManagerMenu);

		storedCheckItem = new JMenuItem("库存盘点");
		storedCheckItem.addActionListener(this);
		storedManagerMenu.add(storedCheckItem);

		storedCallItem = new JMenuItem("库存报警");
		storedCallItem.addActionListener(this);

		storedAllItem = new JMenuItem("盘点汇总");
		storedAllItem.addActionListener(this);
		storedManagerMenu.add(storedAllItem);
		storedManagerMenu.add(storedCallItem);

		reportMenu = new JMenu("统计报表(R)");
		reportMenu.setMnemonic(KeyEvent.VK_R);
		menuBar.add(reportMenu);

		orderReportItem = new JMenuItem("库存报表");
		orderReportItem.addActionListener(this);
		reportMenu.add(orderReportItem);

		inStorageReportItem = new JMenuItem("入库报表");
		inStorageReportItem.addActionListener(this);
		reportMenu.add(inStorageReportItem);

		saleReportItem = new JMenuItem("销售报表");
		saleReportItem.addActionListener(this);
		reportMenu.add(saleReportItem);

		systemMenu = new JMenu("系统设置(T)");
		systemMenu.setMnemonic(KeyEvent.VK_T);
		menuBar.add(systemMenu);

		dataBakItem = new JMenuItem("数据备份");
		dataBakItem.addActionListener(this);
		systemMenu.add(dataBakItem);

		dataRecoveryItem = new JMenuItem("数据恢复");
		dataRecoveryItem.addActionListener(this);
		systemMenu.add(dataRecoveryItem);

		JSeparator separator_3 = new JSeparator();
		systemMenu.add(separator_3);

		userManagerItem = new JMenuItem("用户管理");
		userManagerItem.addActionListener(this);

		roleManagerItem = new JMenuItem("角色管理");
		roleManagerItem.addActionListener(this);
		systemMenu.add(roleManagerItem);
		systemMenu.add(userManagerItem);

		JSeparator separator_4 = new JSeparator();
		systemMenu.add(separator_4);

		aboutItem = new JMenuItem("关于");
		aboutItem.addActionListener(this);
		systemMenu.add(aboutItem);

		JSeparator separator_5 = new JSeparator();
		systemMenu.add(separator_5);

		quitSystemItem = new JMenuItem("退出系统");
		quitSystemItem.addActionListener(this);

		logoutItem = new JMenuItem("注销");
		logoutItem.addActionListener(this);
		systemMenu.add(logoutItem);
		systemMenu.add(quitSystemItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		toolBar = new JToolBar();
		toolBar.setPreferredSize(new Dimension(600, 70));
		toolBar.setBackground(UIManager.getColor("CheckBox.interiorBackground"));
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.NORTH);

		// 工具栏中的货品类别
		btnGoodsKind = new JButton();
		btnGoodsKind
				.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		toolBar.add(btnGoodsKind);
		btnGoodsKind.setIcon(new ImageIcon("image/basic/basic_03.jpg"));
		btnGoodsKind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goodsKindItem.doClick();
			}
		});
		btnGoodsKind.setToolTipText("用于管理货品类别");

		// 工具栏中的货品信息
		btnGoodsInfo = new JButton();
		btnGoodsInfo.setBorder(new LineBorder(Color.WHITE));
		toolBar.add(btnGoodsInfo);
		btnGoodsInfo.setIcon(new ImageIcon("image/basic/basic_07.jpg"));
		btnGoodsInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goodsInfoItem.doClick();
			}
		});
		btnGoodsInfo.setToolTipText("用于管理货品信息");

		btnSupplie = new JButton("");
		btnSupplie.setBorder(new LineBorder(Color.WHITE));
		toolBar.add(btnSupplie);
		btnSupplie.setIcon(new ImageIcon("image/basic/basic_11.jpg"));
		btnSupplie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				supplierInfoItem.doClick();
			}
		});
		btnSupplie.setToolTipText("用于管理供应商信息");

		btnVip = new JButton("");
		btnVip.setBorder(new LineBorder(Color.WHITE));
		toolBar.add(btnVip);
		btnVip.setIcon(new ImageIcon("image/basic/basic_13.jpg"));
		btnVip.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				customerInfoItem.doClick();
			}
		});
		btnVip.setToolTipText("用于管理客户信息");

		btnEmp = new JButton("");
		btnEmp.setBorder(new LineBorder(Color.WHITE));
		toolBar.add(btnEmp);
		btnEmp.setIcon(new ImageIcon("image/basic/basic_15.jpg"));
		btnEmp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				employeeInfoItem.doClick();
			}
		});
		btnEmp.setToolTipText("用于管理员工信息");

		btnOrder = new JButton("");
		btnOrder.setBorder(new LineBorder(Color.WHITE));
		toolBar.add(btnOrder);
		btnOrder.setIcon(new ImageIcon("image/instorage/buy_14.jpg"));
		btnOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inStorageItem.doClick();
			}
		});
		btnOrder.setToolTipText("用于采购入库");

		btnExit = new JButton("");
		btnExit.setBorder(new LineBorder(Color.WHITE));
		toolBar.add(btnExit);
		btnExit.setIcon(new ImageIcon("image/system/quit.jpg"));
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quitSystemItem.doClick();
			}
		});
		btnExit.setToolTipText("退出系统");

		// 窗体居中
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		// 计算出窗口在屏幕中间的起点 x=屏幕高-窗口高/2 y=屏幕宽-窗口宽/2
		int y = Double.valueOf((dimension.getHeight() - this.getHeight()) / 2)
				.intValue();
		int x = Double.valueOf((dimension.getWidth() - this.getWidth()) / 2)
				.intValue();
		this.setLocation(x, y);

		// 左边面板
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(200, 0));
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(null);

		JLabel lblFunction = new JLabel("");
		lblFunction.setForeground(Color.RED);
		lblFunction.setFont(new Font("幼圆", Font.BOLD, 24));
		lblFunction.setHorizontalAlignment(SwingConstants.CENTER);
		lblFunction.setBounds(0, 10, 195, 36);
		lblFunction.setText("功能导航");
		panel.add(lblFunction);

		btnInStorage = new JButton("采购管理");
		btnInStorage.addActionListener(this);
		btnInStorage.setHorizontalAlignment(SwingConstants.LEFT);
		btnInStorage.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnInStorage.setIcon(new ImageIcon("image/system/lock.png"));
		btnInStorage.setBounds(10, 93, 170, 40);
		panel.add(btnInStorage);

		btnSale = new JButton("销售管理");
		btnSale.addActionListener(this);
		btnSale.setHorizontalAlignment(SwingConstants.LEFT);
		btnSale.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnSale.setIcon(new ImageIcon("image/system/lock.png"));
		btnSale.setBounds(10, 142, 170, 40);
		panel.add(btnSale);

		btnStore = new JButton("库存管理");
		btnStore.addActionListener(this);
		btnStore.setHorizontalAlignment(SwingConstants.LEFT);
		btnStore.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnStore.setIcon(new ImageIcon("image/system/lock.png"));
		btnStore.setSelectedIcon(new ImageIcon("image/system/on.png"));
		btnStore.setBounds(10, 191, 170, 40);
		panel.add(btnStore);

		btnReport = new JButton("统计报表");
		btnReport.addActionListener(this);
		btnReport.setHorizontalAlignment(SwingConstants.LEFT);
		btnReport.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnReport.setIcon(new ImageIcon("image/system/lock.png"));
		btnReport.setBounds(10, 243, 170, 40);
		panel.add(btnReport);

		btnSystem = new JButton("系统管理");
		btnSystem.addActionListener(this);
		btnSystem.setHorizontalAlignment(SwingConstants.LEFT);
		btnSystem.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnSystem.setIcon(new ImageIcon("image/system/lock.png"));
		btnSystem.setBounds(10, 294, 170, 40);
		panel.add(btnSystem);

		btnBasic = new JButton("基础资料");
		btnBasic.addActionListener(this);
		btnBasic.setHorizontalAlignment(SwingConstants.LEFT);
		btnBasic.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnBasic.setIcon(new ImageIcon("image/system/lock.png"));
		btnBasic.setBounds(10, 48, 170, 40);
		panel.add(btnBasic);

		JTextArea txtTip = new JTextArea();
		txtTip.setBorder(new LineBorder(Color.WHITE));
		txtTip.setEditable(false);
		txtTip.setBackground(UIManager.getColor("CheckBox.interiorBackground"));
		txtTip.setBounds(10, 384, 170, 147);
		panel.add(txtTip);

		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(0, 20));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 3, 0, 0));

		// 下方提示当前用户
		lblUser = new JLabel();
		lblUser.setText("当前用户:                 " + user.getUserAccount());
		lblUser.setForeground(Color.BLUE);
		lblUser.setIcon(new ImageIcon("image/main/users.png"));
		lblUser.setBorder(new BevelBorder(BevelBorder.LOWERED));
		panel_1.add(lblUser);

		// 欢迎用户使用
		lblWelcome = new JLabel();
		lblWelcome.setForeground(Color.BLUE);
		lblWelcome.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setForeground(Color.BLUE);
		lblWelcome.setText("欢迎使用GM进存销管理系统！");
		lblWelcome.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblWelcome.setIcon(new ImageIcon("image/main/welcome.png"));
		panel_1.add(lblWelcome);

		// 时间提示栏
		lblTime = new JLabel();
		lblTime.setBorder(new BevelBorder(BevelBorder.LOWERED));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);

		lblTime.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblTime.setForeground(Color.RED);
		panel_1.add(lblTime);

		desktopPane = new JDesktopPane();
		desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		desktopPane.setBackground(UIManager
				.getColor("CheckBox.interiorBackground"));
		contentPane.add(desktopPane, BorderLayout.CENTER);

		// 基础面板
		panBasic = new JPanel();
		panBasic.setBounds(10, 10, 914, 521);
		desktopPane.add(panBasic);
		panBasic.setLayout(null);

		JButton btnBasicEmp = new JButton("员工资料");
		btnBasicEmp.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnBasicEmp.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBasicEmp.setIcon(new ImageIcon("image/basic/1.png"));
		btnBasicEmp.setBounds(205, 94, 155, 155);
		btnBasicEmp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				employeeInfoItem.doClick();
			}
		});
		panBasic.add(btnBasicEmp);

		JButton btnBasicGoods = new JButton("商品资料");
		btnBasicGoods.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnBasicGoods.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBasicGoods.setIcon(new ImageIcon("image/basic/2.png"));
		btnBasicGoods.setBounds(415, 195, 155, 155);
		btnBasicGoods.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goodsInfoItem.doClick();
			}
		});
		panBasic.add(btnBasicGoods);

		JButton btnBasicKind = new JButton("商品类别");
		btnBasicKind.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnBasicKind.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBasicKind.setIcon(new ImageIcon("image/basic/3.png"));
		btnBasicKind.setBounds(618, 94, 155, 155);
		btnBasicKind.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goodsKindItem.doClick();
			}
		});
		panBasic.add(btnBasicKind);

		JButton btnBasicSupplie = new JButton("供应商资料");
		btnBasicSupplie.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnBasicSupplie.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBasicSupplie.setIcon(new ImageIcon("image/basic/4.png"));
		btnBasicSupplie.setBounds(205, 311, 155, 155);
		btnBasicSupplie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				supplierInfoItem.doClick();
			}
		});
		panBasic.add(btnBasicSupplie);

		JButton btnBasicVip = new JButton("客户资料");
		btnBasicVip.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnBasicVip.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBasicVip.setIcon(new ImageIcon("image/basic/5.png"));
		btnBasicVip.setBounds(618, 311, 155, 155);
		btnBasicVip.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				customerInfoItem.doClick();
			}
		});
		panBasic.add(btnBasicVip);
		panBasic.setVisible(false);

		// 入库面板
		panInStorage = new JPanel();
		panInStorage.setBounds(10, 10, 914, 521);
		desktopPane.add(panInStorage);
		panInStorage.setLayout(null);

		JButton btnInOrder = new JButton("采购入库");
		btnInOrder.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnInOrder.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnInOrder.setHorizontalTextPosition(SwingConstants.CENTER);
		btnInOrder.setIcon(new ImageIcon("image/instorage/6.png"));
		btnInOrder.setBounds(250, 200, 155, 155);
		btnInOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				inStorageItem.doClick();
			}
		});
		panInStorage.add(btnInOrder);

		JButton btnOrderAll = new JButton("入库汇总");
		btnOrderAll.setAlignmentX(1.0f);
		btnOrderAll.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnOrderAll.setHorizontalTextPosition(SwingConstants.CENTER);
		btnOrderAll.setIcon(new ImageIcon("image/instorage/7.png"));
		btnOrderAll.setBounds(550, 200, 155, 155);
		btnOrderAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				inStorageAllItem.doClick();
			}
		});
		panInStorage.add(btnOrderAll);
		panInStorage.setVisible(false);

		// 销售面板
		panSale = new JPanel();
		panSale.setBounds(10, 10, 904, 521);
		desktopPane.add(panSale);
		panSale.setLayout(null);

		JButton btnSaleOrder = new JButton("销售出库");
		btnSaleOrder.setAlignmentX(1.0f);
		btnSaleOrder.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSaleOrder.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSaleOrder.setIcon(new ImageIcon("image/sale/10.png"));
		btnSaleOrder.setBounds(250, 200, 155, 155);
		btnSaleOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sellOrderItem.doClick();
			}
		});
		panSale.add(btnSaleOrder);

		JButton btnAllSale = new JButton("出库汇总");
		btnAllSale.setAlignmentX(1.0f);
		btnAllSale.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAllSale.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAllSale.setIcon(new ImageIcon("image/sale/11.png"));
		btnAllSale.setBounds(550, 200, 155, 155);
		btnAllSale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sellAllItem.doClick();
			}
		});
		panSale.add(btnAllSale);
		panSale.setVisible(false);

		// 库存面板
		panStock = new JPanel();
		panStock.setBounds(10, 10, 904, 521);
		desktopPane.add(panStock);
		panStock.setLayout(null);

		JButton btnStoredCall = new JButton("库存报警");
		btnStoredCall.setAlignmentX(1.0f);
		btnStoredCall.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnStoredCall.setHorizontalTextPosition(SwingConstants.CENTER);
		btnStoredCall.setIcon(new ImageIcon("image/stored/12.png"));
		btnStoredCall.setBounds(230, 94, 155, 155);
		btnStoredCall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				storedCallItem.doClick();
			}
		});
		panStock.add(btnStoredCall);

		JButton btnCheckInfo = new JButton("库存盘点");
		btnCheckInfo.setAlignmentX(1.0f);
		btnCheckInfo.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCheckInfo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCheckInfo.setIcon(new ImageIcon("image/stored/13.png"));
		btnCheckInfo.setBounds(580, 94, 155, 155);
		btnCheckInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				storedCheckItem.doClick();
			}
		});
		panStock.add(btnCheckInfo);

		JButton btnAllInfo = new JButton("盘点汇总");
		btnAllInfo.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAllInfo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAllInfo.setIcon(new ImageIcon("image/stored/14.png"));
		btnAllInfo.setAlignmentX(1.0f);
		btnAllInfo.setBounds(420, 311, 155, 155);
		panStock.add(btnAllInfo);
		btnAllInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				storedAllItem.doClick();
			}
		});
		panStock.setVisible(false);

		// 报表面板
		panReport = new JPanel();
		panReport.setBounds(10, 10, 904, 521);
		desktopPane.add(panReport);
		panReport.setLayout(null);

		JButton btnOrderReport = new JButton("库存报表");
		btnOrderReport.setAlignmentX(1.0f);
		btnOrderReport.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnOrderReport.setHorizontalTextPosition(SwingConstants.CENTER);
		btnOrderReport.setIcon(new ImageIcon("image/report/15.png"));
		btnOrderReport.setBounds(230, 94, 155, 155);
		btnOrderReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				orderReportItem.doClick();
			}
		});
		panReport.add(btnOrderReport);

		JButton btnInReport = new JButton("入库报表");
		btnInReport.setAlignmentX(1.0f);
		btnInReport.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnInReport.setHorizontalTextPosition(SwingConstants.CENTER);
		btnInReport.setIcon(new ImageIcon("image/report/16.png"));
		btnInReport.setBounds(580, 94, 155, 155);
		btnInReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				inStorageReportItem.doClick();
			}
		});
		panReport.add(btnInReport);

		JButton btnSaleReport = new JButton("销售报表");
		btnSaleReport.setAlignmentX(1.0f);
		btnSaleReport.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSaleReport.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSaleReport.setIcon(new ImageIcon("image/report/18.png"));
		btnSaleReport.setBounds(420, 311, 155, 155);
		btnSaleReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				saleReportItem.doClick();
			}
		});
		panReport.add(btnSaleReport);
		panReport.setVisible(false);

		// 系统面板
		panSystem = new JPanel();
		panSystem.setBounds(10, 10, 904, 521);
		desktopPane.add(panSystem);
		panSystem.setLayout(null);

		JButton btnSystemDataBack = new JButton("数据备份");
		btnSystemDataBack.setAlignmentX(1.0f);
		btnSystemDataBack.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSystemDataBack.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSystemDataBack.setIcon(new ImageIcon("image/system/1.png"));
		btnSystemDataBack.setBounds(205, 94, 155, 155);
		btnSystemDataBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dataBakItem.doClick();
			}
		});
		panSystem.add(btnSystemDataBack);

		JButton btnSystemDataRecover = new JButton("数据恢复");
		btnSystemDataRecover.setAlignmentX(1.0f);
		btnSystemDataRecover.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSystemDataRecover.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSystemDataRecover.setIcon(new ImageIcon("image/system/2.png"));
		btnSystemDataRecover.setBounds(618, 94, 155, 155);
		btnSystemDataRecover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dataRecoveryItem.doClick();
			}
		});
		panSystem.add(btnSystemDataRecover);

		JButton btnSystemRoleManager = new JButton("角色管理");
		btnSystemRoleManager.setAlignmentX(1.0f);
		btnSystemRoleManager.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSystemRoleManager.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSystemRoleManager.setIcon(new ImageIcon("image/system/3.png"));
		btnSystemRoleManager.setBounds(415, 94, 155, 155);
		btnSystemRoleManager.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				roleManagerItem.doClick();
			}
		});
		panSystem.add(btnSystemRoleManager);

		JButton btnSystemUserManager = new JButton("用户管理");
		btnSystemUserManager.setAlignmentX(1.0f);
		btnSystemUserManager.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSystemUserManager.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSystemUserManager.setIcon(new ImageIcon("image/system/4.png"));
		btnSystemUserManager.setBounds(205, 311, 155, 155);
		btnSystemUserManager.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userManagerItem.doClick();
			}
		});
		panSystem.add(btnSystemUserManager);

		JButton btnSystemExit = new JButton("退出系统");
		btnSystemExit.setAlignmentX(1.0f);
		btnSystemExit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSystemExit.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSystemExit.setIcon(new ImageIcon("image/system/5.png"));
		btnSystemExit.setBounds(618, 311, 155, 155);
		btnSystemExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				quitSystemItem.doClick();
			}
		});
		panSystem.add(btnSystemExit);

		JButton btnZhuxiao = new JButton("注销");
		btnZhuxiao.setAlignmentX(1.0f);
		btnZhuxiao.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnZhuxiao.setHorizontalTextPosition(SwingConstants.CENTER);
		btnZhuxiao.setIcon(new ImageIcon("image/system/6.png"));
		btnZhuxiao.setBounds(415, 311, 155, 155);
		btnZhuxiao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				logoutItem.doClick();
			}
		});
		panSystem.add(btnZhuxiao);
		panSystem.setVisible(false);

		// 设置欢迎标栏烁
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				// 欢迎栏闪烁
				Timer timerWelcome = new Timer(1000, new ActionListener() {
					String welcome = lblWelcome.getText();

					@Override
					public void actionPerformed(ActionEvent e) {
						// 时间
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy年MM月dd日 HH:mm:ss EE ");
						lblTime.setText(sdf.format(date));

						if (lblWelcome.getText().isEmpty()) {
							lblWelcome.setText(welcome);
							lblWelcome.setIcon(new ImageIcon(
									"image/main/welcome.png"));
						} else {
							lblWelcome.setText("");
							lblWelcome.setIcon(null);
						}
					}
				});
				timerWelcome.start();
			}

			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(MainFrame.this,
						"确认退出吗?", "温馨提示", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

		this.setIconImage(new ImageIcon("image/main/ico.png").getImage());
		// 检查用户
		checkAuth(user);
		btnBasic.doClick();

		// 设置按钮的形状
		SubstanceLookAndFeel.setCurrentButtonShaper(new StandardButtonShaper());
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				// 检测预警值，提示用户少于预警值的商品
				checkCallNum();
			}
		});
	}

	/**
	 * 事件处理
	 */
	public void actionPerformed(ActionEvent e) {
		// 基本设置
		if (e.getSource() == goodsSizeItem || e.getSource() == goodsKindItem
				|| e.getSource() == goodsInfoItem
				|| e.getSource() == customerInfoItem
				|| e.getSource() == supplierInfoItem
				|| e.getSource() == employeeInfoItem) {
			String event = e.getActionCommand().toString();
			BaseEvent baseEvent = new BaseEvent(this, event);
			baseEvent.doActionEvent();
			// 入库管理
		} else if (e.getSource() == inStorageItem
				|| e.getSource() == inReturnItem
				|| e.getSource() == inStorageAllItem
				|| e.getSource() == inReturnAllItem) {
			String event = e.getActionCommand().toString();
			System.out.println(event);
			InStorageEvent inStorageEvent = new InStorageEvent(this, event);
			inStorageEvent.doActionEvent();
			// 销售管理
		} else if (e.getSource() == sellOrderItem
				|| e.getSource() == sellAllItem) {
			String event = e.getActionCommand().toString();
			SaleManagerEvent saleManagerEvent = new SaleManagerEvent(this,
					event);
			saleManagerEvent.doActionEvent();
			// 库存管理
		} else if (e.getSource() == storedCallItem
				|| e.getSource() == storedCheckItem
				|| e.getSource() == storedAllItem) {
			String event = e.getActionCommand().toString();
			StoredManagerEvent storedManagerEvent = new StoredManagerEvent(
					this, event);
			storedManagerEvent.doActionEvent();
			// 统计报表
		} else if (e.getSource() == orderReportItem
				|| e.getSource() == inStorageReportItem
				|| e.getSource() == saleReportItem) {
			String event = e.getActionCommand().toString();
			ReportManagerEvent reportManagerEvent = new ReportManagerEvent(
					this, event);
			reportManagerEvent.doActionEvent();
			// 财务管理
		} else if (e.getSource() == dataBakItem
				|| e.getSource() == dataRecoveryItem
				|| e.getSource() == userManagerItem
				|| e.getSource() == aboutItem
				|| e.getSource() == quitSystemItem
				|| e.getSource() == roleManagerItem
				|| e.getSource() == logoutItem) {
			String event = e.getActionCommand().toString();
			SystemEvent systemEvent = new SystemEvent(this, event);
			systemEvent.doActionEvent();
		} else if (e.getSource() == btnBasic) {
			panBasic.setVisible(true);
			panInStorage.setVisible(false);
			panSale.setVisible(false);
			panStock.setVisible(false);
			panReport.setVisible(false);
			panSystem.setVisible(false);
		} else if (e.getSource() == btnInStorage) {
			panBasic.setVisible(false);
			panInStorage.setVisible(true);
			panSale.setVisible(false);
			panStock.setVisible(false);
			panReport.setVisible(false);
			panSystem.setVisible(false);
		} else if (e.getSource() == btnSale) {
			panBasic.setVisible(false);
			panInStorage.setVisible(false);
			panSale.setVisible(true);
			panStock.setVisible(false);
			panReport.setVisible(false);
			panSystem.setVisible(false);
		} else if (e.getSource() == btnStore) {
			panBasic.setVisible(false);
			panInStorage.setVisible(false);
			panSale.setVisible(false);
			panStock.setVisible(true);
			panReport.setVisible(false);
			panSystem.setVisible(false);
		} else if (e.getSource() == btnReport) {
			panBasic.setVisible(false);
			panInStorage.setVisible(false);
			panSale.setVisible(false);
			panStock.setVisible(false);
			panReport.setVisible(true);
			panSystem.setVisible(false);
		} else if (e.getSource() == btnSystem) {
			panBasic.setVisible(false);
			panInStorage.setVisible(false);
			panSale.setVisible(false);
			panStock.setVisible(false);
			panReport.setVisible(false);
			panSystem.setVisible(true);
		}

	}

	/**
	 * 检查用户的权限
	 * 
	 * @param user
	 */
	public void checkAuth(User user) {
		String roleName = user.getRoleId().getRoleName();
		if (roleName.equals("销售员")) {
			// 左边按钮
			btnReport.setEnabled(false);
			btnSystem.setEnabled(false);
			btnBasic.setEnabled(false);
			// 上边按钮
			reportMenu.setEnabled(false);
			dataRecoveryItem.setEnabled(false);
			roleManagerItem.setEnabled(false);
			userManagerItem.setEnabled(false);
		}
	}

	/**
	 * 检测预警值
	 */
	public void checkCallNum() {
		CallSetFrame callSetFrame = new CallSetFrame(this);
		String fileName = "config/call.properties";
		int qty = callSetFrame.parseProperties(fileName);
		StoredDao storedDao = new StoredDao();
		List<Stock> stockList = storedDao.findStockByCallNum(qty);
		try {
			ShowCallFrame showCallFrame = new ShowCallFrame(this,stockList);
			desktopPane.add(showCallFrame);
			showCallFrame.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
}
