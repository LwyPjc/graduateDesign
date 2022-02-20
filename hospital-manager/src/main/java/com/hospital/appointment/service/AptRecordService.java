package com.hospital.appointment.service;

import com.hospital.appointment.entity.AptRecord;

import java.util.List;

/**
 * <p>标题: 服务 - 业务逻辑层</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: kknight
 * @date 2022-2-20
 */
public interface AptRecordService extends BaseService<AptRecord> {
List<AptRecord> findByOpenid(String openid);
}