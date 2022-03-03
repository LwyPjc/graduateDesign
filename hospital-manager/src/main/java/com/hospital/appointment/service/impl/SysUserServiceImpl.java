package com.hospital.appointment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.appointment.dao.SysUserMapper;
import com.hospital.appointment.entity.SysUser;
import com.hospital.appointment.service.SysUserService;
import com.hospital.appointment.utils.Constant;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author auth
 * @since 2022-02-19
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public SysUser findByUserName(String userName) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        //设置username = 参数
        queryWrapper.eq(Constant.USER_NAME, userName);
        //查询并获取第一个结果
        return this.getOne(queryWrapper);
    }

    @Override
    public boolean removeByUserName(String userName) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.USER_NAME, userName);
        return this.remove(queryWrapper);
    }

    @Override
    public boolean updatePasswordByUserName(String userName, String pwd, String oldPassword) {
        UpdateWrapper<SysUser> updateWrapperCheckOldPassword = new UpdateWrapper<>();
        updateWrapperCheckOldPassword.eq(Constant.USER_NAME, userName);
        updateWrapperCheckOldPassword.eq(Constant.PASSWORD, oldPassword);
        SysUser one = this.getOne(updateWrapperCheckOldPassword);
        if (one == null) {
            throw new RuntimeException("旧密码不正确");
        }
        UpdateWrapper<SysUser> updateWrapper = new UpdateWrapper<>();
        //设置过滤条件
        updateWrapper.eq(Constant.USER_NAME, userName);
        //设置修改后的值
        updateWrapper.set(Constant.PASSWORD, pwd);
        return this.update(updateWrapper);
    }
}
