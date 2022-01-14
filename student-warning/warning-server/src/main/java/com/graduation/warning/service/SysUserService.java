package com.graduation.warning.service;

import com.graduation.warning.entity.SysUser;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author auth
 * @since 2021-03-29
 */
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 根据用户名查找用户
     */
    SysUser findByUserName(String userName);

    /**
     * 根据用户名删除用户
     */
    boolean removeByUserName(String userName);

    /**
     * 根据用户名更新密码
     */
    boolean updatePasswordByUserName(String userName,String pwd);
}
