package com.graduation.mesume.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.PageUtil;
import com.google.common.collect.Lists;
import com.graduation.mesume.entity.SysCategory;
import com.graduation.mesume.entity.ZtGoods;
import com.graduation.mesume.entity.dto.ZtGoodsDto;
import com.graduation.mesume.service.SysCategoryService;
import com.graduation.mesume.service.ZtCollectService;
import com.graduation.mesume.service.ZtDonateService;
import com.graduation.mesume.service.ZtGoodsService;
import com.graduation.mesume.util.Constants;
import com.graduation.mesume.util.Md5Util;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * <p>
 * 物品信息 前端控制器
 * </p>
 *
 * @author auth
 * @since 2021-02-01
 */
@Controller
@RequestMapping("/ztGoods")
@CrossOrigin
@ResponseBody
public class ZtGoodsController {
    @Autowired
    private ZtGoodsService ztGoodsService;
    @Autowired
    private ZtCollectService ztCollectService;

    @GetMapping("/findList")
    public List<ZtGoods> findList(ZtGoods ztGoods) {
        //只查询审核通过的文物
        ztGoods.setStatus(Constants.GOODS_STATUS_SUCCESS);
        return ztGoodsService.findList(ztGoods);
    }

    @GetMapping("/findListByPage")
    public Page<ZtGoodsDto> findListByPage(ZtGoods ztGoods, Page page) {
        //只查询审核通过的文物
        ztGoods.setStatus(Constants.GOODS_STATUS_SUCCESS);
        QueryWrapper<ZtGoods> queryWrapper = new QueryWrapper<>(ztGoods);
        Page<ZtGoodsDto> goodsPage = ztGoodsService.page(page, queryWrapper);
        List<ZtGoodsDto> goodsDtos = Lists.newArrayList();
        for(int i = 0;i<goodsPage.getRecords().size();i++){
            ZtGoodsDto trs = new ZtGoodsDto();
            BeanUtil.copyProperties(goodsPage.getRecords().get(i),trs,true);
            goodsDtos.add(trs);
        }
        goodsPage.setRecords(ztGoodsService.transferType(goodsDtos));
        return goodsPage;
    }

    @GetMapping("/{id}")
    public ZtGoodsDto getById(@PathVariable String id,String openid) {
        ZtGoodsDto dto = new ZtGoodsDto();
        BeanUtil.copyProperties(ztGoodsService.getById(id),dto,true);
        dto.setCollectCnt(ztCollectService.countCollectByGoods(dto.getId()));
        dto.setCollect(ztCollectService.isUserCollectGood(id,openid));
        return dto;
    }

    @PostMapping("/save")
    public Serializable save(ZtGoods ztGoods) {
        ztGoodsService.save(ztGoods);
        return ztGoods.getId();
    }

    @PostMapping("/edit")
    public boolean edit(ZtGoods ztGoods) {
        return ztGoodsService.updateById(ztGoods);
    }

    /**
     * 上传图片
     * @param request
     * @return
     */
    @PostMapping("/upload")
    public boolean upload(MultipartHttpServletRequest request,ZtGoods ztGoods) {
        //保存的文件路径
        String savePath = Constants.IMG_BASE_PATH;
        //取得文件对象
        MultipartFile file = request.getFile("file");
        boolean result = true;
        try {
            //以文件md5值为文件夹名，避免出现重复
            /*String md5 = Md5Util.fullFileBase64Md5(file.getInputStream());
            md5 = md5.replaceAll("\\\\","-");
            md5 = md5.replaceAll("\\/","-");*/
            String md5 =  UUID.randomUUID().toString();
            savePath += md5;
            File saveFile = new File( savePath + "/"+file.getOriginalFilename());
            //保存文件
            FileUtils.copyInputStreamToFile(file.getInputStream(),saveFile);
            //设置文件地址
            ztGoods.setPhotoUrl(md5);
            ztGoods.setPhotoName(file.getOriginalFilename());
            //id为空表示保存 不为空表示更新
            if(ztGoods.getId() == null){
                save(ztGoods);
            }else {
                result = edit(ztGoods);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return  false;
        }
        return result;
    }

    @GetMapping("/delete/{id}")
    public boolean removeById(@PathVariable Serializable id) {
        return ztGoodsService.removeById(id);
    }

    /**
     * 更新文物的状态
     * @param goodId
     */
    @GetMapping("/auditGoods")
    public String auditGoods(int goodId){
        ztGoodsService.updateGoodStatus(goodId,Constants.GOODS_STATUS_SUCCESS);
        return "success";
    }
}

