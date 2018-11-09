package cn.finwood.demo.user.service;

import java.util.List;

/**
 * created by haoyanbing on 2018/11/9 14:41
 */
public interface UserService {
    /**
     * 根据用户ID查询用户可访问的API
     * 1.userId=0时查询不需要登陆即可访问的权限
     * 2.userId>0时查询该用户的权限和不需要登陆即可访问的权限
     * 3.对于用户的权限需要进行缓存到Redis，避免每次从数据库查询(key=user:permission:userId)
     * @param userId
     * @return
     */
    List<String> listPermissionByUserId(Long userId);

}
