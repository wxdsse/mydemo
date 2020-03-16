/*
 * @(#) MyUserDetailService
 * 版权声明 厦门畅享信息技术有限公司, 版权所有 违者必究
 *
 * <br> Copyright:  Copyright (c) 2020
 * <br> Company:厦门畅享信息技术有限公司
 * <br> @author Administrator
 * <br> 2020-02-26 14:34:52
 */

package com.example.demo.config.security;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.dao.security.SysRoleMapper;
import com.example.demo.dao.security.SysUserMapper;
import com.example.demo.entity.security.SysRole;
import com.example.demo.entity.security.SysUser;
import com.example.demo.model.security.UserInfo;
import com.example.demo.model.security.UserRoleInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("登录用户id为：{}", userName);
        SysUser user = sysUserMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUserName, userName));
        if (Objects.isNull(user)) {
            //抛出错误，用户不存在
            throw new UsernameNotFoundException("用户名【 " + userName + "】不存在");
        }
        //获取用户权限
        List<UserRoleInfo> authorities = new ArrayList<>();
        List<SysRole> roles = sysRoleMapper.getRoleByUserId(user.getUserId());
        if (CollectionUtils.isNotEmpty(roles)) {
            roles.forEach(e -> authorities.add(new UserRoleInfo().setRoleName(e.getName())));
        }
        return new UserInfo(userName, user.getPassword(), authorities);
    }
}
