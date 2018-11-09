package cn.finwood.demo.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 字符串工具类
 * created by haoyanbing on 2018/11/9 15:06
 */
public final class StringUtils {

	/**
	 * 字符串判空
	 * 
	 * @param source
	 * @return
	 */
	public static boolean isEmpty(String source) {
		if (source == null || source.equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串长度
	 * 
	 * @param source
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean judgeLength(String source, int min, int max) {
		int length = -1;
		if (isEmpty(source)) {
			length = 0;
		} else {
			length = source.length();
		}
		if (length >= min && length <= max) {
			return true;
		}
		return false;
	}

	public static boolean judgeLength(String source, int min) {
		return judgeLength(source, min, Integer.MAX_VALUE);
	}

	/**
	 * 生成短信验证码（纯数字）
	 * 
	 * @param length
	 * @return
	 */
	public static String createSmsCode(int length) {
		Random random = new Random();
		int min = (int) Math.pow(10, length - 1) + 1;
		int max = (int) Math.pow(10, length);
		int code = random.nextInt(max) % (max - min) + min;
		return Integer.toString(code);
	}

	/**
	 * 生成随机字符串
	 * 
	 * @return
	 */
	public static String createRandomCode(int length) {
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		return createRandom(length,base);
	}
	
	/**
	 * 生成随机指定位数的数字
	 * @param length
	 * @return
	 */
	public static String createRandomNum(int length)
	{
		String base = "0123456789";
		return createRandom(length,base);
	}
	
	/**
	 * 创建随机数
	 * @param length
	 * @param baseChar
	 * @return
	 */
	public static String createRandom(int length ,String baseChar)
	{
		Random random = new Random();
		StringBuffer code = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(baseChar.length());
			code.append(baseChar.charAt(number));
		}
		return code.toString();
	}

	/**
	 * 用指定字符串切分
	 * 
	 * @param source
	 * @param seperator
	 * @return
	 */
	public static List<String> split(String source, String seperator) {
		if (StringUtils.isEmpty(source)) {
			return null;
		}
		if (seperator.equals("|") || seperator.equals(".")) {
			seperator = "\\" + seperator;
		}
		String[] temp = source.split(seperator, -1);
		List<String> lstRet = new ArrayList<String>();
		for (String item : temp) {
			lstRet.add(item);
		}
		return lstRet;
	}

	/**
	 * 隐藏字符
	 * 
	 * @param start
	 * @param end
	 * @param replaceChart
	 */
	public static String hidePartial(String source, int start, int end, String replaceChart) {
		if (isEmpty(source)) {
			return "";
		}
		if (start > end) {
			return source;
		}
		if (start < 0) {
			start = 0;
		}
		if (end > source.length() - 1) {
			end = source.length() - 1;
		}
		String header = "";
		if (start >= 1) {
			header = source.substring(0, start);
		}

		String footer = "";
		if (end < source.length() - 1) {
			footer = source.substring(end );
		}
		StringBuilder builder = new StringBuilder(header);
		for (int i = 0; i < end - start; i++) {
			builder.append(replaceChart);
		}
		builder.append(footer);
		return builder.toString();
	}
	
	/**
	 * 空值转换
	 * @param text
	 * @return
	 */
	public static String nullToEmpty(String text)
	{
		return (text == null) ? "" : text.trim();
	}
	

}
