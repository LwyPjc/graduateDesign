package com.hospital.appointment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.appointment.entity.HospitalInfo;
import com.hospital.appointment.dao.HospitalInfoMapper;
import com.hospital.appointment.service.HospitalInfoService;

/**
 * <p>标题: 服务 - 业务逻辑层</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: mic
 * @date 2022-2-14
 */
@Service
public class HospitalInfoServiceImpl extends ServiceImpl<HospitalInfoMapper, HospitalInfo> implements HospitalInfoService {
    @Autowired
    private HospitalInfoMapper hospitalInfoMapper;

    @Override
    public HospitalInfo getHospitalInfo() {
        return hospitalInfoMapper.getHospitalInfo();
    }
}
