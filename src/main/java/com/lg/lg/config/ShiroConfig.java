/*
package com.lg.lg.config;


import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

*/
/**
 * @ClassName ShiroConfig
 * @Author admin
 * @Describe
 *//*

@Configuration
public class ShiroConfig {

    */
/**
     * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
     * @return myShiroRealm
     *//*

    @Bean
    public MyRealm myShiroRealm(){
        MyRealm myShiroRealm = new MyRealm();
        return myShiroRealm;
    }

    */
/**
     * 安全管理器
     * @return securityManager
     *//*

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    */
/**
     * 定义shiroFilter过滤器并注入securityManager
     * 这里的配置其实和xml的配置大致是一样的
     *
     * @param securityManager
     * @return
     *//*

    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        bean.setLoginUrl("/login/toLogin");
        //认证成功
        bean.setSuccessUrl("/login/toWelcome");
        //没有权限
        bean.setUnauthorizedUrl("/login/error403");
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/login/toLogin", "anon");
        filterChainDefinitionMap.put("/login/loginUser", "anon");
         filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/font/**", "anon");
        filterChainDefinitionMap.put("/h-ui/**", "anon");
        filterChainDefinitionMap.put("/hui/admin/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/layui/**", "anon");
        filterChainDefinitionMap.put("/lib/**", "anon");
        filterChainDefinitionMap.put("/login/logout", "logout");
        filterChainDefinitionMap.put("/**","authc");
*/
/*        //另外的页面需要用户进行登录才能访问
        filterChainDefinitionMap.put("/**", "user");*//*

        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

}
*/
