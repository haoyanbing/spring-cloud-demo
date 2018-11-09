package cn.finwood.demo.user.service.impl;

import cn.finwood.demo.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by haoyanbing on 2018/11/9 15:10
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<String> listPermissionByUserId(Long userId) {
        return null;
    }
}
