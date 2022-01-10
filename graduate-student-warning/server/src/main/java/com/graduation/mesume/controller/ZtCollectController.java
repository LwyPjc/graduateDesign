package com.graduation.mesume.controller;


import com.graduation.mesume.entity.ZtCollect;
import com.graduation.mesume.entity.ZtGoods;
import com.graduation.mesume.service.ZtCollectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
/**
 * <p>
 * 收藏信息 前端控制器
 * </p>
 *
 * @author auth
 * @since 2021-02-01
 */
@Controller
@RequestMapping("/ztCollect")
@CrossOrigin
@ResponseBody
public class ZtCollectController {

    @Autowired
    private ZtCollectService ztCollectService;

    @GetMapping("/findList")
    public List<ZtCollect> findList(ZtCollect ztCollect) {
        return ztCollectService.findList(ztCollect);
    }

    @GetMapping("/findListByPage")
    public Page<ZtCollect> findListByPage(ZtCollect ztCollect, Page page) {
        QueryWrapper<ZtCollect> queryWrapper = new QueryWrapper<>(ztCollect);
        return ztCollectService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    public ZtCollect getById(@PathVariable String id) {
        return ztCollectService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(ZtCollect ztCollect) {
        ztCollectService.save(ztCollect);
        return ztCollect.getId();
    }

    /**
     * 收藏文物
     * @param goodId
     * @param openid
     * @return
     */
    @GetMapping("/collect")
    public Serializable collect(Integer goodId,String openid) {
        ZtCollect ztCollect = new ZtCollect();
        ztCollect.setCollectTime(LocalDateTime.now());
        //文物数量或者用户id为空直接返回
        if(StringUtils.isEmpty(openid) || goodId == null){
            return false;
        }
        ztCollect.setGoodsId(goodId);
        ztCollect.setUserId(openid);
        ztCollectService.save(ztCollect);
        return ztCollect.getId();
    }

    @PostMapping("/edit")
    public boolean edit(ZtCollect ztCollect) {
        return ztCollectService.updateById(ztCollect);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return ztCollectService.removeById(id);
    }

    /**
     * 取消收藏文物
     * @param goodId
     * @param openid
     * @return
     */
    @GetMapping("/cancelCollect")
    public boolean cancelCollect(Integer goodId,String openid) {
        //文物数量或者用户id为空直接返回
        if(StringUtils.isEmpty(openid) || goodId == null){
            return false;
        }
        ztCollectService.cancelCollect(goodId,openid);
        return true;
    }

    /**
     * 我的收藏
     * @param openid
     * @return
     */
    @GetMapping("/myCollect")
    public List<ZtGoods> myCollect(String openid) {
        return ztCollectService.getMyCollect(openid);
    }
}

