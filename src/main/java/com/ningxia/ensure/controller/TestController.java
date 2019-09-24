package com.ningxia.ensure.controller;

import com.ningxia.ensure.service.TestService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TestController {
    static Logger log = (Logger) LogManager.getLogger(TestController.class.getName());
    @Autowired
    TestService testService;

    @RequiresPermissions(value = {"a", "b"}, logical = Logical.OR)
    @RequestMapping("/hello")
    public Object hello() {
        List<Map<String, Object>> maps = null;
        try {
            maps = testService.basicInfo(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maps;
    }

    @RequestMapping("/login")
    public Object login(String userName, String Password) throws Exception {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, Password);
        token.setRememberMe(true);// 默认不记住密码
        try {
            currentUser.login(token); //登录
            return "登录成功";
        } catch (UnknownAccountException e) {
            log.info("==========用户名不存在=======");
            return "用户名不存在";
        } catch (DisabledAccountException e) {
            log.info("==========您的账户已经被冻结=======");
            return "您的账户已经被冻结";
        } catch (IncorrectCredentialsException e) {
            log.info("==========密码错误=======");
            return "密码错误";
        } catch (ExcessiveAttemptsException e) {
            log.info("==========您错误的次数太多了吧,封你半小时=======");
            return "您错误的次数太多了吧,封你半小时";
        } catch (RuntimeException e) {
            log.info("==========运行异常=======");
            return "运行异常";
        }
    }

    @RequestMapping("/logout")
    public String logOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "index";
    }

    @RequestMapping("/unauthorizedurl")
    public String unauthorizedurl() {
        return "未授权";
    }
}
