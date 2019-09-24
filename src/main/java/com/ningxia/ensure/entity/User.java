package com.ningxia.ensure.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User implements Serializable {
    private String u_id;       // 用户id
    private String u_name;   // 登录名，不可改
    private String u_nick;    // 用户昵称，可改
    private String u_pwd;     // 密码
    private Set<String> roles = new HashSet<>();    //用户所有角色值，用于shiro做角色权限的判断
    private Set<String> auths = new HashSet<>();    //用户所有权限值，用于shiro做资源权限的判断

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_nick() {
        return u_nick;
    }

    public void setU_nick(String u_nick) {
        this.u_nick = u_nick;
    }

    public String getU_pwd() {
        return u_pwd;
    }

    public void setU_pwd(String u_pwd) {
        this.u_pwd = u_pwd;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getAuths() {
        return auths;
    }

    public void setAuths(Set<String> auths) {
        this.auths = auths;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(u_id, user.u_id) &&
                Objects.equals(u_name, user.u_name) &&
                Objects.equals(u_nick, user.u_nick) &&
                Objects.equals(u_pwd, user.u_pwd) &&
                Objects.equals(roles, user.roles) &&
                Objects.equals(auths, user.auths);
    }

    @Override
    public int hashCode() {
        return Objects.hash(u_id, u_name, u_nick, u_pwd, roles, auths);
    }
}
