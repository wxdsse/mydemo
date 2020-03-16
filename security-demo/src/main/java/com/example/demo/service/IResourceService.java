/*
 * @(#) IReourceService
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2020
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2020-03-03 15:28:10
 */

package com.example.demo.service;

import com.example.demo.model.resource.ResourceView;

import java.util.List;

public interface IResourceService {

    List<ResourceView> getResource();
}
