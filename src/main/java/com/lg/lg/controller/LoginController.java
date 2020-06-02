package com.lg.lg.controller;

import com.lg.lg.config.AjaxResult;
import com.lg.lg.entity.LgAuthority;
import com.lg.lg.entity.LgScoredetails;
import com.lg.lg.entity.UserPassword;
import com.lg.lg.service.LgAuthorityService;
import com.lg.lg.service.LgUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.lg.lg.config.BaseController;
import com.lg.lg.entity.LgUser;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author admin
 * @date 2020/5/14 14:58
 */
@Controller
@RequestMapping("/login")
@SessionAttributes({"user","lgAuthorities"})
public class LoginController extends BaseController {

    @Autowired
    private LgUserService lgUserService;
    @Autowired
    private LgAuthorityService lgAuthorityService;

    /**
     * 跳转登录界面
     * @return
     */
    @GetMapping("toLogin")
    public String toLogin(){
        return "login/login";
    }

    /**
     * 登录
     * @param user
     * @param model
     * @return
     */
    @RequestMapping ("/loginUser")
    @ResponseBody
    public AjaxResult loginUser(LgUser user, Model model, HttpSession session){
        System.out.println("姓名："+user.getUserName()+"密码："+user.getPassword());

        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            return toAjax(0);
        }

        LgUser users = lgUserService.selectbyNameAndPassword(user.getUserName(),user.getPassword());

        if (users != null) {
            LgAuthority lgAuthorities=lgAuthorityService.selectByUserId(users.getId());
            session.setAttribute("user",users);
            session.setAttribute("lgAuthorities",lgAuthorities);
            model.addAttribute("user", users);

            return toAjax(1);
        } else {
            return toAjax(0);
        }

    }


    /**
     * 退出登录
     * @return
     */
    @RequestMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
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

    @GetMapping("/index")
    public String error404(){
        return "login/index";
    }

    @GetMapping("/error403")
    public String error403(){
        return "error/403";
    }

    /**
     * 修改密码界面
     * @return
     */
    @GetMapping("/toUpdatePassword")
    public String toUpdatePassword(){
        return "user/updatePassword";
    }

    /**
     * 修改用户密码
     * @param userPassword
     * @param session
     * @return
     */
    @PostMapping("updateUserPassword")
    @ResponseBody
    public Integer updateUserPassword(UserPassword userPassword, HttpSession session){
        LgUser user=(LgUser)session.getAttribute("user");
       if(user.getPassword().equals(userPassword.getOldPass())){

           if(userPassword.getNewPass().equals(userPassword.getComfirmPass())){
               user.setPassword(userPassword.getNewPass());
               lgUserService.saveOrUpdate(user);
               return 2;
           }else{
               return 1;
           }
       }else{
           return 0;
       }
    }

}
