package ${basepackage}<#if subpackage!="">.${subpackage}</#if>.controller;

        <#assign className=table.className>
        <#assign classNameLower=className?uncap_first>
        <#assign pkColumn=table.pkColumn>
        <#assign pkColumnName=table.pkColumn.columnName>
        <#assign pkColumnNameLower=table.pkColumn.columnNameLower>
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ${basepackage}<#if subpackage!="">.${subpackage}</#if>.entity.${className};
import ${basepackage}<#if subpackage!="">.${subpackage}</#if>.service.${className}Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>标题: ${table.remarks!}服务 - 请求控制层</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) ${now?string('yyyy')}</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: ${author}
 * @date ${now?date}
 */
@RestController
@RequestMapping("<#if subpackage != "">/${subpackage}</#if>/${classNameLower}")
public class ${className}Controller {

    @Autowired
    private ${className}Service ${classNameLower}Service;

    @GetMapping("/findList")
    public List<${className}> findList(${className} ${classNameLower}) {
        return ${classNameLower}Service.findList(${classNameLower});
    }

    @GetMapping("/findListByPage")
    public Page<${className}> findListByPage(${className} ${classNameLower}, Page page) {
        QueryWrapper<${className}> queryWrapper = new QueryWrapper<>(${classNameLower});
        return ${classNameLower}Service.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public ${className} getById(@PathVariable String id) {
        return ${classNameLower}Service.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(${className} ${classNameLower}) {
        ${classNameLower}Service.save(${classNameLower});
        return ${classNameLower}.get${pkColumnName}();
    }

    @PostMapping("/edit")
    public boolean edit(${className} ${classNameLower}) {
        return ${classNameLower}Service.updateById(${classNameLower});
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return ${classNameLower}Service.removeById(id);
    }

}
