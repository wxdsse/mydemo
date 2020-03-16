package com.example.clienta.controller;

import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 获取页面的接口
 * @author zfh
 * @version 1.0
 * @since 2019/6/14 13:58
 */
@Controller
public class IndexAction {

    @GetMapping(value = "")
    public String index() {
        System.out.println("进入ClientA首页");
        return "index.html";
    }

    @GetMapping(value = "securedPage")
    public String home() {
        System.out.println("进入ClientA securedPage");
        return "securedPage.html";
    }
}
