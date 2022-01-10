package ${basepackage}<#if subpackage!="">.${subpackage}</#if>.entity;

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
<#assign className=table.className>
<#assign classNameLower=className?uncap_first>

/**
 * <p>标题: ${table.sqlName}实体</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) ${now?string('yyyy')}</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: ${author}
 * @date ${now?date}
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Alias("${className}")
@ApiModel(description = "${table.remarks!}实体")
@TableName("${table.schema}.${table.sqlName}")
public class ${className} extends BaseEntity{

    <#list table.columns as column>
    <#if column.pk>
    @TableId
    </#if>
    @ApiModelProperty(value="${column.remarks!}")
    <#if column.javaType == "java.sql.Clob">
    @TableField(jdbcType = JdbcType.CLOB)
    private String ${column.columnNameLower};
    <#elseif column.javaType == "java.sql.Blob">
    @TableField(typeHandler = com.zoe.optimus.core.mybatis.typehandler.BlobToStringTypeHandler.class)
    private String ${column.columnNameLower};
    <#else>
    private ${column.javaType} ${column.columnNameLower};
    </#if>

    </#list>
}
