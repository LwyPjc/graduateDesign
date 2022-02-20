package com.hospital.appointment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.appointment.entity.UserInfo;
import com.hospital.appointment.dao.UserInfoMapper;
import com.hospital.appointment.service.UserInfoService;

/**
 * <p>标题: 服务 - 业务逻辑层</p>
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public boolean save(UserInfo userInfo) {
        return userInfoMapper.save(userInfo);
    }

    @Override
    public String getOpenIDByIdCard(String idCard) {
        return userInfoMapper.getOpenIDByIdCard(idCard);
    }
}
