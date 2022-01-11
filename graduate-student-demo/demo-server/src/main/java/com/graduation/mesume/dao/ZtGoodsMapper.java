package com.graduation.mesume.dao;

import com.graduation.mesume.entity.ZtGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 物品信息 Mapper 接口
 * </p>
 *
 * @author auth
 * @since 2021-02-01
 */
@Mapper
@Repository
public interface ZtGoodsMapper extends BaseMapper<ZtGoods> {

    /**
     * 更新文物的状态
     * @param status
     */
    void updateGoodStatus(int goodId,int status);
}
