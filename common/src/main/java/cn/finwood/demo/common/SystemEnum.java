package cn.finwood.demo.common;

/**
 * 系统枚举
 * created by haoyanbing 2018/11/9 15:57
 */
public enum SystemEnum {

    ;

    //值
    private int value;
    //显示值
    private String text;
    //分组
    private int group;

    private SystemEnum() {
    }

    private SystemEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    private SystemEnum(String text, int value, int group) {
        this.text = text;
        this.value = value;
        this.group = group;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
