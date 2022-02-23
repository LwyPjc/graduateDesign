package com.hospital.appointment.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hospital.appointment.entity.Department;
import org.apache.ibatis.annotations.Mapper;

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
public interface DepartmentMapper extends BaseMapper<Department> {

}