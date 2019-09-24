package com.ningxia.ensure.config;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    @Bean
    MyRealm myRealm() {
        return new MyRealm();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm());
        return manager;
    }


    /**
     * 这里统一做鉴权，即判断哪些请求路径需要用户登录，哪些请求路径不需要用户登录。
     * 这里只做鉴权，不做权限控制，因为权限用注解来做。
     * 配置缩写                            对应的过滤器                                    功能
     * anon                          AnonymousFilter                            指定url可以匿名访问
     * authc                         FormAuthenticationFilter               指定url需要form表单登录，默认会从请求中获取username、password,rememberMe等参数并尝试登录，如果登录不了就会跳转到loginUrl配置的路径。我们也可以用这个过滤器做默认的登录逻辑，但是一般都是我们自己在控制器写登录逻辑的，自己写的话出错返回的信息都可以定制嘛。
     * authcBasic                 BasicHttpAuthenticationFilter        指定url需要basic登录
     * logout                        LogoutFilter                                   登出过滤器，配置指定url就可以实现退出功能，非常方便
     * noSessionCreation    NoSessionCreationFilter                 禁止创建会话
     * perms                        PermissionsAuthorizationFilter      需要指定权限才能访问
     * port                            PortFilter                                        需要指定端口才能访问
     * rest                            HttpMethodPermissionFilter          将http请求方法转化成相应的动词来构造一个权限字符串，这个感觉意义不大，有兴趣自己看源码的注释
     * roles                            RolesAuthorizationFilter               需要指定角色才能访问
     * ssl                                SslFilter                                        需要https请求才能访问
     * user                            UserFilter                                       需要已登录或“记住我”的用户才能访问
     * <p>
     * 注解                                        功能
     *
     * @RequiresGuest 只有游客可以访问
     * @RequiresAuthentication 需要登录才能访问
     * @RequiresUser 已登录的用户或“记住我”的用户能访问
     * @RequiresRoles 已登录的用户需具有指定的角色才能访问
     * @RequiresPermissions 已登录的用户需具有指定的权限才能访问
     */
    @Bean
    ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        definition.addPathDefinition("/login", "anon");//哪些请求可以匿名访问
        definition.addPathDefinition("/**", "authc");//除了以上的请求外，其它请求都需要登录
        return definition;
    }
}

