package com.sxt.gmms.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * 
 * @author Huang
 * 对数字进行格式化得工具
 */
public class NumberUtil {
	
	/**
	 *  方法1：格式数字为只有小数点2位解决输出
	 */
	public static float formatNumberTwo(float num){
		NumberFormat nf=NumberFormat.getNumberInstance(Locale.CHINA);
		//设置小数点后只有两位
		nf.setMaximumFractionDigits(2);
		return Float.parseFloat(nf.format(num));
	}
	
	/**
	 *  方法1：格式数字为只有小数点2位解决输出
	 */
	public static float formatNumber(float num){
		String numStr=""+num;
		int count=numStr.indexOf('.');
		if(count!=-1){
			numStr=numStr.substring(0, count+2);
		}
		return Float.parseFloat(numStr);
	}
	
	
	/**
	 * 方法2：格式数字为只有小数点2位
	 */
	public static float formatNumber(String numStr){
		DecimalFormat df=new DecimalFormat("#.##");
		try {
			Number number=df.parse(numStr);
			return number.floatValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Float.parseFloat(numStr);
	}
	
	

}
