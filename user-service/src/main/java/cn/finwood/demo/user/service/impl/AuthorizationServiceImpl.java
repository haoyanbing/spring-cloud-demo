package cn.finwood.demo.user.service.impl;

import cn.finwood.demo.common.SystemPromptCode;
import cn.finwood.demo.common.util.JwtUtil;
import cn.finwood.demo.common.util.ReturnUtil;
import cn.finwood.demo.common.util.StringUtils;
import cn.finwood.demo.model.CommonDto;
import cn.finwood.demo.user.service.AuthorizationService;
import cn.finwood.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by haoyanbing on 2018/11/9 9:55
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private UserService userService;

    @Override
    public CommonDto<Object> auth(String token, String apiName) {
        Long userId = 0L;
        if (!StringUtils.isEmpty(token)) {
            userId = JwtUtil.getUserId(token);
        }
        // 获取用户可访问的API
        List<String> permissions = userService.listPermissionByUserId(userId);
        if (permissions == null || !permissions.contains(apiName)) {
            return ReturnUtil.Fail(SystemPromptCode.SYSTEM_NO_PERMISSION.getCode());
        }
        return ReturnUtil.Success();
    }
}
