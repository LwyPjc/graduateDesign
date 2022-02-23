package com.graduation.warning.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zoe.optimus.core.base.entity.BaseEntity;


import java.util.Date;

/**
 * <p>标题: statistics实体</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: mic
 * @date 2022-2-21
 */
public class Statistics extends Model<Statistics>{

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    private String doctorName;

    private String patientName;

    private Integer doctorId;

    private String patientIds;

    private Integer cnt;

}
