package com.hospital.appointment.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zoe.optimus.core.base.entity.BaseEntity;


import java.util.Date;

/**
 * <p>标题: department实体</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: kknight
 * @date 2022-2-20
 */
public class Department extends Model<Department>{

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String desc;

    private Integer parentId;

    private String deleteFlg;

    private Date createTime;

}
