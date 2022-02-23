package com.hospital.appointment.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hospital.appointment.entity.AptRecord;
import com.hospital.appointment.service.AptRecordService;

import java.io.Serializable;
import java.util.List;

/**
 * <p>标题: 预约 - 请求控制层</p>
 * <p>描述: </p>
 * <p>版权: Copyright (c) 2022</p>
 * <p></p>
 *
 * @version: 1.0
 * @author: kknight
 * @date 2022-2-20
 */
@RestController
@RequestMapping("/aptRecord")
public class AptRecordController {

    @Autowired
    private AptRecordService aptRecordService;

    @GetMapping("/findByOpenid")
    public List<AptRecord> findByOpenid(@RequestParam String openid){
        return aptRecordService.findByOpenid(openid);
    }

    @GetMapping("/findList")
    public List<AptRecord> findList(AptRecord aptRecord) {
        return aptRecordService.findList(aptRecord);
    }

    @GetMapping("/findListByPage")
    public Page<AptRecord> findListByPage(AptRecord aptRecord, Page page) {
        QueryWrapper<AptRecord> queryWrapper = new QueryWrapper<>(aptRecord);
        return aptRecordService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public AptRecord getById(@PathVariable String id) {
        return aptRecordService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(AptRecord aptRecord) {
        aptRecordService.save(aptRecord);
        return aptRecord.getId();
    }

    @PostMapping("/edit")
    public boolean edit(AptRecord aptRecord) {
        return aptRecordService.updateById(aptRecord);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return aptRecordService.removeById(id);
    }

}
