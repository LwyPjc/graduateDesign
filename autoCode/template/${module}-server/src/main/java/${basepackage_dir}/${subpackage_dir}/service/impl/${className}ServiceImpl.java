package ${basepackage}<#if subpackage!="">.${subpackage}</#if>.service.impl;

        <#assign className=table.className>
        <#assign classNameLower=className?uncap_first>
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.zoe.optimus.core.base.service.impl.BaseServiceImpl;
import ${basepackage}<#if subpackage!="">.${subpackage}</#if>.entity.${className};
import ${basepackage}<#if subpackage!="">.${subpackage}</#if>.dao.${className}Dao;
import ${basepackage}<#if subpackage!="">.${subpackage}</#if>.service.${className}Service;

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
@Slf4j
@Service
public class ${className}ServiceImpl extends BaseServiceImpl<${className}, ${className}Dao> implements ${className}Service {

}
