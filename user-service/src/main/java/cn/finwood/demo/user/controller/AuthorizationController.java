package cn.finwood.demo.user.controller;

import cn.finwood.demo.model.CommonDto;
import cn.finwood.demo.user.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 授权Controller
 * created by haoyanbing on 2018/11/9 9:47
 */
@RestController
public class AuthorizationController {
    @Autowired
    private AuthorizationService authorizationService;

    @GetMapping("verifyAuth")
    public CommonDto<Object> verifyAuth(@RequestParam(value = "token", required = false) String token, @RequestParam("apiName") String apiName) {
        return authorizationService.auth(token, apiName);
    }
}
