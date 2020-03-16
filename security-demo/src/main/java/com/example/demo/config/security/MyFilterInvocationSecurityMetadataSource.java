/*
 * @(#) MyFilterInvocationSecurityMetadataSource
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2020
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2020-02-19 09:40:01
 */

package com.example.demo.config.security;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import com.example.demo.dao.resource.SysResourceMapper;
import com.example.demo.model.resource.ResourceView;
import com.example.demo.model.security.UserRoleInfo;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 在实现类中加载资源权限，并在filterSecurityInterceptor中注入本类。
 */
@Service
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static Map<String, Collection<ConfigAttribute>> urlPermMap = null;

    @Resource
    private SysResourceMapper resourceMapper;

    @PostConstruct
    public void loadResourceDefine() {
        List<ResourceView> resourceViews = resourceMapper.getResource();
        Multimap<String, ConfigAttribute> multimap = ArrayListMultimap.create();
        resourceViews.forEach(e ->
            multimap.put(e.getResourceUrl(), (ConfigAttribute) () -> e.getRoleName())
        );
        urlPermMap = multimap.asMap();
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) o;
        String url = fi.getRequestUrl();
        //接下来就是通过url获取角色信息(Set)，角色信息要继承ConfigAttribute接口，接口getAttribute返回角色code
        return urlPermMap.get(url);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
