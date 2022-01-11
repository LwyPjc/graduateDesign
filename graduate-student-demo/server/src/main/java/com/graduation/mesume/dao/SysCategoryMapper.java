package com.graduation.mesume.dao;

import com.graduation.mesume.entity.SysCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 物品类别 Mapper 接口
 * </p>
 *
 * @author auth
 * @since 2021-02-01
 */
@Mapper
@Repository
public interface SysCategoryMapper extends BaseMapper<SysCategory> {

}
