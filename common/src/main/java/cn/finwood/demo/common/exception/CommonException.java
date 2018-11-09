package cn.finwood.demo.common.exception;

import cn.finwood.demo.common.SystemPromptCode;
import cn.finwood.demo.common.util.EnumUtil;

/**
 * 自定义公共异常
 * created by haoyanbing on 2018/11/9 13:55
 */
public class CommonException extends RuntimeException {
    /**
     * 错误代码
     */
    private int code;
    /**
     * 错误信息
     */
    private String message;

    public CommonException() {
        super();
        this.code = SystemPromptCode.SYSTEM_UNDEFINED.getCode();
    }

    public CommonException(String message, Throwable cause) {
        super(cause);
        this.message = message;
        this.code = SystemPromptCode.SYSTEM_UNDEFINED.getCode();
    }

    public CommonException(Throwable cause) {
        super(cause);
        this.code = SystemPromptCode.SYSTEM_UNDEFINED.getCode();
    }

    public CommonException(String message) {
        super(message);
        this.message = message;
        this.code = SystemPromptCode.SYSTEM_UNDEFINED.getCode();
    }

    public CommonException(int code) {
        super(EnumUtil.getErrorText(code));
        message = super.getMessage();
        this.code = code;
    }

    public CommonException(int code, Throwable cause) {
        super(cause);
        message = EnumUtil.getErrorText(code);
        this.code = code;
    }

    public CommonException(int code, String messageAppend) {
        super();
        message = EnumUtil.getErrorText(code) + " " + messageAppend;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 取城要写日志的信息
     *
     * @return
     */
//    public String getErrorLog() {
//        String msg = message + "\r\n" + ExceptionUtils.getStackTrace(this);
//        return msg;
//    }

}
