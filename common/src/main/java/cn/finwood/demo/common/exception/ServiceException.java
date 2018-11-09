package cn.finwood.demo.common.exception;

/**
 * 服务异常
 * created by haoyanbing on 2018/11/9 13:55
 */
public class ServiceException extends CommonException {

    public ServiceException() {
        super();
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(int code) {
        super(code);
    }

    public ServiceException(int code, Throwable cause) {
        super(code, cause);
    }

    public ServiceException(int code, String messageAppend) {
        super(code, messageAppend);
    }
}
