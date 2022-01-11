package com.graduation.mesume.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.mesume.entity.SysSetting;
import com.graduation.mesume.service.SysSettingService;
import com.graduation.mesume.util.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 系统配置  前端控制器
 * </p>
 *
 * @author auth
 * @since 2021-02-08
 */
@Controller
@RequestMapping("/sysSetting")
@ResponseBody
@CrossOrigin
public class SysSettingController {
    @Autowired
    private SysSettingService sysSettingService;

    /**
     * 获取列表
     * @param sysSetting
     * @return
     */
    @GetMapping("/findList")
    public List<SysSetting> findList(SysSetting sysSetting) {
        List<SysSetting>  result = sysSettingService.findList(sysSetting);
        if(CollectionUtil.isEmpty(result)){
            return result;
        }
        sortSettingByOrder(result);
        int count = 1;
        for(SysSetting setting:result){
            if(setting.getOrderNum() !=null){
                setting.setContent(count + ". " + setting.getContent());
                count ++;
            }
        }
        return result;
    }

    /**
     * 获取列表（分页）
     * @param sysSetting
     * @param page
     * @return
     */
    @GetMapping("/findListByPage")
    public Page<SysSetting> findListByPage(SysSetting sysSetting, Page page) {
        QueryWrapper<SysSetting> queryWrapper = new QueryWrapper<>(sysSetting);
        return sysSettingService.page(page, queryWrapper);
    }

    /**
     * 根据url的id来获取详细信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public SysSetting getById(@PathVariable String id) {
        return sysSettingService.getById(id);
    }

    /**
     * 创建
     * @param sysSetting
     * @return
     */
    @PostMapping("/save")
    public Serializable save(SysSetting sysSetting) {
        sysSetting.setCreatedTime(LocalDateTime.now());
        sysSettingService.save(sysSetting);
        return sysSetting.getId();
    }

    /**
     * 编辑
     * @param sysSetting
     * @return
     */
    @PostMapping("/edit")
    public boolean edit(SysSetting sysSetting) {
        return sysSettingService.updateById(sysSetting);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return sysSettingService.removeById(id);
    }

    private void sortSettingByOrder(List<SysSetting> result){
        Collections.sort(result, new Comparator<SysSetting>() {
            @Override
            public int compare(SysSetting o1, SysSetting o2) {
                if(o1 == null || o1.getOrderNum() == null){
                    return -1;
                }
                if(o2 == null || o2.getOrderNum() == null){
                    return 1;
                }
                return o1.getOrderNum() - o2.getOrderNum();
            }
        });
    }
}

