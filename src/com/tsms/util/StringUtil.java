package com.tsms.util;

public class StringUtil {

	
	/**
	 * 判断不为空的字符串
	 * @param str
	 * @return
	 */
	public static String isNotNullStr(String str) {
		return isNotBlank(str)?str:"";
	}
	
	/**
	 * 判断为空
	 * @param s
	 * @return
	 */
	public static boolean isBlank(String s) {
		return "".equals(s) || s == null ?true:false;
	}
	
	/**
	 * 判断不为空
	 * @param s
	 * @return
	 */
	public static boolean isNotBlank(String s) {
		return !"".equals(s) && s != null ?true:false;
	}
	
	/**
	 * 字符串转换为Integer
	 * @param s
	 * @return
	 */
	public static Integer stringToInteger(String s) {
		return isNotBlank(s) ? Integer.parseInt(s):0;
	}
	
	/**
	 * 判断部位null和长度不小于0
	 * @param param
	 * @return
	 */
	public static boolean isNotNull(String param) {
		return param != null && param.toString().length()>0 ? true : false;
	}
}
