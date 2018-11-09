package cn.finwood.demo.gateway.feign;

import cn.finwood.demo.model.CommonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * created by haoyanbing on 2018/11/9 13:55
 */
@FeignClient("user-service")
public interface UserService {

    @GetMapping("verifyAuth")
    CommonDto<Object> verifyAuth(@RequestParam(value = "token", required = false) String token, @RequestParam("apiName") String apiName);
}
