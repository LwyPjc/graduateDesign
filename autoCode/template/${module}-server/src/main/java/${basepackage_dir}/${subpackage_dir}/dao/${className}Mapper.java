package ${basepackage}<#if subpackage!="">.${subpackage}</#if>.dao;

<#assign className=table.className>
        <#assign classNameLower=className?uncap_first>
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${basepackage}<#if subpackage!="">.${subpackage}</#if>.entity.${className};
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>标题: ${table.remarks!}服务 - 数据交互层</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) ${now?string('yyyy')}</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: ${author}
 * @date ${now?date}
 */
@Mapper
public interface ${className}Mapper extends BaseMapper<${className}> {

}