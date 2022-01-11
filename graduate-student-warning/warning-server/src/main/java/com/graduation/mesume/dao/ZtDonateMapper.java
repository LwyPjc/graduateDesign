package com.graduation.mesume.dao;

import com.graduation.mesume.entity.ZtDonate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduation.mesume.entity.dto.ZtGoodsDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 捐赠信息 Mapper 接口
 * </p>
 *
 * @author auth
 * @since 2021-02-01
 */
@Mapper
@Repository
public interface ZtDonateMapper extends BaseMapper<ZtDonate> {

    /**
     * 获取捐赠详情，包含文物信息
     * @return
     */
    List<ZtGoodsDto> findDonateGoodList(ZtGoodsDto ztGoodsDto);
}
