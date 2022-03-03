package com.graduation.warning.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zoe.optimus.core.base.entity.BaseEntity;


import java.util.Date;

/**
 * <p>标题: user_info实体</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: mic
 * @date 2022-3-3
 */
public class UserInfo extends Model<UserInfo>{

    private String avatarUrl;

    private String nickName;

    @TableId(value = "ID", type = IdType.AUTO)
    private String id;

    private String phoneNum;

    private String medicareCard;

    private String gender;

    private String idCard;

    private Integer age;

    private String address;

    private String temp2;

    private String trueName;

}
