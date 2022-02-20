package com.hospital.appointment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.appointment.entity.AptRecord;
import com.hospital.appointment.dao.AptRecordMapper;
import com.hospital.appointment.service.AptRecordService;

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
@Service
public class AptRecordServiceImpl extends ServiceImpl<AptRecordMapper, AptRecord> implements AptRecordService {
    @Autowired
    private AptRecordMapper aptRecordMapper;
    @Override
    public List<AptRecord> findByOpenid(String openid) {
        return aptRecordMapper.findByOpenid(openid);
    }
}
