package com.by.config;

import com.by.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ${zw} on 2019/7/4.
 */

@Configuration
public class ShiroConfiguration {

    /*
    *   <!--securityManager-->
        <bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
        <property name="realm" ref="myRealm"/>
        </bean>
    * */

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){

        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm());

        return  defaultWebSecurityManager;
    }



    /*
*  <!--自定义的realm-->
    <bean id="myRealm" class="com.by.realm.MyRealm">
    </bean>
* */

    @Bean
    public MyRealm myRealm(){

        MyRealm myRealm = new MyRealm();

        myRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myRealm;
    }


//    <!--加密器-->
//            <bean id="credentialsMatcher"
//    class="org.apache.shiro.authc.credential.HashedCredentialsMatcher">
//                <!--使用的加密技术-->
//                <property name="hashAlgorithmName" value="md5" />
//                <!--加密的次数-->
//                <property name="hashIterations" value="9" />
//            </bean>


    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(9);

        return  hashedCredentialsMatcher;

    }



    /*
    * <!-- ShiroFilter
        注意：id必须和web.xml中定义的 <filter-name>shiroFilter</filter-name> 一致
     -->
    <bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
        <property name="securityManager" ref="securityManager"/>
        <!--登录页面-->
        <property name="loginUrl" value="/login.jsp"/>
        <!--登陆后的界面-->
        <property name="successUrl" value="/success.jsp"></property>
        <!-- 没有授权的路径  -->
        <property name="unauthorizedUrl" value="/unauthorized.jsp"/>
        <!-- 拦截规则 -->
        <property name="filterChainDefinitions">
            <value>
                <!--anon:匿名拦截器，即不需要登录即可访问；一般用于静态资源过滤；示例“/static/**=anon”-->
                <!--authc:表示需要认证(登录)才能使用;示例“/**=authc”-->
                <!--所有的请求都会被shiroFilter拦截认证-->

                /login.jsp = anon
                /user/login = anon

                /user/logout = logout
                /** = authc

            </value>
        </property>
    </bean>
    * */

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager());
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        shiroFilterFactoryBean.setSuccessUrl("/success.jsp");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized.jsp");

        Map<String , String> map = new LinkedHashMap<>();
        map.put("/test/list","anon");
        map.put("/login.jsp","anon");
        map.put("/user/user","anon");
        map.put("/user/logout","logout");
        map.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

}
