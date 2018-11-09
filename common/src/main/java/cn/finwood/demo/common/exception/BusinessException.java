package cn.finwood.demo.common.exception;

/**
 * 业务异常
 * created by haoyanbing on 2018/11/9 13:55
 */
public class BusinessException extends  CommonException {

	public BusinessException() {
		super();
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(int code) {
		super(code);
	}

	public BusinessException(int code, Throwable cause) {
		super(code, cause);
	}

	public BusinessException(int code, String messageAppend) {
		super(code, messageAppend);
	}
}
