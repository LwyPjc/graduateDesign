package com.hospital.appointment.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hospital.appointment.entity.AptRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>标题: 服务 - 数据交互层</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: kknight
 * @date 2022-2-20
 */
@Mapper
public interface AptRecordMapper extends BaseMapper<AptRecord> {
    List<AptRecord> findByOpenid(String openid);
}