package com.graduation.mesume.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduation.mesume.entity.SysUser;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author auth
 * @since 2021-01-27
 */
public interface SysUserService extends BaseService<SysUser> {
    /**
     * 根据用户名查找用户
     * @param userName
     * @return
     */
    SysUser findByUserName(String userName);

    /**
     * 根据用户名更新用户
     */
    void updateNameByUserName(String userName,String name);
}
