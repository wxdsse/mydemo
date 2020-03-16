/*
 * @(#) WebSecurityConfig
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2020
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2020-02-19 10:33:46
 */

package com.example.demo.config.security;

import com.example.demo.common.JsonResult;
import com.example.demo.common.ResultCodeEnum;
import com.example.demo.config.security.jwt.JwtAuthenticationTokenFilter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.startUp}")
    private boolean startUp;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private MyAccessDecisionManager myAccessDecisionManager;

    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Resource
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Resource
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public MyFilterSecurityInterceptor myFilterSecurityInterceptor() {
        MyFilterSecurityInterceptor myFilterSecurityInterceptor = new MyFilterSecurityInterceptor();
        myFilterSecurityInterceptor.setMyAccessDecisionManager(myAccessDecisionManager);
        return myFilterSecurityInterceptor;
    }

    /*
     * 认证管理器
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /*
     * 身份验证配置，用于注入自定义身份验证Bean和密码校验规则
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            //配置 UserDetailsService 实现类，实现自定义登录校验
            .userDetailsService(userDetailsService)
            //配置密码加密规则
            //DelegatingPasswordEncoder 遇到新密码 DelegatingPasswordEncoder
            // 会委托给 BCryptPasswordEncoder(encodingId为bcryp*) 进行加密。
            // 同时，对历史上使用 ldap、MD4、MD5 等等加密算法的密码认证保持兼容（如果数据库里的密码使用的是MD5算法,
            // 那使用matches方法认证仍可以通过,但新密码会使bcrypt进行储存
            .passwordEncoder(new BCryptPasswordEncoder());

    }


    /**
     * 无需权限校验直接放行的路径
     */
    private final String[] PATH_PASS = {
        "/user/logout", "/user/login"
    };

    /**
     * Request层面的配置，对应XML Configuration中的<http>元素
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers(PATH_PASS).permitAll()
            .anyRequest().authenticated()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(myAuthenticationEntryPoint) //身份认证失败的处理
            .accessDeniedHandler((httpServletRequest, httpServletResponse, e) -> {//无权限的处理
                httpServletResponse.setContentType("application/json;charset=utf-8");
                httpServletResponse.getWriter().write(JsonResult.getJsonResult(ResultCodeEnum.NO_PERMISSION));
            })
            .and()
            .formLogin()
            .successHandler(myAuthenticationSuccessHandler)
            .failureHandler(myAuthenticationFailureHandler)
            .and()
            .logout()
            .logoutSuccessHandler(myLogoutSuccessHandler)
            .and().csrf().disable() //前后端分离必须加，否则导致登录路径无效
            .httpBasic();
        // 将自定义的过滤器配置在FilterSecurityInterceptor之前
        http.addFilterBefore(myFilterSecurityInterceptor(), FilterSecurityInterceptor.class);
        if (startUp) {
            //基于token，所以不需要session
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
            // 禁用缓存
            http.headers().cacheControl();
        }
    }

    /**
     * Web层面的配置，一般用来配置无需权限校验的路径，也可以在HttpSecurity中配置，但是在web.ignoring()中配置效率更高。
     * web.ignoring()是一个忽略的过滤器，而HttpSecurity中定义了一个过滤器链，即使permitAll()放行还是会走所有的过滤器，
     * 直到最后一个过滤器FilterSecurityInterceptor认定是可以放行的，才能访问。
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/favor.ioc");
    }
}
