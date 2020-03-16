/*
 * @(#) ResourceController
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2020
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2020-03-03 10:30:34
 */

package com.example.demo.controller.resource;

import com.example.demo.model.resource.ResourceView;
import com.example.demo.service.IResourceService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Resource;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Resource
    private IResourceService resourceService;

    @GetMapping("/list")
    public List<ResourceView> getResource() {
        return resourceService.getResource();
    }
}
