package com.hospital.appointment.service;
import com.hospital.appointment.entity.UserInfo;

/**
 * <p>标题: 服务 - 业务逻辑层</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: mic
 * @date 2022-2-10
 */
public interface UserInfoService extends BaseService<UserInfo> {
    String getOpenIDByIdCard(String idCard);
}