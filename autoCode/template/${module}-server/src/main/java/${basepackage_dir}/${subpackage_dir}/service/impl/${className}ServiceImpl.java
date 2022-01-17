package ${basepackage}<#if subpackage!="">.${subpackage}</#if>.service.impl;

        <#assign className=table.className>
        <#assign classNameLower=className?uncap_first>
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${basepackage}<#if subpackage!="">.${subpackage}</#if>.entity.${className};
import ${basepackage}<#if subpackage!="">.${subpackage}</#if>.dao.${className}Mapper;
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
@Service
public class ${className}ServiceImpl extends ServiceImpl<${className}Mapper, ${className}> implements ${className}Service {

}
