/*
 * @(#) UserRoleInfo
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2020
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2020-02-26 14:52:56
 */

package com.example.demo.model.security;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserRoleInfo implements GrantedAuthority {
    private String roleId;
    private String roleName;
    private String roleNameEn;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
