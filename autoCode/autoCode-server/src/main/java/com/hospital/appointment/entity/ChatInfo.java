package com.hospital.appointment.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zoe.optimus.core.base.entity.BaseEntity;


import java.util.Date;

/**
 * <p>标题: chat_info实体</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: mic
 * @date 2022-3-2
 */
public class ChatInfo extends Model<ChatInfo>{

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    private String openid;

    private String trueName;

    private Integer docId;

    private String docName;

    private String content;

    private Date createTime;

    private String sendFrom;

}
