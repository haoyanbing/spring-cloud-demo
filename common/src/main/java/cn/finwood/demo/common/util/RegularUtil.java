package cn.finwood.demo.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 规则校验工具类
 * created by haoyanbing on 2018/11/9 15:06
 */
public final class RegularUtil {

    // 验证手机号
    public static boolean isMobile(String mobile) {
//        Pattern pattern = Pattern.compile("^((\\+)?86|((\\+)?86)?)^0?(13\\d|14[5,7]|15[0-3,5-9]|17[0,6-8]|18\\d)\\d{8}$");
        Pattern pattern = Pattern.compile("^((\\+)?86|((\\+)?86)?)^0?1\\d{10}$");
        Matcher matcher = pattern.matcher(mobile);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    // 验证浮点数（包含整数）
    public static boolean isDecimal(String input) {
        Pattern pattern = Pattern.compile("^([+]?[1-9]\\d*(\\.\\d+)?|+?0(\\.\\d*[0-9]\\d*)?)$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    // 验证邮箱
    public static boolean isEmail(String email) {
        Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
        Matcher matcher = pattern.matcher(email);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    // 验证url
    public static boolean isUrl(String url) {
        Pattern pattern = Pattern.compile("^((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    // 验证国内电话
    public static boolean isTelphone(String tel) {
        Pattern pattern = Pattern.compile("^(\\d{3,4}[-_－—]{1}\\d{5,8})?[-_－—]?\\d{1,8}?$");
        Matcher matcher = pattern.matcher(tel);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    // 验证正整数
    public static boolean isPureDigital(String string) {
        if (StringUtils.isEmpty(string))
            return false;
        String regEx1 = "^([1-9]*[1-9][0-9]*)$";
        Pattern p = Pattern.compile(regEx1);
        Matcher m = p.matcher(string);
        if (m.matches())
            return true;
        else
            return false;
    }

    // 验证中文
    public static boolean isChinese(String source, int minLength, int maxLength) {
        String format = "";
        if (minLength == maxLength || minLength > maxLength) {
            format = "^[\\u4e00-\\u9fa5]+$";
        } else {
            format = String.format("^[\\u4e00-\\u9fa5]{%d,%d}$", minLength, maxLength);
        }
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(source);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    public static boolean isChinese(String source) {
        return isChinese(source, 0, 0);
    }

    // 验证非负浮点数
    public static boolean isPositiveDecimal(String input) {
        Pattern pattern = Pattern.compile("^\\d+(\\.\\d+)?$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /*
     * ip地址格式验证
     */
    public static boolean isIpAddress(String ipAddress) {
        if (ipAddress.matches("0.0.0.0") || ipAddress.matches("1.1.1.1")) {
            return false;
        }
        String regex = "^(((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))(\\.|,)){1,3}((\\d{1,2})|(1\\d{2})|(2[0-4]\\d)|(25[0-5]))$";
        return ipAddress.matches(regex);
    }

    public static void main(String[] args) {
        String mobile = "18801563888";
        boolean valid = isMobile(mobile);
        System.out.println(valid);
    }

    /**
     * 简单密码验证
     *
     * @param strPassword
     */
    public static boolean isEasyPassword(String strPassword) {
        if (strPassword.length() > 20 || strPassword.length() < 6) {
            return false;
        }
        return true;//!strPassword.matches(".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*");
    }

    /**
     * 验证正整数
     *
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 金额验证
     *
     * @param str
     * @return
     */
    public static boolean isPrice(String str) {
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式  
        Matcher match = pattern.matcher(str);
        if (match.matches() == false) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 数量验证(3位小数)
     *
     * @param str
     * @return
     */
    public static boolean isQuantity(String str) {
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,3})?$"); // 判断小数点后2位的数字的正则表达式  
        Matcher match = pattern.matcher(str);
        if (match.matches() == false) {
            return false;
        } else {
            return true;
        }
    }
}
