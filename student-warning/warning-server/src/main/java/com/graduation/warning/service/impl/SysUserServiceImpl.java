package com.graduation.warning.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.graduation.warning.entity.SysUser;
import com.graduation.warning.dao.SysUserMapper;
import com.graduation.warning.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduation.warning.util.Constant;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author auth
 * @since 2021-03-29
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public SysUser findByUserName(String userName) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        //设置username = 参数
        queryWrapper.eq(Constant.USER_NAME,userName);
        //查询并获取第一个结果
        return this.getOne(queryWrapper);
    }

    @Override
    public boolean removeByUserName(String userName) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Constant.USER_NAME,userName);
        return this.remove(queryWrapper);
    }

    @Override
    public boolean updatePasswordByUserName(String userName,String pwd) {
        UpdateWrapper<SysUser> updateWrapper = new UpdateWrapper<>();
        //设置过滤条件
        updateWrapper.eq(Constant.USER_NAME,userName);
        //设置修改后的值
        updateWrapper.set(Constant.PASSWORD,pwd);
        return this.update(updateWrapper);
    }
}
