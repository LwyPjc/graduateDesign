package com.graduation.mesume.service.impl;

import com.graduation.mesume.entity.ZtDonate;
import com.graduation.mesume.dao.ZtDonateMapper;
import com.graduation.mesume.entity.dto.ZtGoodsDto;
import com.graduation.mesume.service.ZtDonateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 捐赠信息 服务实现类
 * </p>
 *
 * @author auth
 * @since 2021-02-01
 */
@Service
public class ZtDonateServiceImpl extends ServiceImpl<ZtDonateMapper, ZtDonate> implements ZtDonateService {

    @Autowired
    private ZtDonateMapper ztDonateMapper;
    /**
     * 获取捐赠详情，包含文物信息
     * @return
     */
    @Override
    public List<ZtGoodsDto> findDonateGoodList(ZtGoodsDto ztGoodsDto) {
        return ztDonateMapper.findDonateGoodList(ztGoodsDto);
    }
}
