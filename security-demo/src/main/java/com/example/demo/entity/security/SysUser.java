/*
 * @(#) SysUser
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2020
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2020-02-26 15:16:15
 */

package com.example.demo.entity.security;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author wangxd
 * @since 2020-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_SYS_USER")
public class SysUser implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户ID
     */
    @TableId("USER_ID")
    private String userId;

    /**
     * 用户名
     */
    @TableField("USER_NAME")
    private String userName;

    /**
     * 用户姓名
     */
    @TableField("NICK_NAME")
    private String nickName;

    /**
     * 密码
     */
    @TableField("PASSWORD")
    private String password;

    /**
     * 手机号
     */
    @TableField("MOBILE")
    private String mobile;

    /**
     * 邮箱
     */
    @TableField("EMAIL")
    private String email;

    @TableField("DEPT_CODE")
    private String deptCode;

    /**
     * 创建人
     */
    @TableField("CREATED_USER_ID")
    private String createdUserId;

    /**
     * 创建时间
     */
    @TableField("CREATED_TIME")
    private LocalDateTime createdTime;

    /**
     * 更新人
     */
    @TableField("UPDATED_USER_ID")
    private String updatedUserId;

    /**
     * 更新时间
     */
    @TableField("UPDATED_TIME")
    private LocalDateTime updatedTime;

    /**
     * 版本
     */
    @TableField("VERSION")
    private Integer version;


}
