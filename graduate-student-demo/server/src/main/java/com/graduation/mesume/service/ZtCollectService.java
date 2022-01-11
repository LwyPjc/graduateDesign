package com.graduation.mesume.service;

import com.graduation.mesume.entity.ZtCollect;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduation.mesume.entity.ZtGoods;

import java.util.List;

/**
 * <p>
 * 收藏信息 服务类
 * </p>
 *
 * @author auth
 * @since 2021-02-01
 */
public interface ZtCollectService extends BaseService<ZtCollect> {

    /**
     * 统计文物被收藏的次数
     * @param goodId
     * @return
     */
    int countCollectByGoods(int goodId);

    /**
     * 判断文物是否被用户收藏
     */
    boolean isUserCollectGood(String goodId,String userId);

    /**
     * 用户取消收藏文物
     */
    void cancelCollect(Integer goodId,String userId);

    /**
     * 根据用户id查找收藏的文物信息
     * @param userId
     * @return
     */
    List<ZtGoods> getMyCollect(String userId);
}
