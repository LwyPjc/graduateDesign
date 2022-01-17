package ${basepackage}<#if subpackage!="">.${subpackage}</#if>.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zoe.optimus.core.base.entity.BaseEntity;


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
public class ${className} extends Model<${className}>{

    <#list table.columns as column>
    <#if column.pk>
    @TableId(value = "ID", type = IdType.AUTO)
    </#if>
    <#if column.javaType == "java.sql.Clob">
    private String ${column.columnNameLower};
    <#elseif column.javaType == "java.sql.Blob">
    private String ${column.columnNameLower};
    <#else>
    private ${column.javaType} ${column.columnNameLower};
    </#if>

    </#list>
}
