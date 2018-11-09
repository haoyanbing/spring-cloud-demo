package cn.finwood.demo.common.exception;

/**
 * Redis缓存异常
 * created by haoyanbing on 2018/11/9 13:55
 */
public class RedisCacheException extends CommonException {
    public RedisCacheException() {
        super();
    }

    public RedisCacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisCacheException(Throwable cause) {
        super(cause);
    }

    public RedisCacheException(String message) {
        super(message);
    }

    public RedisCacheException(int code) {
        super(code);
    }

    public RedisCacheException(int code, Throwable cause) {
        super(code, cause);
    }

    public RedisCacheException(int code, String messageAppend) {
        super(code, messageAppend);
    }
}
