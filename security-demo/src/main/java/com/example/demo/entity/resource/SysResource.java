/*
 * @(#) TSysResource
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2020
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2020-03-03 10:37:56
 */

package com.example.demo.entity.resource;

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
 * 角色表
 * </p>
 *
 * @author wangxd
 * @since 2020-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_SYS_RESOURCE")
public class SysResource implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 资源id
     */
      @TableId("RESOURCE_ID")
    private String resourceId;

    /**
     * 资源名称
     */
    @TableField("RESOURCE_NAME")
    private String resourceName;

    /**
     * 资源路径
     */
    @TableField("RESOURCE_URL")
    private String resourceUrl;

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
