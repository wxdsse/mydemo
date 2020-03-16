/*
 * @(#) MyAuthenticationSuccessHandler
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2020
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2020-03-02 10:02:27
 */

package com.example.demo.config.security;

import com.example.demo.common.JsonResult;
import com.example.demo.common.ResultCodeEnum;
import com.example.demo.config.security.jwt.JwtServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtServiceImpl jwtUtils;

    @Value("${jwt.startUp}")
    private boolean startUp;

    @Value("${jwt.header}")
    private String header;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        RequestCache cache = new HttpSessionRequestCache();
        SavedRequest savedRequest = cache.getRequest(request, response);
        if (Objects.isNull(savedRequest)) {
            if (startUp) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                String token = jwtUtils.generateToken(userDetails);
                jwtUtils.getUsernameFromToken(token);
                response.setHeader(header, token);
            }
            response.getWriter().write(JsonResult.getJsonResult(ResultCodeEnum.SUCCESS));
        } else {
            String url = savedRequest.getRedirectUrl();
            response.sendRedirect(url);
        }
    }


}
