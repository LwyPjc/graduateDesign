package com.graduation.mesume.dao;

import com.graduation.mesume.entity.ZtCollect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduation.mesume.entity.ZtGoods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 收藏信息 Mapper 接口
 * </p>
 *
 * @author auth
 * @since 2021-02-01
 */
@Mapper
@Repository
public interface ZtCollectMapper extends BaseMapper<ZtCollect> {

    /**
     * 统计文物被收藏的次数
     * @param goodId
     * @return
     */
    int countCollectByGoods(int goodId);

    /**
     * 判断文物是否被用户收藏
     */
    int userCollectGood(String goodId,String userId);

    /**
     * 根据文物id和用户id删除记录
     */
    void deleteUserGood(Integer goodId,String userId);

    /**
     * 根据用户id查找收藏的文物信息
     * @param userId
     * @return
     */
    List<ZtGoods> getCollectGoodByUserId(String userId);
}
