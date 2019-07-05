package com.by.realm;
import com.by.model.User;
import com.by.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ${zw} on 2019/7/4.
 */

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {


        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        获取红户名
        String username = token.getUsername();
//        通过用户名查询
        User user = userService.findUserByUserName(username);

        if (user == null) {
            return null;
        }

        Object principal = user.getName();
        Object credentials =  user.getPassword();
        String realmName = getName();//获取realm名，直接获取父类的名称

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,realmName);
        return info;

    }


}
