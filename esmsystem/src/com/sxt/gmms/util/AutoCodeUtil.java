package com.sxt.gmms.util;

import java.awt.image.SampleModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 自动生成编码的工具
 * 
 * @author Administrator
 * 
 */
public class AutoCodeUtil {

	/**
	 * 
	 * 自动产生采购订单编号
	 * 
	 * @param head
	 *            编号头
	 * @param dateStrOld
	 *            日期
	 * @return 返回产生编号
	 */
	public static String createOrderCode(String head) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStrNew = sdf.format(date);
		System.out.println(dateStrNew);
		return head + dateStrNew;
	}

	public static void main(String[] args) {
		System.out.println(createOrderCode("hehe"));
	}

}
