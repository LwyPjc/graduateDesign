package com.hospital.appointment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.appointment.entity.Department;
import com.hospital.appointment.dao.DepartmentMapper;
import com.hospital.appointment.service.DepartmentService;

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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public List<Department> findAll() {
        return departmentMapper.findAll();
    }
}
