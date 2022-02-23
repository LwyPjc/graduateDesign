package com.graduation.warning.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zoe.optimus.core.base.entity.BaseEntity;


import java.util.Date;

/**
 * <p>标题: apt_record实体</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: mic
 * @date 2022-2-21
 */
public class AptRecord extends Model<AptRecord>{

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    private String openid;

    private Integer dptId;

    private Integer docId;

    private Date aptTime;

    private Date createTime;

    private Integer status;

}
