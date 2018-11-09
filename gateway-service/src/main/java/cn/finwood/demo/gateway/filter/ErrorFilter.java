package cn.finwood.demo.gateway.filter;

import cn.finwood.demo.common.exception.CommonException;
import cn.finwood.demo.common.exception.GateWayException;
import cn.finwood.demo.common.util.ReturnUtil;
import cn.finwood.demo.model.CommonDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.sun.org.apache.regexp.internal.REUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * 网关异常过滤器
 * created by haoyanbing on 2018/11/8 10:27
 */
@Component
public class ErrorFilter extends ZuulFilter {
    private final static Logger logger = LoggerFactory.getLogger(ErrorFilter.class);

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        HttpServletResponse response = requestContext.getResponse();
        Throwable throwable = requestContext.getThrowable();
        if (throwable != null) {
            Throwable exception = throwable.getCause();
            CommonDto<String> result;
            if (exception instanceof CommonException) {
                CommonException commonException = (CommonException) exception;
                result = ReturnUtil.Fail(commonException.getCode(), commonException.getMessage());
            } else {
                result = ReturnUtil.Fail(exception.getMessage());
            }
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            ObjectMapper mapper = new ObjectMapper();
            String json = "";
            try {
                json = mapper.writeValueAsString(result);
                response.getWriter().write(json);
            } catch (Exception e) {
                logger.error("返回错误json数据异常");
            }
        }
        return null;
    }
}
