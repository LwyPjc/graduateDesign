package com.graduation.mesume.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduation.mesume.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author auth
 * @since 2021-01-27
 */
@Mapper
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

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
