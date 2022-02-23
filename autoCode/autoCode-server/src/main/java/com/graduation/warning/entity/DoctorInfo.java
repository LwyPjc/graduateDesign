package com.graduation.warning.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zoe.optimus.core.base.entity.BaseEntity;


import java.util.Date;

/**
 * <p>标题: doctor_info实体</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: mic
 * @date 2022-2-21
 */
public class DoctorInfo extends Model<DoctorInfo>{

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer dptId;

    private String descs;

    private Integer titleId;

    private String phone;

    private String temp1;

    private String dptName;

}
