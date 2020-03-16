/*
 * @(#) UserInfo
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2020
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2020-02-26 14:52:53
 */

package com.example.demo.model.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class UserInfo implements UserDetails {
    private String userName;
    private String password;
    private List<UserRoleInfo> userRoles;

    public UserInfo() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRoles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }
    /**
     * 账户是否未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /**
     * 账户是否被未锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /**
     * 帐户密码是否未过期，一般有的密码要求性高的系统会使用到，比较每隔一段时间就要求用户重置密码
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
     * 账户是否可用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
