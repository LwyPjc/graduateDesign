package com.graduation.mesume.service.impl;

import com.graduation.mesume.entity.ZtCollect;
import com.graduation.mesume.dao.ZtCollectMapper;
import com.graduation.mesume.entity.ZtGoods;
import com.graduation.mesume.service.ZtCollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 收藏信息 服务实现类
 * </p>
 *
 * @author auth
 * @since 2021-02-01
 */
@Service
public class ZtCollectServiceImpl extends ServiceImpl<ZtCollectMapper, ZtCollect> implements ZtCollectService {
    @Autowired
    private ZtCollectMapper ztCollectMapper;

    /**
     * 统计文物被收藏的次数
     * @param goodId
     * @return
     */
    @Override
    public int countCollectByGoods(int goodId) {
        return ztCollectMapper.countCollectByGoods(goodId);
    }

    /**
     * 判断文物是否被用户收藏
     */
    @Override
    public boolean isUserCollectGood(String goodId, String userId) {
        return ztCollectMapper.userCollectGood(goodId,userId)>0;
    }

    /**
     * 用户取消收藏文物
     */
    @Override
    public void cancelCollect(Integer goodId, String userId) {
        ztCollectMapper.deleteUserGood(goodId,userId);
    }

    /**
     * 根据用户id查找收藏的文物信息
     * @param userId
     * @return
     */
    @Override
    public List<ZtGoods> getMyCollect(String userId) {
        return ztCollectMapper.getCollectGoodByUserId(userId);
    }
}
