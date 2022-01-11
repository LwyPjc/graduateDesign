package com.graduation.mesume.service.impl;

import com.google.common.collect.Lists;
import com.graduation.mesume.entity.SysCategory;
import com.graduation.mesume.entity.ZtGoods;
import com.graduation.mesume.dao.ZtGoodsMapper;
import com.graduation.mesume.entity.dto.ZtGoodsDto;
import com.graduation.mesume.service.SysCategoryService;
import com.graduation.mesume.service.ZtGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 物品信息 服务实现类
 * </p>
 *
 * @author auth
 * @since 2021-02-01
 */
@Service
public class ZtGoodsServiceImpl extends ServiceImpl<ZtGoodsMapper, ZtGoods> implements ZtGoodsService {
    @Autowired
    private SysCategoryService sysCategoryService;
    @Autowired
    private ZtGoodsMapper ztGoodsMapper;
    /**
     * 将文物类型转为中文
     * @param goodsList
     * @return
     */
    public List<ZtGoodsDto> transferType(List<ZtGoodsDto> goodsList){
        List<SysCategory> categories = sysCategoryService.findList(new SysCategory());
        //获取所有分类 （id，名称） 以便将文物类型转为中文
        Map<Integer,String> idAndName = categories.stream().collect(Collectors.toMap(SysCategory::getId,SysCategory::getName,(a, b)->a));
        List<ZtGoodsDto> dtoList = Lists.newArrayList();
        for(ZtGoods goods:goodsList){
            ZtGoodsDto dto = (ZtGoodsDto)goods;
            if(idAndName.containsKey(dto.getType())){
                dto.setTypeChn(idAndName.get(dto.getType()));
            }
            if(0 == dto.getStatus()){
                dto.setStatusChn("待审核");
            }else {
                dto.setStatusChn("审核通过");
            }
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public void updateGoodStatus(int goodId,int status) {
        ztGoodsMapper.updateGoodStatus(goodId,status);
    }
}
