package com.hospital.appointment.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hospital.appointment.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author auth
 * @since 2022-02-19
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}
