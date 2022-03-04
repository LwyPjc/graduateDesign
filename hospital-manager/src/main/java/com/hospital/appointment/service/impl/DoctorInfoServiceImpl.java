package com.hospital.appointment.service.impl;

import com.hospital.appointment.dao.DoctorInfoMapper;
import com.hospital.appointment.entity.DoctorInfo;
import com.hospital.appointment.service.DoctorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * <p>标题: 服务 - 业务逻辑层</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: mic
 * @date 2022-2-21
 */
@Service
public class DoctorInfoServiceImpl extends ServiceImpl<DoctorInfoMapper, DoctorInfo> implements DoctorInfoService {
    @Autowired
    private DoctorInfoMapper doctorInfoMapper;
    @Override
    public List<DoctorInfo> getByDptId(String dptId) {
        return doctorInfoMapper.getByDptId(dptId);
    }

    @Override
    public List<DoctorInfo> findByOpenid(String openid) {
        return doctorInfoMapper.findByOpenid(openid);
    }
}
