package cn.finwood.demo.user.service;

import cn.finwood.demo.model.CommonDto;

/**
 * created by haoyanbing on 2018/11/9 9:54
 */
public interface AuthorizationService {
    /**
     * 验证权限
     * @param token
     * @return
     */
    CommonDto<Object> auth(String token, String apiName);
}
