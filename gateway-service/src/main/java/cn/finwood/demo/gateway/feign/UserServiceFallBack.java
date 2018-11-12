package cn.finwood.demo.gateway.feign;

import cn.finwood.demo.common.util.ReturnUtil;
import cn.finwood.demo.model.CommonDto;
import org.springframework.stereotype.Component;

/**
 * created by haoyanbing on 2018/11/12 10:10
 */
@Component
public class UserServiceFallBack implements UserService {

    @Override
    public CommonDto<Object> verifyAuth(String token, String apiName) {
        return ReturnUtil.Fail("权限验证超时");
    }
}
