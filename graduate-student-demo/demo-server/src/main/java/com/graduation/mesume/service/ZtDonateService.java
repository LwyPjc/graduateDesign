package com.graduation.mesume.service;

import com.graduation.mesume.entity.ZtDonate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduation.mesume.entity.dto.ZtGoodsDto;

import java.util.List;

/**
 * <p>
 * 捐赠信息 服务类
 * </p>
 *
 * @author auth
 * @since 2021-02-01
 */
public interface ZtDonateService extends BaseService<ZtDonate> {

    /**
     * 获取捐赠详情，包含文物信息
     * @return
     */
    List<ZtGoodsDto> findDonateGoodList(ZtGoodsDto ztGoodsDto);


}
