package com.ningxia.ensure.config;

import com.ningxia.ensure.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    @Override//定义如何获取用户的角色和权限的逻辑，给shiro做权限判断
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }
        User user = (User) getAvailablePrincipal(principals);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        System.out.println("获取角色信息：" + user.getRoles());
        System.out.println("获取权限信息：" + user.getAuths());
        info.setRoles(user.getRoles());
        info.setStringPermissions(user.getAuths());
        return info;
    }

    @Override//定义如何获取用户信息的业务逻辑，给shiro做登录
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        // Null username is invalid
        if (username == null) {
            throw new AccountException("请输入用户名");
        }
        if (!"123".equals(username)) {
            throw new UnknownAccountException("账户不存在!");
        }
        //查询用户的角色和权限存到SimpleAuthenticationInfo中，这样在其它地方
        //SecurityUtils.getSubject().getPrincipal()就能拿出用户的所有信息，包括角色和权限
        User user = new User();
        user.setU_name(username);
        user.getRoles().add("aa");
        user.getAuths().add("ba");
        return new SimpleAuthenticationInfo(user, "123", getName());
    }
}

