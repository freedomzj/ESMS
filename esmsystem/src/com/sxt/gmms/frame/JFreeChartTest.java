package com.sxt.gmms.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.text.DecimalFormat;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

public class JFreeChartTest {

	public static void main(String[] args) {
		createColumnChart();
	}

	public static void createColumnChart() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(100, "北京", "苹果");
		dataset.addValue(100, "上海", "苹果");
		dataset.addValue(100, "广州", "苹果");
		dataset.addValue(200, "北京", "梨子");
		dataset.addValue(200, "上海", "梨子");
		dataset.addValue(200, "广州", "梨子");
		dataset.addValue(300, "北京", "葡萄");
		dataset.addValue(300, "上海", "葡萄");
		dataset.addValue(300, "广州", "葡萄");
		dataset.addValue(400, "北京", "香蕉");
		dataset.addValue(400, "上海", "香蕉");
		dataset.addValue(400, "广州", "香蕉");
		dataset.addValue(500, "北京", "荔枝");
		dataset.addValue(500, "上海", "荔枝");
		dataset.addValue(500, "广州", "荔枝");
		JFreeChart chart = ChartFactory.createBarChart3D("水果产量图", "水量", "产量",
				dataset, PlotOrientation.VERTICAL, true, false, true);
		
		// 设置总的背景颜色
		chart.setBackgroundPaint(ChartColor.GRAY);
		// 设置标题颜色
		chart.getTitle().setPaint(ChartColor.blue);
		// 获得图表对象
		CategoryPlot p = chart.getCategoryPlot();
		// 设置图的背景颜色
		p.setBackgroundPaint(ChartColor.WHITE);
		// 设置表格线颜色
		p.setRangeGridlinePaint(ChartColor.red);
		
		/*----------设置消除字体的锯齿渲染（解决中文问题）--------------*/
		chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		// 底部汉字乱码的问题
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
		// 设置标题字体
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("黑体", Font.PLAIN, 20));
		textTitle.setBackgroundPaint(Color.LIGHT_GRAY);// 标题背景色
		textTitle.setPaint(Color.cyan);// 标题字体颜色
		textTitle.setText("类型统计图");// 标题内容
		CategoryPlot plot = chart.getCategoryPlot();// 设置图的高级属性
		BarRenderer3D renderer = new BarRenderer3D();// 3D属性修改
		CategoryAxis domainAxis = plot.getDomainAxis();// 对X轴做操作
		ValueAxis rAxis = plot.getRangeAxis();// 对Y轴做操作
		/***
		 * domainAxis设置(x轴一些设置)
		 **/
		// 设置X轴坐标上的文字
		domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
		// 设置X轴的标题文字
		domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
		domainAxis.setLabel("");// X轴的标题内容
		domainAxis.setTickLabelPaint(Color.red);// X轴的标题文字颜色
		domainAxis.setTickLabelsVisible(true);// X轴的标题文字是否显示
		domainAxis.setAxisLinePaint(Color.red);// X轴横线颜色
		domainAxis.setTickMarksVisible(true);// 标记线是否显示
		domainAxis.setTickMarkOutsideLength(3);// 标记线向外长度
		domainAxis.setTickMarkInsideLength(3);// 标记线向内长度
		domainAxis.setTickMarkPaint(Color.red);// 标记线颜色
		domainAxis.setUpperMargin(0.2);// 设置距离图片左端距离
		domainAxis.setLowerMargin(0.2); // 设置距离图片右端距离
		// 横轴上的 Lable 是否完整显示
		domainAxis.setMaximumCategoryLabelWidthRatio(0.6f);
		// 横轴上的 Lable 45度倾斜
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
		/**
		 * rAxis设置 Y轴设置
		 * 
		 **/
		// 设置Y轴坐标上的文字
		rAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
		// 设置Y轴的标题文字
		rAxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));
		// Y轴取值范围（下面不能出现 rAxis.setAutoRange(true) 否则不起作用）
		rAxis.setRange(100, 600);
		// rAxis.setLowerBound(100); //Y轴以开始的最小值
		// rAxis.setUpperBound(600);//Y轴的最大值
		rAxis.setLabel("content");// Y轴内容
		rAxis.setLabelAngle(1.6);// 标题内容显示角度（1.6时候水平）
		rAxis.setLabelPaint(Color.red);// 标题内容颜色
		rAxis.setMinorTickMarksVisible(true);// 标记线是否显示
		rAxis.setMinorTickCount(7);// 节段中的刻度数
		rAxis.setMinorTickMarkInsideLength(3);// 内刻度线向内长度
		rAxis.setMinorTickMarkOutsideLength(3);// 内刻度记线向外长度
		rAxis.setTickMarkInsideLength(3);// 外刻度线向内长度
		rAxis.setTickMarkPaint(Color.red);// 刻度线颜色
		rAxis.setTickLabelsVisible(true);// 刻度数值是否显示
		// 所有Y标记线是否显示（如果前面设置rAxis.setMinorTickMarksVisible(true); 则其照样显示）
		rAxis.setTickMarksVisible(true);
		rAxis.setAxisLinePaint(Color.red);// Y轴竖线颜色
		rAxis.setAxisLineVisible(true);// Y轴竖线是否显示
		// 设置最高的一个 Item 与图片顶端的距离 (在设置rAxis.setRange(100, 600);情况下不起作用)
		rAxis.setUpperMargin(0.15);
		// 设置最低的一个 Item 与图片底端的距离
		rAxis.setLowerMargin(0.15);
		rAxis.setAutoRange(true);// 是否自动适应范围
		rAxis.setVisible(true);// Y轴内容是否显示
		// 数据轴精度
		NumberAxis na = (NumberAxis) plot.getRangeAxis();
		na.setAutoRangeIncludesZero(true);
		DecimalFormat df = new DecimalFormat("#0.000");
		// 数据轴数据标签的显示格式
		na.setNumberFormatOverride(df);
		/**
		 * renderer设置 柱子相关属性设置
		 */
		renderer.setBaseOutlinePaint(Color.ORANGE); // 边框颜色
		renderer.setDrawBarOutline(true);
		renderer.setWallPaint(Color.gray);// 设置墙体颜色
		renderer.setMaximumBarWidth(0.08); // 设置柱子宽度
		renderer.setMinimumBarLength(0.1); // 设置柱子高度
		renderer.setSeriesPaint(0, Color.ORANGE); // 设置柱的颜色
		renderer.setItemMargin(0); // 设置每个地区所包含的平行柱的之间距离
		// 在柱子上显示相应信息
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseItemLabelPaint(Color.BLACK);// 设置数值颜色，默认黑色
		// 搭配ItemLabelAnchor TextAnchor
		// 这两项能达到不同的效果，但是ItemLabelAnchor最好选OUTSIDE，因为INSIDE显示不出来
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.CENTER_LEFT));
		// 下面可以进一步调整数值的位置，但是得根据ItemLabelAnchor选择情况.
		renderer.setItemLabelAnchorOffset(10);

		/**
		 * plot 设置
		 ***/
		// 设置网格竖线颜色
		plot.setDomainGridlinePaint(Color.blue);
		plot.setDomainGridlinesVisible(true);
		// 设置网格横线颜色
		plot.setRangeGridlinePaint(Color.blue);
		plot.setRangeGridlinesVisible(true);
		// 图片背景色
		plot.setBackgroundPaint(Color.LIGHT_GRAY);
		plot.setOutlineVisible(true);
		// 图边框颜色
		plot.setOutlinePaint(Color.magenta);
		// 设置柱的透明度
		plot.setForegroundAlpha(1.0f);
		// 将类型放到上面
		plot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
		// 将默认放到左边的数值放到右边
		plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
		plot.setRenderer(renderer);// 将修改后的属性值保存到图中

		ChartFrame frame = new ChartFrame("进货统计图 ", chart, true);
		frame.pack();
		frame.setResizable(false);
		frame.setLocation(400, 220);
		frame.setVisible(true);
	}
}
