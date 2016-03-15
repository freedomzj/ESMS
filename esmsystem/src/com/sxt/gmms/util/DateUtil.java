package com.sxt.gmms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @author Administrator
 *
 */
public class DateUtil {
	
	/**
	 * 将字符串日期格式化成指定格式的Date
	 * @param dateStr 字符串日期
	 * @return 返回格式后的date
	 */
	public static Date formatStringToDate(String dateStr){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将字符串日期格式化成精确格式的Date
	 * @param dateStr 字符串日期
	 * @return 返回格式后的date
	 */
	public static Date formatStringToDateExact(String dateStr){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 将date类型转化为制定字符串类型
	 */
	public static String DateStringToString(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 String dateStr=sdf.format(date);
		return dateStr;
	}
	
	
	
	
	/**
	 * 将字符串日期yyyy-MM-dd格式转化成yyyyMMdd格式
	 */
	public static String formatStringToString(String oldDateStr){
		try {
			SimpleDateFormat sdfOld=new SimpleDateFormat("yyyy-MM-dd");
			Date dateOld=sdfOld.parse(oldDateStr);
			SimpleDateFormat sdfNew=new SimpleDateFormat("yyyyMMdd");
			String newDateStr=sdfNew.format(dateOld);
			return newDateStr;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return  null;
	}
	
	
	/**
	 * 将日期转换成精确到秒的字符串
	 */
	 public static String DateToStringExact(Date date){
		 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String dateStr=sdf.format(date);
		 return dateStr;
	 }
	 
	 /**
	  * 将日期字符串转化成精确到秒的日期
	  */
	 public static Date StringToDateExact(String dateStr){
		 Date date=null;
		 try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     date=sdf.parse(dateStr);   
		 } catch (Exception e) {
			e.printStackTrace();
		}
		 return date;
		 
	 }
	 

}
