package com.graduation.mesume.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.mesume.entity.ZtAdvice;
import com.graduation.mesume.service.ZtAdviceService;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 建议反馈表 前端控制器
 * </p>
 *
 * @author auth
 * @since 2021-02-01
 */
@Controller
@RequestMapping("/ztAdvice")
@CrossOrigin
@ResponseBody
public class ZtAdviceController {

    @Autowired
    private ZtAdviceService ztAdviceService;

    @GetMapping("/findList")
    public List<ZtAdvice> findList(ZtAdvice ztAdvice) {
        return ztAdviceService.findList(ztAdvice);
    }


    @GetMapping("/findListByPage")
    public Page<ZtAdvice> findListByPage(ZtAdvice ztAdvice, Page page) {
        QueryWrapper<ZtAdvice> queryWrapper = new QueryWrapper<>(ztAdvice);
        return ztAdviceService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public ZtAdvice getById(@PathVariable String id) {
        return ztAdviceService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(ZtAdvice ztAdvice,String openid) {
        ztAdvice.setCreateTime(LocalDateTime.now());
        ztAdvice.setUserId(openid);
        ztAdviceService.save(ztAdvice);
        return ztAdvice.getId();
    }

    @PostMapping("/edit")
    public boolean edit(ZtAdvice ztAdvice) {
        return ztAdviceService.updateById(ztAdvice);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return ztAdviceService.removeById(id);
    }


}

