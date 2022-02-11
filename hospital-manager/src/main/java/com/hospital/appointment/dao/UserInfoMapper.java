package com.hospital.appointment.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hospital.appointment.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>标题: 服务 - 数据交互层</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: mic
 * @date 2022-2-10
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    boolean save(UserInfo userInfo);
}