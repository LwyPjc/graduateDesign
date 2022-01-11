package com.graduation.mesume.controller;


import cn.hutool.core.bean.BeanUtil;
import com.graduation.mesume.entity.ZtDonate;
import com.graduation.mesume.entity.ZtGoods;
import com.graduation.mesume.entity.dto.ZtGoodsDto;
import com.graduation.mesume.service.ZtDonateService;
import com.graduation.mesume.service.ZtGoodsService;
import com.graduation.mesume.util.Constants;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 捐赠信息 前端控制器
 * </p>
 *
 * @author auth
 * @since 2021-02-01
 */
@Controller
@RequestMapping("/ztDonate")
@CrossOrigin
@ResponseBody
public class ZtDonateController {
    @Autowired
    private ZtDonateService ztDonateService;
    @Autowired
    private ZtGoodsService ztGoodsService;

    @GetMapping("/findList")
    public List<ZtGoodsDto> findList(ZtGoodsDto ztGoodsDto) {
        return ztDonateService.findDonateGoodList(ztGoodsDto);
    }

    @GetMapping("/findListByPage")
    public Page<ZtGoodsDto> findListByPage(ZtGoodsDto ztGoodsDto, Page page) {
        Page result = new Page();
        BeanUtil.copyProperties(page,result,true);
        result.setRecords(ztGoodsService.transferType(ztDonateService.findDonateGoodList(ztGoodsDto)));
        return result;
    }

    @GetMapping("/{id}")
    public ZtDonate getById(@PathVariable String id) {
        return ztDonateService.getById(id);
    }

    @PostMapping("/save")
    public Serializable save(ZtDonate ztDonate) {
        ztDonateService.save(ztDonate);
        return ztDonate.getId();
    }

    @PostMapping("/edit")
    public boolean edit(ZtDonate ztDonate) {
        return ztDonateService.updateById(ztDonate);
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        ZtDonate ztDonate = ztDonateService.getById(id);
        //删除文物
        ztGoodsService.removeById(ztDonate.getGoodsId());
        //删除捐赠信息
        return ztDonateService.removeById(id);
    }
    @GetMapping("/myDonate")
    public List<ZtGoodsDto> myDonate(String openid) {
        ZtGoodsDto ztGoodsDto = new ZtGoodsDto();
        ztGoodsDto.setUserId(openid);
        return ztDonateService.findDonateGoodList(ztGoodsDto);
    }

    /**
     * 小程序发起捐赠
     * @param name
     * @param produce
     * @param phone
     * @param openid
     * @param file
     */
    @PostMapping("/donate")
    public void donate(String name,String produce,String phone,String openid, MultipartFile file) {
        //保存的文件路径
        String savePath = Constants.IMG_BASE_PATH;
        try {
            String md5 =  UUID.randomUUID().toString();
            savePath += md5;
            File saveFile = new File( savePath + "/"+file.getOriginalFilename());
            //保存文件
            FileUtils.copyInputStreamToFile(file.getInputStream(),saveFile);
            //保存文物信息
            ZtGoods ztGoods = new ZtGoods();
            ztGoods.setPhotoUrl(md5);
            ztGoods.setPhotoName(file.getOriginalFilename());
            ztGoods.setName(name);
            ztGoods.setProduce(produce);
            ztGoods.setStatus(Constants.GOODS_STATUS_AUDITING);
            ztGoodsService.save(ztGoods);
            //保存捐赠信息
            ZtDonate ztDonate = new ZtDonate();
            ztDonate.setDonateTime(LocalDateTime.now());
            ztDonate.setUserId(openid);
            ztDonate.setGoodsId(ztGoods.getId());
            ztDonateService.save(ztDonate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

