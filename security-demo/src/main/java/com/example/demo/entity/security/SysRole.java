/*
 * @(#) TSysRole
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2020
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2020-02-26 15:25:05
 */

package com.example.demo.entity.security;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author wangxd
 * @since 2020-02-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_SYS_ROLE")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId("ROLE_ID")
    private String roleId;

    /**
     * 角色名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 角色中文名
     */
    @TableField("NAME_CN")
    private String nameCn;

    /**
     * 角色描述
     */
    @TableField("DESCRIPTION")
    private String description;

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
