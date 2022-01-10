package com.graduation.mesume.dao;

import com.graduation.mesume.entity.ZtAdvice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 建议反馈表 Mapper 接口
 * </p>
 *
 * @author auth
 * @since 2021-02-01
 */
@Mapper
@Repository
public interface ZtAdviceMapper extends BaseMapper<ZtAdvice> {

}
