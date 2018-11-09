package cn.finwood.demo.gateway.filter;

import cn.finwood.demo.common.SystemConst;
import cn.finwood.demo.common.exception.GateWayException;
import cn.finwood.demo.model.CommonDto;
import cn.finwood.demo.gateway.feign.UserService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限过滤器
 * created by haoyanbing on 2018/11/8 15:26
 */
@Component
public class AuthorizationFilter extends ZuulFilter {

    @Autowired
    private UserService userService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String token = request.getHeader(SystemConst.REQUEST_HEADER_TOKEN_NAME);
        String apiName = request.getRequestURI();

        // 调用user-service进行权限验证
        CommonDto<Object> result = userService.verifyAuth(token, apiName);
        if (result.getCode() != 0) {
            throw new GateWayException(result.getCode(), result.getMessage());
        }
        return null;
    }
}
