package com.graduation.warning.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zoe.optimus.core.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * <p>标题: warning_rule实体</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: mic
 * @date 2022-1-16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Alias("WarningRule")
@ApiModel(description = "实体")
@TableName("warning_rule")
public class WarningRule extends BaseEntity{

    @TableId
    @ApiModelProperty(value="ID")
    private Integer id;

    @ApiModelProperty(value="类型:1:缺几节课程 2:缺几次作业 3:成绩不合格")
    private String type;

    @ApiModelProperty(value="预警等级")
    private String warningLevel;

    @ApiModelProperty(value="预警值")
    private String value;

}
