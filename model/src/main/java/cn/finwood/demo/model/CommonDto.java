package cn.finwood.demo.model;

/**
 * 返回结果公共DTO
 * @param <T>
 * created by haoyanbing on 2018/11/9 16:04
 */
public class CommonDto<T> {
	
	private  int  code;
	
	private String   message;
	
	public CommonDto()
	{
		 code=0;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the result
	 */
	public T getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(T result) {
		this.result = result;
	}

	private  T result;
	
}
