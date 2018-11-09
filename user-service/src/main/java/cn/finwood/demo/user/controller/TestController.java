package cn.finwood.demo.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by haoyanbing on 2018/11/7 11:37
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("test")
    public String test() {
        return "hello world";
    }
}
