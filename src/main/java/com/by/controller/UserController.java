package com.by.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.login.LoginContext;

/**
 * Created by ${zw} on 2019/7/4.
 */

@Controller
@RequestMapping("user")
@Slf4j
public class UserController {


    @ResponseBody
    @RequestMapping("user")
    public String user(String userName, String userPassword){

        //获取 subject
        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
            //将前台用户名密码，添加在 UsernamePasswordToken
            UsernamePasswordToken token = new UsernamePasswordToken(userName, userPassword);
            //记住我
            token.setRememberMe(true);
            try {
                //登录方法 ，执行 doGetAuthenticationInfo 方法
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
//                System.out.println("用户名不存在");
                log.info("用户名不存在");
            } catch (IncorrectCredentialsException ice) {
//                System.out.println("密码错误");
                log.info("密码错误");
            }
        }
        return "redirect:/success.jsp";

    }

}
