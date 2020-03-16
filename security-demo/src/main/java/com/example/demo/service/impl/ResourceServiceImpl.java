/*
 * @(#) ResourceServiceImpl
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2020
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2020-03-03 15:32:15
 */

package com.example.demo.service.impl;

import com.example.demo.dao.resource.SysResourceMapper;
import com.example.demo.model.resource.ResourceView;
import com.example.demo.service.IResourceService;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service
public class ResourceServiceImpl implements IResourceService {
    @Resource
    private SysResourceMapper resourceMapper;

    @Override
    public List<ResourceView> getResource() {
        return resourceMapper.getResource();
    }
}
