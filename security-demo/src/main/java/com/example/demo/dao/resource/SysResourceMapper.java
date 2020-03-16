/*
 * @(#) TSysResourceMapper
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2020
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2020-03-03 10:37:40
 */

package com.example.demo.dao.resource;

import com.example.demo.entity.resource.SysResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.resource.ResourceView;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author wangxd
 * @since 2020-03-03
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {
    List<ResourceView> getResource();
}
