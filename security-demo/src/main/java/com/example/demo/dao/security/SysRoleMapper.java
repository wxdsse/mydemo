/*
 * @(#) TSysRoleMapper
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2020
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2020-02-26 15:25:39
 */

package com.example.demo.dao.security;

import com.example.demo.entity.security.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author wangxd
 * @since 2020-02-26
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> getRoleByUserId(String userId);
}
