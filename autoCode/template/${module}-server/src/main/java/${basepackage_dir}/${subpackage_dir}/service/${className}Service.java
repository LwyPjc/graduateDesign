package ${basepackage}<#if subpackage!="">.${subpackage}</#if>.service;
        <#assign className=table.className>
        <#assign classNameLower=className?uncap_first>
import ${basepackage}<#if subpackage!="">.${subpackage}</#if>.entity.${className};

/**
 * <p>标题: ${table.remarks!}服务 - 业务逻辑层</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) ${now?string('yyyy')}</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: ${author}
 * @date ${now?date}
 */
public interface ${className}Service extends BaseService<${className}> {

}