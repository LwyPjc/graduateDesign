package com.graduation.mesume.service;

import com.graduation.mesume.entity.ZtGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduation.mesume.entity.dto.ZtGoodsDto;

import java.util.List;

/**
 * <p>
 * 物品信息 服务类
 * </p>
 *
 * @author auth
 * @since 2021-02-01
 */
public interface ZtGoodsService extends BaseService<ZtGoods> {

    /**
     * 将文物类型转为中文
     * @param goodsList
     * @return
     */
    List<ZtGoodsDto> transferType(List<ZtGoodsDto> goodsList);


    /**
     * 更新文物的状态
     * @param status
     */
    void updateGoodStatus(int goodId,int status);
}
