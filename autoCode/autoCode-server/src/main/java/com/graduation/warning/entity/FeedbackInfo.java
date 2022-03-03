package com.graduation.warning.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zoe.optimus.core.base.entity.BaseEntity;


import java.util.Date;

/**
 * <p>标题: feedback_info实体</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: mic
 * @date 2022-3-3
 */
public class FeedbackInfo extends Model<FeedbackInfo>{

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    private String content;

    private String openid;

    private Date createTime;

    private String temp1;

}
