package com.lg.lg.controller;

import com.lg.lg.config.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author admin
 * @date 2020/5/14 14:58
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    /**
     * 跳转登录界面
     * @return
     */
    @GetMapping("toLogin")
    public String toLogin(){
        return "login/login";
    }

    /**
     * 首页
     * @return
     */
    @GetMapping("toWelcome")
    public String toWelcome(){
        return "login/welcome";
    }
}
