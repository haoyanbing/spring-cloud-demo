package cn.finwood.demo.common.util;

import cn.finwood.demo.common.SystemEnum;
import cn.finwood.demo.common.SystemPromptCode;
import cn.finwood.demo.model.EnumDto;

import java.util.*;

/**
 * 枚举工具类
 * created by haoyanbing on 2018/11/9 15:06
 */
public final class EnumUtil {
    private static SystemEnum[] enums;
    private static SystemPromptCode[] enumErrors;

    static {
        if (null == enums) {
            synchronized (EnumUtil.class) {
                if (null == enums) {
                    enums = SystemEnum.values();
                }
            }
        }
    }

    static {
        if (null == enumErrors) {
            synchronized (EnumUtil.class) {
                if (null == enumErrors) {
                    enumErrors = SystemPromptCode.values();
                }
            }
        }
    }

    /**
     * 取对应组的全部枚举
     *
     * @param group
     * @return
     */
    public static List<SystemEnum> getEnumByGroup(int group) {

        List<SystemEnum> lstEnums = new ArrayList<SystemEnum>();
        for (SystemEnum item : enums) {
            if (item.getGroup() == group) {
                lstEnums.add(item);
            }
        }
        return lstEnums;
    }

    /**
     * 取枚举键值对集合
     *
     * @param group
     * @return
     */

    public static List<EnumDto> enumToLstKeyValues(int group, int... excludeItems) {
        List<EnumDto> lstEnum = new LinkedList<EnumDto>();
        for (SystemEnum item : enums) {
            EnumDto temp = null;
            if (item.getGroup() == group) {

                boolean isExists = false;
                if (excludeItems != null && excludeItems.length > 0) {
                    for (int itemValue : excludeItems) {
                        if (itemValue == item.getValue()) {
                            isExists = true;
                            break;
                        }
                    }
                }

                if (isExists) {
                    continue;
                }
                temp = new EnumDto();
                temp.setKey(item.getText());
                temp.setValue(item.getValue());
                lstEnum.add(temp);
            }
        }
        return lstEnum;
    }


    /**
     * 获取枚举值集合
     *
     * @param group
     * @return
     */
    public static List<Integer> getEnumValusByGroup(int group) {
        List<Integer> lstValues = new ArrayList<Integer>();
        for (SystemEnum item : enums) {
            if (item.getGroup() == group) {
                lstValues.add(item.getValue());
            }
        }
        return lstValues;
    }

    /**
     * 取对应组的全部枚举
     *
     * @param group
     * @return
     */
    public static List<SystemEnum> getEnumByGroup(int group, List<Integer> listValue) {

        return getEnumByGroup(group, listValue, true);
    }

    /**
     * 取对应组的全部枚举
     *
     * @param group
     * @param listValue
     * @param isContains 包含 true 排除 false
     * @return
     */
    public static List<SystemEnum> getEnumByGroup(int group, List<Integer> listValue, boolean isContains) {

        List<SystemEnum> lstEnums = new ArrayList<SystemEnum>();
        for (SystemEnum item : enums) {
            if (item.getGroup() == group && ((isContains && listValue.contains(item.getValue()) || (!isContains && !listValue.contains(item.getValue()))))) {
                lstEnums.add(item);
            }
        }
        return lstEnums;
    }


    /**
     * 排序
     */
    static class EnumByValue implements Comparator {
        public int compare(Object o1, Object o2) {
            SystemEnum s1 = (SystemEnum) o1;
            SystemEnum s2 = (SystemEnum) o2;

            if (s1.getValue() < s2.getValue()) {
                return 1;
            } else {
                return -1;
            }
        }
    }


    /**
     * 取枚举值的文本
     *
     * @param group
     * @param value
     * @return
     */
    public static String getBitEnumText(Integer group, Integer value) {

        if (value == null) {
            return "";
        }

        //找出分组的枚举
        List<SystemEnum> sysEnums = new ArrayList<>();
        for (SystemEnum item : enums) {
            if (item.getGroup() == group) {
                sysEnums.add(item);
            }
        }

        //倒叙排序
        Collections.sort(sysEnums, new EnumByValue());
        //逗号
        StringBuilder enumText = new StringBuilder();
        for (SystemEnum item : sysEnums) {
            if (value >= item.getValue()) {
                enumText.append(item.getText() + ",");
                value -= item.getValue();
            }
        }
        //去除最后一个逗号
        if (enumText.length() > 0) {
            return enumText.substring(0, enumText.length() - 1);
        }
        return "";
    }

    /**
     * 取枚举值的文本
     *
     * @param group
     * @param value
     * @return
     */
    public static List<Integer> getBitEnumValues(Integer group, Integer value) {

        List<Integer> list = new ArrayList<>();
        if (value == null) {
            return list;
        }

        //找出分组的枚举
        List<SystemEnum> sysEnums = new ArrayList<>();
        for (SystemEnum item : enums) {
            if (item.getGroup() == group) {
                sysEnums.add(item);
            }
        }

        //倒叙排序
        Collections.sort(sysEnums, new EnumByValue());
        for (SystemEnum item : sysEnums) {
            if (value >= item.getValue()) {
                list.add(item.getValue());
                value -= item.getValue();
            }
        }
        return list;
    }


    /**
     *
     */
    public static String getEnumText(int group, int value) {
        for (SystemEnum item : enums) {
            if (item.getGroup() == group) {
                if (value == item.getValue()) {
                    return item.getText();
                }
            }
        }
        return "";
    }

    /**
     * 根据分组ID、枚举值判断是否有效值
     *
     * @param group
     * @param val
     * @return
     */
    public static boolean isEnumValid(int group, Integer val) {
        if(null==val||val==0) {
            return false;
        }
        for (SystemEnum item : enums) {
            if (item.getGroup() == group) {
                if (val == item.getValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 根据分组ID、枚举文本获取枚举值
     *
     * @param group
     * @param text
     * @return
     */
    public static int getEnumValueByText(int group, String text) {
        for (SystemEnum item : enums) {
            if (group == item.getGroup()) {
                if (text.equals(item.getText())) {
                    return item.getValue();
                }
            }
        }
        return -1;
    }


    /**
     * 错误代码文本
     *
     * @param key
     * @return
     */
    public static String getErrorText(int key) {
        for (SystemPromptCode item : enumErrors) {
            if (key == item.getCode()) {
                return item.getMessage();
            }
        }
        return "";
    }


}
