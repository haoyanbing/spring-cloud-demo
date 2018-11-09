package cn.finwood.demo.common.util;

import java.security.MessageDigest;

/**
 * MD5工具类
 * created by haoyanbing on 2018/11/9 15:06
 */
public class MD5Util {

	private final static String ENCODE_MODE = "UTF-8";
	private final static String MD5 = "MD5";

	/**
	 * MD5加密
	 * @param source
	 * @return
	 */
	public static String md5(String source) {
		try {
			MessageDigest md5 = MessageDigest.getInstance(MD5);
			byte[] result = md5.digest(source.getBytes(ENCODE_MODE));
			StringBuffer buffer = new StringBuffer();
			for (byte b : result) {
				int num = b & 0xff;
				String str = Integer.toHexString(num);
				if (str.length() == 1) {
                    buffer.append("0");
				}
				buffer.append(str);
			}
			return buffer.toString();
		} catch (Exception e) {
			// TODO 抛出异常
		}
		return null;
	}

}
