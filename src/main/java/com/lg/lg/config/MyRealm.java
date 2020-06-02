/*
package com.lg.lg.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lg.lg.entity.LgAuthority;
import com.lg.lg.entity.LgRole;
import com.lg.lg.entity.LgScoredetails;
import com.lg.lg.entity.LgUser;
import com.lg.lg.service.LgAuthorityService;
import com.lg.lg.service.LgRoleService;
import com.lg.lg.service.LgUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


*
 * @author admin
 * @date 2020/5/26 10:41


public class MyRealm extends AuthorizingRealm {

    @Autowired
    private LgUserService userService;
    @Autowired
    private LgRoleService lgRoleService;
    @Autowired
    private LgAuthorityService lgAuthorityService;

    // 权限授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        LgUser userInfo  = (LgUser)principals.getPrimaryPrincipal();
        for(String lgRole : lgRoleService.selectByUserId(userInfo.getId())){
            authorizationInfo.addRole(lgRole);
            for(String lgAuthority : lgAuthorityService.selectByUserId(userInfo.getId())){
                authorizationInfo.addStringPermission(lgAuthority);
            }
        };
        return authorizationInfo;
    }

    //主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        QueryWrapper<LgUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(LgUser::getUserName,username);
        LgUser userInfo =  userService.getOne(queryWrapper);
        if(userInfo == null){
            // 抛出 帐号找不到异常
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), getName());
    }
}
*/
