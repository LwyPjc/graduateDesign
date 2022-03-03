package com.graduation.warning.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zoe.optimus.core.base.entity.BaseEntity;


import java.util.Date;

/**
 * <p>标题: collect_info实体</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: mic
 * @date 2022-3-3
 */
public class CollectInfo extends Model<CollectInfo>{

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    private String openid;

    private Integer dptId;

    private String dptName;

    private Integer docId;

    private String docName;

    private Date createTime;

    private Integer deleteFlg;

}
