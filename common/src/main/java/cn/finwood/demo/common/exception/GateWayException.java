package cn.finwood.demo.common.exception;

/**
 * 网关异常
 * created by haoyanbing on 2018/11/8 16:42
 */
public class GateWayException extends CommonException {
    public GateWayException() {
        super();
    }

    public GateWayException(String message, Throwable cause) {
        super(message, cause);
    }

    public GateWayException(Throwable cause) {
        super(cause);
    }

    public GateWayException(String message) {
        super(message);
    }

    public GateWayException(int code) {
        super(code);
    }

    public GateWayException(int code, Throwable cause) {
        super(code, cause);
    }

    public GateWayException(int code, String messageAppend) {
        super(code, messageAppend);
    }
}
