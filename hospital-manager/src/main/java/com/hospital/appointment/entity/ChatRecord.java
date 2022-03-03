package com.hospital.appointment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;


import java.util.Date;

/**
 * <p>标题: chat_record实体</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: mic
 * @date 2022-3-3
 */
public class ChatRecord extends Model<ChatRecord>{

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    private String openid;

    private String trueName;

    private Integer docId;

    private String docName;

}
