package ${basepackage}<#if subpackage!="">.${subpackage}</#if>.controller;

        <#assign className=table.className>
        <#assign classNameLower=className?uncap_first>
        <#assign pkColumn=table.pkColumn>
        <#assign pkColumnName=table.pkColumn.columnName>
        <#assign pkColumnNameLower=table.pkColumn.columnNameLower>
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zoe.optimus.core.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "${className}Controller", tags = {"${table.remarks!}服务"})
public class ${className}Controller extends BaseController {

    @Autowired
    private ${className}Service ${classNameLower}Service;

    @ApiOperation(value = "获取${table.remarks!}列表")
    @GetMapping("/findList")
    public List<${className}> findList(${className} ${classNameLower}) {
        return ${classNameLower}Service.findList(${classNameLower});
    }

    @ApiOperation(value = "获取${table.remarks!}列表（分页）", response = Page.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "current", value = "当前页码", required = true, dataType = "int"),
    })
    @GetMapping("/findListByPage")
    public Page<${className}> findListByPage(${className} ${classNameLower}, Page page) {
        QueryWrapper<${className}> queryWrapper = new QueryWrapper<>(${classNameLower});
        return ${classNameLower}Service.page(page, queryWrapper);
    }

    @ApiOperation(value = "获取${table.remarks!}详细信息", notes = "根据url的id来获取${table.remarks!}详细信息")
    @ApiImplicitParam(name = "id", value = "${table.remarks!}的ID", required = true, dataType = "String")
    @GetMapping("/{id}")
    public ${className} getById(@PathVariable String id) {
        return ${classNameLower}Service.getById(id);
    }

    @ApiOperation(value = "创建${table.remarks!}")
    @PostMapping("/save")
    public Serializable save(${className} ${classNameLower}) {
        ${classNameLower}Service.save(${classNameLower});
        return ${classNameLower}.get${pkColumnName}();
    }

    @ApiOperation(value = "更新${table.remarks!}详细信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "${table.remarks!}ID", required = true, paramType = "query", dataType = "String")
    })
    @PostMapping("/edit")
    public boolean edit(${className} ${classNameLower}) {
        return ${classNameLower}Service.updateById(${classNameLower});
    }

    @ApiOperation(value = "删除${table.remarks!}", notes = "删除${table.remarks!}")
    @ApiImplicitParam(name = "id", value = "${table.remarks!}ID", required = true, paramType = "query", dataType = "String")
    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return ${classNameLower}Service.removeById(id);
    }

}
