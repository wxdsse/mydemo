/*
 * @(#) ResultCode
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2020
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2020-03-02 14:54:47
 */

package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultCodeEnum {
    SUCCESS(200, "成功！"),
    COMMON_FAIL(9999, "失败!"),
    PARAM_NOT_VALID(1001, "参数无效！"),
    PARAM_IS_BLANK(1002, "参数为空！"),
    PARAM_TYPE_ERROR(1003, "参数类型错误！"),
    PARAM_NOT_COMPLETE(1004, "参数缺失！"),
    USER_NOT_LOGIN(2001, "用户未登录！"),
    LOGIN_SUCCESS(2002,"登录成功！"),
    LOG_OUT_SUCCESS(2003, "已注销！"),
    USER_ACCOUNT_EXPIRED(2004, "账户过期，请联系管理员!"),
    USER_CREDENTIALS_ERROR(2005, "账户名或者密码输入错误!"),
    USER_CREDENTIALS_EXPIRED(2006, "密码过期，请联系管理员!"),
    USER_ACCOUNT_DISABLE(2007, "账户被禁用，请联系管理员!"),
    USER_ACCOUNT_LOCKED(2008, "账户被锁定，请联系管理员!"),
    USER_ACCOUNT_ALREADY_EXIST(2009, "账号已存在！"),
    NO_PERMISSION(4001, "没有访问权限！");
    private Integer code;
    private String msg;
}
