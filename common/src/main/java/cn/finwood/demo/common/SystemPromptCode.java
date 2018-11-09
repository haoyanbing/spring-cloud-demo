package cn.finwood.demo.common;

/**
 * 系统提示code
 * created by haoyanbing on 2018/11/9 15:06
 */
public enum SystemPromptCode {

	SUCCESS(0,""),
	SYSTEM_UNDEFINED(100,"未知错误"),
	SYSTEM_NO_LOGIN(101,"未登录"),
	SYSTEM_NO_PERMISSION(102,"没有权限"),
	SYSTEM_SIGNATURE_INVALID(103,"签名错误"),
	SYSTEM_TIMESTAMP_INVALID(104,"调用时间过期"),
	SYSTEM_NULL_DATA(105,"数据为空"),
	SYSTEM_NULL_PARAMETER(106,"参数为空"),
	SYSTEM_ERROR_PARAMETER(107,"参数错误")

    ;

	//错误码
	private int code;
	//错误信息
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

    private SystemPromptCode() {

    }

	/**
	 * 构造函数
	 * @param code
	 */
	private SystemPromptCode(int code) {
		this.code = code;
	}

	/**
	 * 构造函数
	 * @param code
	 * @param msg
	 */
	private SystemPromptCode(int code, String msg) {
		this.code = code;
		this.message = msg;
	}

}
