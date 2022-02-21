package com.hospital.appointment.service;


import com.hospital.appointment.entity.SysUser;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author auth
 * @since 2022-02-19
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
