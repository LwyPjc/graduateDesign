package com.graduation.mesume.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.mesume.entity.SysUser;
import com.graduation.mesume.dao.SysUserMapper;
import com.graduation.mesume.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author auth
 * @since 2021-01-27
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    /**
     * 根据用户名查找用户
     * @param userName
     * @return
     */
    @Override
    public SysUser findByUserName(String userName) {
        return sysUserMapper.findByUserName(userName);
    }

    @Override
    public void updateNameByUserName(String userName, String name) {
        sysUserMapper.updateNameByUserName(userName,name);
    }
}
