/*
 * @(#) JsonResult
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2020
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2020-03-02 14:58:40
 */

package com.example.demo.common;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

import lombok.Data;


@Data
public class JsonResult implements Serializable {
    private Integer code;
    private String msg;
    private Object data;

    public JsonResult(ResultCodeEnum codeEnum, Object data) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
        this.data = data;
    }

    public static String getJsonResult(ResultCodeEnum codeEnum) {
        return JSON.toJSONString(new JsonResult(codeEnum, null));
    }

    public static String getJsonResult(ResultCodeEnum codeEnum, Object data) {
        return JSON.toJSONString(new JsonResult(codeEnum, data));
    }
}