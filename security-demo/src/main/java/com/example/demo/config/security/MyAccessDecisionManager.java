/*
 * @(#) MyAccessDecisionManager
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2020
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2020-02-19 10:02:47
 */

package com.example.demo.config.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyAccessDecisionManager implements AccessDecisionManager {

    /**
     * 进行权限判断
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes)
        throws AccessDeniedException, InsufficientAuthenticationException {
        if (CollectionUtils.isEmpty(configAttributes)) {
            return;
        }
        //authentication.getAuthorities()的角色id是在userDetail的时候塞进去的
        List<String> roleIds = configAttributes.stream()
            .map(ConfigAttribute::getAttribute)
            .collect(Collectors.toList());
        for (GrantedAuthority ga : authentication.getAuthorities()) {
            if (roleIds.contains(ga.getAuthority())) {
                return;
            }
        }
        throw new AccessDeniedException("没有访问权限");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
