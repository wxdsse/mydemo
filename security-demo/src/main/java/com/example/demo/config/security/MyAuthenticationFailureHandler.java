/*
 * @(#) MyAuthenticationFailureHandler
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2020
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2020-02-26 16:01:10
 */

package com.example.demo.config.security;

import com.example.demo.common.JsonResult;
import com.example.demo.common.ResultCodeEnum;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        String msg;
        if (exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException) {
            msg = JsonResult.getJsonResult(ResultCodeEnum.USER_CREDENTIALS_ERROR);
        } else if (exception instanceof LockedException) {
            msg = JsonResult.getJsonResult(ResultCodeEnum.USER_ACCOUNT_LOCKED);
        } else if (exception instanceof CredentialsExpiredException) {
            msg = JsonResult.getJsonResult(ResultCodeEnum.USER_CREDENTIALS_EXPIRED);
        } else if (exception instanceof AccountExpiredException) {
            msg = JsonResult.getJsonResult(ResultCodeEnum.USER_ACCOUNT_EXPIRED);
        } else if (exception instanceof DisabledException) {
            msg = JsonResult.getJsonResult(ResultCodeEnum.USER_ACCOUNT_DISABLE);
        }
        else {
            msg = JsonResult.getJsonResult(ResultCodeEnum.COMMON_FAIL);
        }
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.write(msg);
    }
}

