package com.graduation.warning.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zoe.optimus.core.base.entity.BaseEntity;


import java.util.Date;

/**
 * <p>标题: participation_evaluate实体</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: mic
 * @date 2022-1-17
 */
public class ParticipationEvaluate extends Model<ParticipationEvaluate>{

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    private Integer studentId;

    private Integer teacherId;

    private Integer openCourseId;

    private Integer count;

    private String studentName;

    private String teacherName;

    private String courseName;

}
