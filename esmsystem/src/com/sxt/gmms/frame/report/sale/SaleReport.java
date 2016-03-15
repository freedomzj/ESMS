package com.sxt.gmms.frame.report.sale;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.List;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import com.sxt.gmms.dao.sale.SellItemDao;
import com.sxt.gmms.entity.SellItem;
import com.sxt.gmms.frame.MainFrame;

public class SaleReport {

	/**
	 * Create the frame.
	 */
	public SaleReport(MainFrame mf) {
		SellItemDao itemDao = new SellItemDao();
		List<SellItem> itemList = itemDao.loadSellItemReport();

		// 数据集
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		for (SellItem item : itemList) {
			dataSet.addValue(item.getSeItemQty(), "", item.getGoods()
					.getGoodsName());
		}

		JFreeChart chart = ChartFactory.createBarChart3D("销售统计图", "产品名称",
				"销售量", dataSet, PlotOrientation.VERTICAL, true, true, true);
		CategoryPlot cp = chart.getCategoryPlot();
		// 设置总的背景颜色
		chart.setBackgroundPaint(ChartColor.LIGHT_GRAY);

		NumberAxis numberaxis = (NumberAxis) cp.getRangeAxis();
		CategoryAxis domainAxis = cp.getDomainAxis();
		// 1、 图表标题以及副标题乱码
		Font font = new Font("宋体", Font.BOLD, 16);
		TextTitle title = new TextTitle("销售统计图", font);
		// 副标题
		TextTitle subtitle = new TextTitle("总的销售量", new Font("黑体", Font.BOLD,
				12));
		chart.addSubtitle(subtitle);
		chart.setTitle(title); // 标题
		// 2、 X轴乱码
		// 2.1、X轴坐标上的文字：
		domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
		// 2.2、X轴坐标标题（肉类）
		domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
		// 3、 Y轴乱码
		// 3.1、Y轴坐标上的文字：
		numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
		// 3.2、Y轴坐标标题（销量）：
		numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));
		// 4、 图表底部乱码（猪肉等文字）
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));

		BarRenderer3D renderer = new BarRenderer3D();// 3D属性修改
		cp.setRenderer(renderer);// 将修改后的属性值保存到图中

		cp.setDomainGridlinePaint(Color.blue);
		cp.setDomainGridlinesVisible(true);
		// 设置网格横线颜色
		cp.setRangeGridlinePaint(Color.blue);
		cp.setRangeGridlinesVisible(true);
		// 图片背景色
		cp.setBackgroundPaint(Color.LIGHT_GRAY);
		cp.setOutlineVisible(true);
		// 图边框颜色
		cp.setOutlinePaint(Color.magenta);
		// 边框颜色
		renderer.setBaseOutlinePaint(Color.ORANGE);
		renderer.setDrawBarOutline(true);
		// 设置墙体颜色
		renderer.setWallPaint(Color.gray);

		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER_LEFT));
		renderer.setBaseItemLabelsVisible(true);
		renderer.setItemLabelAnchorOffset(10);
		renderer.setBaseItemLabelPaint(Color.BLUE);// 设置数值颜色，默认黑色
		// renderer.setSeriesPaint(0, Color.GREEN); // 设置柱的颜色
		domainAxis.setUpperMargin(0.15);// 设置距离图片左端距离
		domainAxis.setLowerMargin(0.15); // 设置距离图片右端距离

		String path = "image/report/sale.png";
		File file = new File(path);
		if(file.exists()) {
			file.delete();
		}
		try {
			ChartUtilities.saveChartAsPNG(file, chart, 600, 480);
			SaleReportFrame saleReportFrame = new SaleReportFrame(mf, path);
			mf.desktopPane.add(saleReportFrame);
			saleReportFrame.setSelected(true);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
