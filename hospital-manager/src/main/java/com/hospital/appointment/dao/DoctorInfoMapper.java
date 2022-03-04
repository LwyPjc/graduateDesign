package com.hospital.appointment.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hospital.appointment.entity.DoctorInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>标题: 服务 - 数据交互层</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: mic
 * @date 2022-2-21
 */
@Mapper
public interface DoctorInfoMapper extends BaseMapper<DoctorInfo> {
    List<DoctorInfo> getByDptId(String dptId);
    List<DoctorInfo> findByOpenid(String openid);
}