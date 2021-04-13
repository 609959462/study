package com.ppf.frame.config;


import com.ppf.frame.pojo.User;
import com.ppf.frame.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class Realm extends AuthorizingRealm {

   @Autowired
   private IUserService userService;

    /**
     * 权限配置
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("MyShiroRealm->doGetAuthorizationInfo");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//       User userInfo = (User) principals.getPrimaryPrincipal();
//        for (SysRole role : userInfo.getRoles()) {
//            authorizationInfo.addRole(role.getRole());
//            for (SysPermission p : role.getPermissions()) {
//                authorizationInfo.addStringPermission(p.getPermission());
//            }
//        }
        return authorizationInfo;
    }

    /**
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {

        //用户名
       String userName = (String) token.getPrincipal();

        User userInfo = userService.SelectByUsername(userName);
        //不存在 可以抛个异常
        if (userInfo == null) {
            throw new UnknownAccountException();
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userInfo,userInfo.getUserPassword(),getName());
            return simpleAuthenticationInfo;
    }


}