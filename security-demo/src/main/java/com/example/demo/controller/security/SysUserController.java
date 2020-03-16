/*
 * @(#) SysUserController
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2020
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2020-02-26 15:23:46
 */

package com.example.demo.controller.security;


import com.example.demo.model.security.UserInfo;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wangxd
 * @since 2020-02-26
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @GetMapping("/getUser")
    public UserInfo getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Objects.nonNull(principal)) {
            UserDetails userDetails = (UserDetails) principal;
            return new UserInfo().setUserName(userDetails.getUsername());
        }
        // FilterChainProxy

        return null;
    }

}

